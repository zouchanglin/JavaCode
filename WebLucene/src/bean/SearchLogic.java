package bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import analyzer.CommonAnalyzer;
import net.sf.json.JSONArray;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * SearchLogic.java
 * 
 * @version 1.0
 * @createTime Lucene数据库检索
 */
public class SearchLogic {
	private static Connection conn = null;// 数据库链接对象
	private static Statement stmt = null;
	private static ResultSet rs = null;

	//ServletContext con = this.getServletContext(); // 获取全局域对象
	//String searchDirectory = con.getRealPath("index"); //

	private final String searchDirectory = "E:\\搜索引擎项目\\源代码\\WebLucene\\WebRoot\\WEB-INF\\index";// 索引文件所在路径
	private static File indexFile = null;// 索引文件
	private static Searcher searcher = null;// 搜索
	private static Analyzer analyzer = null;// 分词
	/** 索引页面缓冲 */
	private final int maxBufferedDocs = 500;
	private static boolean isFirst = true;

	/**
	 * 获取数据库数据
	 * 
	 * @return ResultSet
	 * @throws Exception
	 */
	public List<SearchBean> getResult(String queryStr) throws Exception {
		List<SearchBean> result = null;
		conn = JdbcUtil.getConnection();
		if (conn == null) {
			throw new Exception("数据库连接失败！");
		}
		String sql = "select *  from content";
		try {
			if(isFirst) {
				this.createIndex();
				isFirst = false;
			}
			TopDocs topDocs = this.search(queryStr);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			result = this.addHits2List(scoreDocs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("sql查询错误>> sql : " + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getJSON(String keyword) throws Exception {
		SearchLogic logic = new SearchLogic();
		List<SearchBean> result = logic.getResult(keyword.toLowerCase());

		List list = new ArrayList();
		for (int i = 0; i < result.size(); i++) {
			// 循环把搜索结果加入到list中
			HashMap dynBean = new HashMap();
			dynBean.put("id", result.get(i).getId());
			dynBean.put("content", result.get(i).getContent());
			dynBean.put("title", result.get(i).getTitle());
			dynBean.put("link", result.get(i).getLink());
			dynBean.put("fileName", result.get(i).getFileName());
			list.add(dynBean);
			System.out.println("标题："+result.get(i).getTitle());
		}
		System.out.println("查找到：" + list.size());
		if (list.size() > 0) {
			// 格式化成json格式并输出到前台
			JSONArray json = JSONArray.fromObject(list);
			return json.toString();
		} else {
			return "[]";
		}
	}

	/**
	 * 为数据库中的数据源创建索引
	 */
	public void createIndex() throws Exception {
		conn = JdbcUtil.getConnection();
		if (conn == null) {
			throw new Exception("数据库连接失败！");
		}
		Directory directory = null;
		IndexWriter idxWriter = null;

		String sql = "select * from content";
		stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		try {
			indexFile = new File(searchDirectory);
			if (!indexFile.exists()) {
				indexFile.mkdir();
			}
			directory = FSDirectory.open(indexFile);
			analyzer = new IKAnalyzer();
			idxWriter = new IndexWriter(directory, analyzer, true,
					IndexWriter.MaxFieldLength.UNLIMITED);
			idxWriter.setMaxBufferedDocs(maxBufferedDocs);
			idxWriter.deleteAll();
			Document doc = null;
			while (result.next()) {
				// 创建文档
				doc = new Document();
				// 文章链接
				Field link = new Field("link", String.valueOf(result
						.getString("link")), Field.Store.YES,
						Field.Index.NOT_ANALYZED, TermVector.NO);
				// 文章标题
				Field title = new Field("title",
						result.getString("title") == null ? "" : result
								.getString("title"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);
				// 文章内容
				Field content = new Field("content",
						result.getString("content") == null ? "" : result
								.getString("content"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);
				// 本地缓存的文件
				Field fileName = new Field("fileName",
						result.getString("fileName")==null ? "" : result
								.getString("fileName"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);

				doc.add(fileName); // 加入对应缓存网页名
				doc.add(link);// 加入文章链接
				doc.add(title);// 加入文章标题
				doc.add(content);// 加入文章内容
				idxWriter.addDocument(doc);
			}
			idxWriter.optimize();
			idxWriter.close();
			System.out.println("========索引改变========");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


/*	public void createIndex(ResultSet result) throws Exception {
		Directory directory = null;
		IndexWriter idxWriter = null;

		try {
			indexFile = new File(searchDirectory);
			if (!indexFile.exists()) {
				indexFile.mkdir();
			}
			directory = FSDirectory.open(indexFile);
			analyzer = new IKAnalyzer();
            idxWriter = new IndexWriter(directory, analyzer, true,
					IndexWriter.MaxFieldLength.UNLIMITED);
            idxWriter.setMaxBufferedDocs(maxBufferedDocs);
			Document doc = null;
			while (result.next()) {
				// 创建文档
				doc = new Document();
				// 文章链接
				Field link = new Field("link", String.valueOf(result
						.getString("link")), Field.Store.YES,
						Field.Index.NOT_ANALYZED, TermVector.NO);
				// 文章标题
				Field title = new Field("title",
						result.getString("title") == null ? "" : result
								.getString("title"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);
				// 文章内容
				Field content = new Field("content",
						result.getString("content") == null ? "" : result
								.getString("content"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);
				// 本地缓存的文件
				Field fileName = new Field("fileName",
						result.getString("fileName")==null ? "" : result
								.getString("fileName"), Field.Store.YES,
						Field.Index.ANALYZED, TermVector.NO);

				doc.add(fileName); // 加入对应缓存网页名
				doc.add(link);// 加入文章链接
				doc.add(title);// 加入文章标题
				doc.add(content);// 加入文章内容
                idxWriter.addDocument(doc);
			}
            idxWriter.optimize();
            idxWriter.close();
            System.out.println("创建了索引");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	/**
	 * 对索引进行搜索
	 */
	private TopDocs search(String queryStr) throws Exception {
		if (searcher == null) {
			indexFile = new File(searchDirectory);
			// 打开索引文件
			searcher = new IndexSearcher(FSDirectory.open(indexFile));
		}
		// searcher.setSimilarity(new IKSimilarity());
			QueryParser parser = new QueryParser(Version.LUCENE_29, "content",
				new IKAnalyzer());
		Query query = parser.parse(queryStr);

		//Query query = new TermQuery(new Term("content", queryStr));
		TopDocs topDocs = searcher.search(query, null, 100);// 返回匹配到的前100条记录
        //searcher.close();
		return topDocs;
	}

	/**
	 * 返回结果并添加到List中
	 */
	private List<SearchBean> addHits2List(ScoreDoc[] scoreDocs)
			throws Exception {
		List<SearchBean> listBean = new ArrayList<SearchBean>();
		SearchBean bean = null;
		// 添加搜索结果到list，并将结果倒序，这样近抓取的信息就会排列在最前面
		for (int i = scoreDocs.length-1; i > -1; i--){
			int docId = scoreDocs[i].doc;
			Document doc = searcher.doc(docId);
			bean = new SearchBean();
			bean.setId(doc.get("id"));
			bean.setLink(doc.get("link"));
			bean.setTitle(doc.get("title"));
			bean.setContent(doc.get("content"));
			bean.setFileName(doc.get("fileName"));
			listBean.add(bean);
		}
		return listBean;
	}

}
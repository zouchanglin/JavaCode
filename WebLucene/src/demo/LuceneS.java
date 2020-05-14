package demo;

import analyzer.CommonAnalyzer;
import com.servlet.ServletService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.poi.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.textmining.text.extraction.WordExtractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @auther Tianzez
 * @create 2019-04-12 16:42
 */
@WebServlet(name = "LuceneS", urlPatterns = "/LuceneS")
public class LuceneS extends HttpServlet {
    public LuceneS(){super();}

    public void destroy() {
        super.destroy();
    }

    private static File indexDir = new File("E:\\搜索引擎项目\\源代码\\WebLucene\\WebRoot\\WEB-INF\\indexQ");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String checkText= new ServletService().getChinese(request.getParameter("CheckText"));
        checkText = URLDecoder.decode(checkText, "UTF-8");;
        String url = new ServletService().getChinese(request.getParameter("URL"));
        String sString  = null;
        try {
            sString = this.luceneSearch(checkText, url);
            if (sString == "" || sString == null){
                sString = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(sString);
    }
/*
    public String luceneSearch(String selectText, String URL) throws Exception {

        String res = "";
        String dataDirectory = "c:\\luceneFile";
        // 保存Lucene索引文件的路径
        String indexDirectory = "c:\\indexQ";
        // 创建Directory对象 ，也就是分词器对象
        Directory directory = FSDirectory.open(indexDir);
        //Directory directory = new SimpleFSDirectory(new File(indexDirectory));
        // 创建一个简单的分词器,可以对数据进行分词
        Analyzer analyzer = new CommonAnalyzer();
        //Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);

        IndexWriter indexWriter = new IndexWriter(directory, analyzer, true,
                IndexWriter.MaxFieldLength.UNLIMITED);
        File file = new File("c:\\luceneFile"+URL);
        String fileType = URL.substring(URL.lastIndexOf(".") + 1,
                URL.length()).toLowerCase();
        Document doc = new Document();
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = null;
        if (fileType != null && !fileType.equals("")) {
            if (fileType.equals("doc")) {
                // 获取doc的word文档
                WordExtractor wordExtractor = new WordExtractor();
                // 创建Field对象，并放入doc对象中
                // Field的各个字段含义如下：
                // 第1个参数是设置field的name，
                // 第2个参数是value，value值可以是文本（String类型，Reader类型或者是预分享的TokenStream）,
                // 二进制（byet[]）, 或者是数字（一个 Number类型）
                // 第3个参数是Field.Store，选择是否存储，如果存储的话在检索的时候可以返回值
                // 第4个参数是Field.Index，用来设置索引方式

                doc.add(new Field("contents", wordExtractor.extractText(in),
                        Field.Store.YES, Field.Index.ANALYZED));
                //doc.add(new Field("contents", wordExtractor.getText(),
                 //       Field.Store.YES, Field.Index.ANALYZED));
                // 关闭文档
                //.close();

                System.out.println("注意：已为文件“" + URL + "”创建了索引");

            } else if (fileType.equals("docx")) {
                // 获取docx的word文档
                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(
                        new XWPFDocument(in));
                // 创建Field对象，并放入doc对象中
                doc.add(new Field("contents", xwpfWordExtractor.getText(),
                        Field.Store.YES, Field.Index.ANALYZED));
                // 关闭文档
                xwpfWordExtractor.close();
                System.out.println("注意：已为文件“" + URL + "”创建了索引");

            } else if (fileType.equals("pdf")) {
                // 获取pdf文档
                PDFParser parser = new PDFParser(in);
                parser.parse();
                PDDocument pdDocument = parser.getPDDocument();
                PDFTextStripper stripper = new PDFTextStripper();
                // 创建Field对象，并放入doc对象中
                doc.add(new Field("contents", stripper.getText(pdDocument),
                        Field.Store.NO, Field.Index.ANALYZED));
                // 关闭文档
                pdDocument.close();
                System.out.println("注意：已为文件“" + URL + "”创建了索引");

            } else if (fileType.equals("txt")) {
                // 建立一个输入流对象reader
                reader = new InputStreamReader(in);
                // 建立一个对象，它把文件内容转成计算机能读懂的语言
                BufferedReader br = new BufferedReader(reader);
                String txtFile = "";
                String line = null;

                while ((line = br.readLine()) != null) {
                    // 一次读入一行数据
                    txtFile += line;
                }
                // 创建Field对象，并放入doc对象中
                doc.add(new Field("contents", txtFile, Field.Store.NO,
                        Field.Index.ANALYZED));
                System.out.println("注意：已为文件“" + URL + "”创建了索引");
            } else {
            }
        }
        // 创建文件名的域，并放入doc对象中
        doc.add(new Field("filename", URL, Field.Store.YES,
                Field.Index.NOT_ANALYZED));
        // 创建时间的域，并放入doc对象中

        doc.add(new Field("indexDate", DateTools.dateToString(new Date(),
                DateTools.Resolution.DAY), Field.Store.YES,
                Field.Index.NOT_ANALYZED));
        // 写入IndexWriter
        indexWriter.addDocument(doc);
        indexWriter.close();

        // 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
        IndexSearcher indexSearch = new IndexSearcher(directory);
        // 创建QueryParser对象,
        // 第1个参数表示Lucene的版本,
        // 第2个表示搜索Field的字段,
        // 第3个表示搜索使用分词器

        //QueryParser queryParser = new QueryParser(Version.LUCENE_29,
                //"contents", new StandardAnalyzer(Version.LUCENE_29));
        // 生成Query对象
        //Query query = queryParser.parse("百度");
        IndexSearcher isearcher = new IndexSearcher(directory, true);
        QueryParser parser = new QueryParser(Version.LUCENE_29, "contents",
                analyzer);
        Query query = parser.parse(selectText);
        // 搜索结果 TopDocs里面有scoreDocs[]数组，里面保存着索引值
        TopDocs topDocs = isearcher.search(query, 10);
        ScoreDoc[] hits = topDocs.scoreDocs;

        return res;
    }
*/


    public String luceneSearch(String selectText, String URL) throws Exception {
        // selectText为所要查的关键字
        //number为所返回数据条数
        int number = 1000;
        String info = null;
        String fieldName = "text";
        String red1 = "<font color=red>";
        String red2 = "</font>";

        // 把文件加载读取内容
        FileInputStream fileIputS = new FileInputStream("E:\\搜索引擎项目\\源代码\\WebLucene\\WebRoot\\WEB-INF\\luceneFile\\"+URL);
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(fileIputS,"utf-8"));
        String data = null;

        // 分词算法
        Analyzer analyzer = new CommonAnalyzer();

        // 目录
        Directory directory = FSDirectory.open(indexDir);
       // Directory directory = FSDirectory.getDirectory(indexDir, true);
        IndexWriter idxwriter = null;
        try {
            // IndexWriter 是 Lucene 用来创建索引的一个核心的类，他的作用是把一个个的 Document 对象加到索引中来
            idxwriter = new IndexWriter(directory, analyzer, true,
                    IndexWriter.MaxFieldLength.LIMITED);
            idxwriter.setMaxFieldLength(20000);
            //Lucene是无法对PDF，TXT，DOC等物理文件建立索引的，
            //它只能对Document文件建立索引，Document是一个虚拟的文件
            while ((data = bufRead.readLine()) != null) {
                Document doc = new Document();
                doc.add(new Field(fieldName, data, Field.Store.YES,
                        Field.Index.ANALYZED));
                idxwriter.addDocument(doc);
            }
            // Field.Store.YES:为该Field值创建索引
            // Field.Index.TOKENIZED:索引Field的值,使它能被查到
            // Field 对象是用来描述一个文档的某个属性的
            idxwriter.close();
            IndexSearcher isearcher = new IndexSearcher(directory, true);
            QueryParser parser = new QueryParser(Version.LUCENE_29, fieldName,
                    analyzer);
            long begin = new Date().getTime();
            Query query = parser.parse(selectText);
            // Hits     用来保存搜索得到的结果
            System.out.println("传来的数量是: " + number);
            TopDocs topDocs = isearcher.search(query, number);
            ScoreDoc[] hits = topDocs.scoreDocs;
            Document hitDoc = null;
            if (hits.length > 0) {
                System.out.println("查到：" + hits.length + "条记录");
            }
            Highlighter highLighter = null;
            String highLightText = "";
            String getValue = "";
            SimpleHTMLFormatter simpleHTMLFormatter;
            for (int i = 0; i < hits.length; i++) {
                hitDoc = isearcher.doc(hits[i].doc);
                getValue = hitDoc.get(fieldName);
                // 自定义标注高亮文本标签
                simpleHTMLFormatter = new SimpleHTMLFormatter(red1, red2);
                highLighter = new Highlighter(simpleHTMLFormatter,
                        new QueryScorer(query));
                highLightText = highLighter.getBestFragment(analyzer, "field",
                        getValue);
                info = highLightText;
            }
            System.out.println("查到：" + hits.length + "条记录");
            System.out.println(info);
            long end = new Date().getTime();
            isearcher.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufRead.close();
        fileIputS.close();
        return info;
    }
}

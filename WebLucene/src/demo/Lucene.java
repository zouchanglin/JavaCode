/*package demo;

import java.io.*;
import java.util.Date;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
*/

/*
import analyzer.CommonAnalyzer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lucene extends HttpServlet {

    public Lucene(){super();}

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

	private static File indexDir = new File("c:\\index");



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String checkText=request.getParameter("CheckText");
		String url = request.getParameter("url");
		String sString  = null;
		try {
			sString = this.luceneSearch(checkText, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(sString);
	}

	public String luceneSearch(String selectText, String URL) throws Exception {
		// selectTextΪ��Ҫ��Ĺؼ���
		//numberΪ��������������
		int number=1000;
		String info = "";
		String fieldName = "text";
		String red1 = "<font color=red>";
		String red2 = "</font>";

		// ���ļ����ض�ȡ����
		FileInputStream fis = new FileInputStream("C:\\luceneFile\\"+URL);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String a;

		// �ִ��㷨
		Analyzer analyzer = new CommonAnalyzer();

		// Ŀ¼
		Directory directory = FSDirectory.open(indexDir);
		// Directory directory = FSDirectory.getDirectory("/tmp/testindex",
		// true);
		try {
			// IndexWriter �� Lucene ��������������һ�����ĵ��࣬���������ǰ�һ������ Document ����ӵ���������
			IndexWriter iwriter = new IndexWriter(directory, analyzer, true,
					IndexWriter.MaxFieldLength.LIMITED);
			iwriter.setMaxFieldLength(25000);
			// Lucene���޷���PDF��TXT��DOC�������ļ����������ģ���ֻ�ܶ�Document�ļ�����������Document��һ��������ļ�
			while ((a = br.readLine()) != null) {
				Document doc = new Document();
				doc.add(new Field(fieldName, a, Field.Store.YES,
						Field.Index.ANALYZED));
				iwriter.addDocument(doc);
			}
			// Field.Store.YES:Ϊ��Fieldֵ��������
			// Field.Index.TOKENIZED:����Field��ֵ,ʹ���ܱ��鵽
			// Field ��������������һ���ĵ���ĳ�����Ե�
			iwriter.close();
			// ��������
			IndexSearcher isearcher = new IndexSearcher(directory, true);
			QueryParser parser = new QueryParser(Version.LUCENE_29, fieldName,
					analyzer);
			// String select = "˽��";//������
			long begin = new Date().getTime();
			Query query = parser.parse(selectText);
			// Hits �������������õ��Ľ��
			System.out.println("������������: " + number);
			TopDocs topDocs = isearcher.search(query, number);
			ScoreDoc[] hits = topDocs.scoreDocs;
			Document hitDoc = null;
			if (hits.length == 0) {

			} else {
				System.out.println("�鵽��" + hits.length + "����¼");
				info += hits.length;
			}
			Highlighter highlighter = null;
			String highLightText = "";
			String getValue = "";
			SimpleHTMLFormatter simpleHTMLFormatter;
			for (int i = 0; i < hits.length; i++) {
				hitDoc = isearcher.doc(hits[i].doc);
				getValue = hitDoc.get(fieldName);
				// System.out.println("���ݣ�" + hitDoc.get(fieldName));
				// info += "���ݣ�" + hitDoc.get(fieldName)+"<br>";
				simpleHTMLFormatter = new SimpleHTMLFormatter(red1, red2);
				highlighter = new Highlighter(simpleHTMLFormatter,
						new QueryScorer(query));
				highLightText = highlighter.getBestFragment(analyzer, "field",
						getValue);
				// System.out.println(highLightText);

			}
			System.out.println("�鵽��" + hits.length + "����¼");
			System.out.println(info);
			long end = new Date().getTime();
			isearcher.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		br.close();
		fis.close();
		return info;
	}
}











package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import analyzer.CommonAnalyzer;

public class Lucene {
	private static File indexDir = new File("c:\\index");

	// selectTextΪ��Ҫ��Ĺؼ���
	//numberΪ��������������
	public String lucene(String selectText,String URL ) throws Exception {
		int number=1000;
		String info = "";
		String fieldName = "text";
		String red1 = "<font color=red>";
		String red2 = "</font>";

		// ���ļ����ض�ȡ����
		FileInputStream fis = new FileInputStream("C:\\luceneFile\\"+URL);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String a;

		// �ִ��㷨
		Analyzer analyzer = new CommonAnalyzer();

		// Ŀ¼
		Directory directory = FSDirectory.open(indexDir);
		// Directory directory = FSDirectory.getDirectory("/tmp/testindex",
		// true);
		try {
			// IndexWriter �� Lucene ��������������һ�����ĵ��࣬���������ǰ�һ������ Document ����ӵ���������
			IndexWriter iwriter = new IndexWriter(directory, analyzer, true,
					IndexWriter.MaxFieldLength.LIMITED);
			iwriter.setMaxFieldLength(25000);
			// Lucene���޷���PDF��TXT��DOC�������ļ����������ģ���ֻ�ܶ�Document�ļ�����������Document��һ��������ļ�
			while ((a = br.readLine()) != null) {
				Document doc = new Document();
				doc.add(new Field(fieldName, a, Field.Store.YES,
						Field.Index.ANALYZED));
				iwriter.addDocument(doc);
			}
			// Field.Store.YES:Ϊ��Fieldֵ��������
			// Field.Index.TOKENIZED:����Field��ֵ,ʹ���ܱ��鵽
			// Field ��������������һ���ĵ���ĳ�����Ե�
			iwriter.close();
			// ��������
			IndexSearcher isearcher = new IndexSearcher(directory, true);
			QueryParser parser = new QueryParser(Version.LUCENE_29, fieldName,
					analyzer);
			// String select = "˽��";//������
			long begin = new Date().getTime();
			Query query = parser.parse(selectText);
			// Hits �������������õ��Ľ��
			System.out.println("������������: " + number);
			TopDocs topDocs = isearcher.search(query, number);
			ScoreDoc[] hits = topDocs.scoreDocs;
			Document hitDoc = null;
			if (hits.length == 0) {
			
			} else {
				System.out.println("�鵽��" + hits.length + "����¼");
				info += hits.length;
			}
			Highlighter highlighter = null;
			String highLightText = "";
			String getValue = "";
			SimpleHTMLFormatter simpleHTMLFormatter;
			for (int i = 0; i < hits.length; i++) {
				hitDoc = isearcher.doc(hits[i].doc);
				getValue = hitDoc.get(fieldName);
				// System.out.println("���ݣ�" + hitDoc.get(fieldName));
				// info += "���ݣ�" + hitDoc.get(fieldName)+"<br>";
				simpleHTMLFormatter = new SimpleHTMLFormatter(red1, red2);
				highlighter = new Highlighter(simpleHTMLFormatter,
						new QueryScorer(query));
				highLightText = highlighter.getBestFragment(analyzer, "field",
						getValue);
				// System.out.println(highLightText);
				
			}
			System.out.println("�鵽��" + hits.length + "����¼");
			System.out.println(info);
			long end = new Date().getTime();
			isearcher.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		br.close();
		fis.close();
		return info;
	}


}
*/

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

    private static File indexDir = new File("E:\\����������Ŀ\\Դ����\\WebLucene\\WebRoot\\WEB-INF\\indexQ");
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
        // ����Lucene�����ļ���·��
        String indexDirectory = "c:\\indexQ";
        // ����Directory���� ��Ҳ���Ƿִ�������
        Directory directory = FSDirectory.open(indexDir);
        //Directory directory = new SimpleFSDirectory(new File(indexDirectory));
        // ����һ���򵥵ķִ���,���Զ����ݽ��зִ�
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
                // ��ȡdoc��word�ĵ�
                WordExtractor wordExtractor = new WordExtractor();
                // ����Field���󣬲�����doc������
                // Field�ĸ����ֶκ������£�
                // ��1������������field��name��
                // ��2��������value��valueֵ�������ı���String���ͣ�Reader���ͻ�����Ԥ�����TokenStream��,
                // �����ƣ�byet[]��, ���������֣�һ�� Number���ͣ�
                // ��3��������Field.Store��ѡ���Ƿ�洢������洢�Ļ��ڼ�����ʱ����Է���ֵ
                // ��4��������Field.Index����������������ʽ

                doc.add(new Field("contents", wordExtractor.extractText(in),
                        Field.Store.YES, Field.Index.ANALYZED));
                //doc.add(new Field("contents", wordExtractor.getText(),
                 //       Field.Store.YES, Field.Index.ANALYZED));
                // �ر��ĵ�
                //.close();

                System.out.println("ע�⣺��Ϊ�ļ���" + URL + "������������");

            } else if (fileType.equals("docx")) {
                // ��ȡdocx��word�ĵ�
                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(
                        new XWPFDocument(in));
                // ����Field���󣬲�����doc������
                doc.add(new Field("contents", xwpfWordExtractor.getText(),
                        Field.Store.YES, Field.Index.ANALYZED));
                // �ر��ĵ�
                xwpfWordExtractor.close();
                System.out.println("ע�⣺��Ϊ�ļ���" + URL + "������������");

            } else if (fileType.equals("pdf")) {
                // ��ȡpdf�ĵ�
                PDFParser parser = new PDFParser(in);
                parser.parse();
                PDDocument pdDocument = parser.getPDDocument();
                PDFTextStripper stripper = new PDFTextStripper();
                // ����Field���󣬲�����doc������
                doc.add(new Field("contents", stripper.getText(pdDocument),
                        Field.Store.NO, Field.Index.ANALYZED));
                // �ر��ĵ�
                pdDocument.close();
                System.out.println("ע�⣺��Ϊ�ļ���" + URL + "������������");

            } else if (fileType.equals("txt")) {
                // ����һ������������reader
                reader = new InputStreamReader(in);
                // ����һ�����������ļ�����ת�ɼ�����ܶ���������
                BufferedReader br = new BufferedReader(reader);
                String txtFile = "";
                String line = null;

                while ((line = br.readLine()) != null) {
                    // һ�ζ���һ������
                    txtFile += line;
                }
                // ����Field���󣬲�����doc������
                doc.add(new Field("contents", txtFile, Field.Store.NO,
                        Field.Index.ANALYZED));
                System.out.println("ע�⣺��Ϊ�ļ���" + URL + "������������");
            } else {
            }
        }
        // �����ļ������򣬲�����doc������
        doc.add(new Field("filename", URL, Field.Store.YES,
                Field.Index.NOT_ANALYZED));
        // ����ʱ����򣬲�����doc������

        doc.add(new Field("indexDate", DateTools.dateToString(new Date(),
                DateTools.Resolution.DAY), Field.Store.YES,
                Field.Index.NOT_ANALYZED));
        // д��IndexWriter
        indexWriter.addDocument(doc);
        indexWriter.close();

        // ���� IndexSearcher�������IndexWriter�������������Ҫ�ṩһ��������Ŀ¼������
        IndexSearcher indexSearch = new IndexSearcher(directory);
        // ����QueryParser����,
        // ��1��������ʾLucene�İ汾,
        // ��2����ʾ����Field���ֶ�,
        // ��3����ʾ����ʹ�÷ִ���

        //QueryParser queryParser = new QueryParser(Version.LUCENE_29,
                //"contents", new StandardAnalyzer(Version.LUCENE_29));
        // ����Query����
        //Query query = queryParser.parse("�ٶ�");
        IndexSearcher isearcher = new IndexSearcher(directory, true);
        QueryParser parser = new QueryParser(Version.LUCENE_29, "contents",
                analyzer);
        Query query = parser.parse(selectText);
        // ������� TopDocs������scoreDocs[]���飬���汣��������ֵ
        TopDocs topDocs = isearcher.search(query, 10);
        ScoreDoc[] hits = topDocs.scoreDocs;

        return res;
    }
*/


    public String luceneSearch(String selectText, String URL) throws Exception {
        // selectTextΪ��Ҫ��Ĺؼ���
        //numberΪ��������������
        int number = 1000;
        String info = null;
        String fieldName = "text";
        String red1 = "<font color=red>";
        String red2 = "</font>";

        // ���ļ����ض�ȡ����
        FileInputStream fileIputS = new FileInputStream("E:\\����������Ŀ\\Դ����\\WebLucene\\WebRoot\\WEB-INF\\luceneFile\\"+URL);
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(fileIputS,"utf-8"));
        String data = null;

        // �ִ��㷨
        Analyzer analyzer = new CommonAnalyzer();

        // Ŀ¼
        Directory directory = FSDirectory.open(indexDir);
       // Directory directory = FSDirectory.getDirectory(indexDir, true);
        IndexWriter idxwriter = null;
        try {
            // IndexWriter �� Lucene ��������������һ�����ĵ��࣬���������ǰ�һ������ Document ����ӵ���������
            idxwriter = new IndexWriter(directory, analyzer, true,
                    IndexWriter.MaxFieldLength.LIMITED);
            idxwriter.setMaxFieldLength(20000);
            //Lucene���޷���PDF��TXT��DOC�������ļ����������ģ�
            //��ֻ�ܶ�Document�ļ�����������Document��һ��������ļ�
            while ((data = bufRead.readLine()) != null) {
                Document doc = new Document();
                doc.add(new Field(fieldName, data, Field.Store.YES,
                        Field.Index.ANALYZED));
                idxwriter.addDocument(doc);
            }
            // Field.Store.YES:Ϊ��Fieldֵ��������
            // Field.Index.TOKENIZED:����Field��ֵ,ʹ���ܱ��鵽
            // Field ��������������һ���ĵ���ĳ�����Ե�
            idxwriter.close();
            IndexSearcher isearcher = new IndexSearcher(directory, true);
            QueryParser parser = new QueryParser(Version.LUCENE_29, fieldName,
                    analyzer);
            long begin = new Date().getTime();
            Query query = parser.parse(selectText);
            // Hits     �������������õ��Ľ��
            System.out.println("������������: " + number);
            TopDocs topDocs = isearcher.search(query, number);
            ScoreDoc[] hits = topDocs.scoreDocs;
            Document hitDoc = null;
            if (hits.length > 0) {
                System.out.println("�鵽��" + hits.length + "����¼");
            }
            Highlighter highLighter = null;
            String highLightText = "";
            String getValue = "";
            SimpleHTMLFormatter simpleHTMLFormatter;
            for (int i = 0; i < hits.length; i++) {
                hitDoc = isearcher.doc(hits[i].doc);
                getValue = hitDoc.get(fieldName);
                // �Զ����ע�����ı���ǩ
                simpleHTMLFormatter = new SimpleHTMLFormatter(red1, red2);
                highLighter = new Highlighter(simpleHTMLFormatter,
                        new QueryScorer(query));
                highLightText = highLighter.getBestFragment(analyzer, "field",
                        getValue);
                info = highLightText;
            }
            System.out.println("�鵽��" + hits.length + "����¼");
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

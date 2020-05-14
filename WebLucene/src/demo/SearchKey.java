package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import bean.JdbcUtil;
import bean.SearchLogic;

@SuppressWarnings("serial")
public class SearchKey extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchKey() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	private static Connection conn = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action=request.getParameter("action");
		String sString = "";
		if (action.equals("SearchKey")) {
			sString=SearchKey(request,response);
		}else if (action.equals("Searchword")) {
			sString=Searchword(request,response);
		}else if (action.equals("Searchhotword")) {
			sString=Searchhotword(request,response);
		}
		
		out.print(sString);
	}

	private String SearchKey(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String keyword = request.getParameter("keyword");
		keyword = URLDecoder.decode(keyword, "UTF-8");

		if (conn == null) {
			return "";
		}

		// 进行关键字屏蔽
		JdbcUtil db = new JdbcUtil();
		ResultSet rs=db.queryExectue("select  * from tb_Filter ");
		try {
			while (rs.next()) {
				int ret = keyword.indexOf(rs.getString("KeyWord"));
				System.out.println(rs.getString("KeyWord"));
				// 发现用户搜索的关键字中含有任意屏蔽字，直接终止搜索，返回”0“
				if(ret != -1){
					return "0";
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("keyword:"+keyword);
		String[] strings = keyword.split(" ");

		//for (int i = 0; i < strings.length; i++) {
		//	if (strings[i].length() > 0) {
		//		db.updateExecute("insert into search_his(KeyWord,SearchTime)values('"+ strings[i] + "',getdate())");
		//	}

		//}
		String index = request.getParameter("ind");
		if(index.equals("0")) {
			// 更新搜索历史
			db.updateExecute("insert into search_his(KeyWord,SearchTime)values('" + keyword + "',getdate())");

			// 更新热搜词汇
			String ret = "";
			ret = new JdbcUtil().executeScalar("select Count from search_hot where HotWord='"+keyword+"'");
			// 如果该词汇之前搜索过，直接将其次数+1
			if(ret != "") {
				db.updateExecute("update search_hot set Count = (Count+1) where HotWord ='" + keyword + "'");
			// 如果该词汇之前未被搜索过，将该词插入数据库中，次数为1
			}else{
				db.updateExecute("insert into search_hot([HotWord],[Count]) values ('"+ keyword+"','"+ 1+"')");
			}
		}
		SearchLogic dal = new SearchLogic();
		String sString = "";
		try {
			sString = dal.getJSON(keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sString;
	}
	private String Searchword(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		conn = JdbcUtil.getConnection();
		if (conn == null) {
			return "";
		}
		JdbcUtil db = new JdbcUtil();
		ResultSet rs=db.queryExectue("select top 8  * from search_his order by SearchTime desc");
		String sString = "";
		List list = new ArrayList();
		try {
			while (rs.next()) {
				HashMap dynBean = new HashMap();
				dynBean.put("KeyWord", rs.getString("KeyWord"));
				list.add(dynBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONArray json = JSONArray.fromObject(list);

		return json.toString();
	}

	private String Searchhotword(HttpServletRequest request,
							  HttpServletResponse response) throws UnsupportedEncodingException {
		conn = JdbcUtil.getConnection();
		if (conn == null) {
			return "";
		}
		JdbcUtil db = new JdbcUtil();
		ResultSet rs=db.queryExectue("select top 8  * from search_hot order by Count desc");
		String sString = "";
		List list = new ArrayList();
		try {
			while (rs.next()) {
				HashMap dynBean = new HashMap();
				dynBean.put("HotWord", rs.getString("HotWord"));
				list.add(dynBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}

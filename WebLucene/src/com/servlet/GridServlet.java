package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.JdbcUtil;
import bean.PagesHelper;

public class GridServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GridServlet() {
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

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("Action");
		System.out.println("执行GridServlet:" + action);
		String sqlString = "";
		ResultSet rs = null;
		List list = new ArrayList();
		int pageSize = 10;
		int currentpage = 0;
		currentpage = Integer.valueOf(request.getParameter("currentpage"));
		currentpage = Math.max(currentpage, 1);

		String table = "content";
		String returnPage = "../admin.jsp";
		if (action.equals("getfilterlist")){
			table = "tb_Filter";
			returnPage = "../filter.jsp";
		}
		if (action.equals("getlist") || action.equals("getfilterlist")) {
			String msg = "";
			if (request.getParameter("msg") != null) {
				msg = new ServletService().getChinese(request.getParameter("msg"));
				//msg = getChinese(request.getParameter("msg"));
			}
			pageSize = 6;
			PagesHelper model = new PagesHelper();
			model.setTableName(table);
			model.setColumnName("*");
			model.setOrder("id desc");
			if(action.equals("getlist")) {
				model.setFilter(" and title like '%" + msg + "%'");
			}else {
				model.setFilter(" and KeyWord like '%" + msg + "%'");
			}
			// 总共多少条
			int totalCount = Integer.valueOf(String
					.valueOf(new JdbcUtil().executeScalar(model.ToCountString())));
			// 多少页
			int pagecount = totalCount % pageSize == 0 ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			currentpage = Math.min(currentpage, pagecount);
			int start = (currentpage - 1) * pageSize + 1;
			int limit = pageSize;
			model.setCurrentIndex(start);
			model.setPageSize(limit);

			rs = new JdbcUtil().queryExectue(model.ToListString());
			System.out.println(model.ToListString());
			request.setAttribute("datalist", ServletService.convertList(rs));
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("total", totalCount);
			request.getRequestDispatcher(returnPage).forward(request, response);
		}
	}



	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * ResultSet 转list
	 * @param rs
	 * @return
	 */

	/*
	public static List convertList(ResultSet rs) {
		List listOfRows = new ArrayList();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			while (rs.next()) {
				Map mapOfColValues = new HashMap(num);
				for (int i = 1; i <= num; i++) {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				listOfRows.add(mapOfColValues);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listOfRows;
	}
	*/


	@Override
	public void init() throws ServletException {
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SearchLogic;
import net.sf.json.JSONArray;
import bean.JdbcUtil;

public class ServletService extends HttpServlet {

	public ServletService() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy();

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		System.out.println(action);
		String write = "";

		switch (action){
			case "login":
			{
				String loginID = request.getParameter("LoginID");
				String passWord = request.getParameter("PassWord");
				write = new JdbcUtil().executeScalar("select ID from Users where LoginID='"+loginID+"' and Pwd='"+passWord+"'");
				if(write != ""){
					write = "1";
				}else{
					write = "0";
				}
				break;
			}
            case "registered":
            {
                String loginID = request.getParameter("LoginID");
                String passWord = request.getParameter("PassWord");
                String ret = "";
                ret = new JdbcUtil().executeScalar("select ID from Users where LoginID='"+loginID+"'");
                if(ret != ""){
                    write = "1";
                    break;
                }else{
                    new JdbcUtil().updateExecute("insert into Users([LoginID],[Pwd]) values ('"+ loginID+"','"+ passWord+"')");
                    write = "2";
                }
                break;
            }
			case "Del":
				write = Del(request);
				break;
			case "getone":
			{
			    // 获取数据，用于修改
				ResultSet rs = new JdbcUtil().queryExectue("select * from content where ID="+request.getParameter("id"));
				List list=convertList(rs);
				write=JSONArray.fromObject(list).toString();
				break;
			}
            case "getonefilter":
            {
                ResultSet rs = new JdbcUtil().queryExectue("select * from tb_Filter where ID="+request.getParameter("id"));
                List list = convertList(rs);
                write=JSONArray.fromObject(list).toString();
                break;
            }
            case "editfilter":
            {
                String id=request.getParameter("id");
                String word = getChinese(request.getParameter("word"));
                ResultSet rst = new JdbcUtil().queryExectue("select * from tb_Filter where KeyWord='"+word+"'");

                try {
                    // 表中没有相同的屏蔽字，就进行插入/更新
                    if(!rst.next()){
                        if ("0".equals(id)) {
                            write="INSERT INTO tb_Filter VALUES('"+word+"')";
                        }else {
                            write="update tb_Filter set KeyWord='"+word+"'"+" where id="+id;
                        }
                        new JdbcUtil().updateExecute(write);
                        write = "1";
                    }else {
                        write = "2";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
			case "edit":
			{
				String id=request.getParameter("id");
				String link=request.getParameter("link");
				String title = getChinese(request.getParameter("title"));
				String content=getChinese(request.getParameter("content"));
				String fileName =getChinese(request.getParameter("fileName"));
				if(link.startsWith("http://") || link.startsWith("https://")) {
                    if ("0".equals(id)) {
                        write = "INSERT INTO content([Title],[content],[Link],[FileName])VALUES('" + title + "','" + content + "','" + link + "','" + fileName + "')";
                    } else {
                        write = "update content set title='" + title + "',content='" + content + "',Link='" + link + "',FileName='" + fileName + "' where id=" + id;
                    }
                    new JdbcUtil().updateExecute(write);
                    write="1";
                }
				break;
			}
			default:
				break;
		}
		out.print(write);
		out.flush();
		out.close();
		// 如果数据源发生了变化，则重新生成索引
		if (action.equals("Del")&&request.getParameter("Table").equals("content")
				|| action.equals("edit")) {
			SearchLogic hand = new SearchLogic();
			try {
				hand.createIndex();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 取得中文
	 * 
	 * @param
	 * @return
	 */
	public String getChinese(String str) {
		if (str == null) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";

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

	public String Del(HttpServletRequest request) {
		int ID = Integer.valueOf(request.getParameter("ID"));
		String Table = request.getParameter("Table");
		String PK_Name = "id";
		if (Table == "tb_Filter"){
		    PK_Name = "ID";
        }
		String sql = "sp_pkeys '" + Table + "'";

		sql = "delete from " + Table + " where " + PK_Name + "=" + ID;
		new JdbcUtil().updateExecute(sql);
		return "1";
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * ResultSet 转list
	 * @param rs
	 * @return
	 */
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
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}

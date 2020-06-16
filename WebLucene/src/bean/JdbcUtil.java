package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	static Connection connect;

	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=LuceneDB2";
			String username = "sa";
			String password = "123456";
			connect = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public int updateExecute(String sql) {
		int result = 0;
		try {
			Connection connect = getConnection();
			Statement sta = connect.createStatement();
			result = sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public ResultSet queryExectue(String sql) {
		ResultSet rs = null;
		try {
			Connection connect = getConnection();
			Statement sta = connect.createStatement();
			rs = sta.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public String executeScalar(String sql) {
		ResultSet rs = queryExectue(sql);
		String str = "";
		try {
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
}

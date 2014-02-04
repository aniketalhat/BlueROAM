package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection con = null;
	static String driverName = "com.mysql.jdbc.Driver";
	static String dbName = "bluemall";
	static String url = "jdbc:mysql://localhost:3306/";
	static String userName = "root";
	static String password = "";

	public static Connection connectDB() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		try {
			Class.forName(driverName).newInstance();
			con = DriverManager.getConnection(url + dbName, userName, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}

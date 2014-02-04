package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddvertiesName {

	public static void main(String[] args) throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		AddvertiesName nm = new AddvertiesName();
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("List :");
		list = nm.selectAdd();
		for (int i = 0; i < list.size(); i++)
			System.out.println("add name :" + list.get(i));

	}

	ArrayList<String> ls = null;

	public ArrayList<String> selectAdd() throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		
		Statement st;
		
		ResultSet rs;

		Connection con = null;

		DBConnection dbConnection = new DBConnection();

		con = dbConnection.connectDB();
		String sql = "select offer_nm from adverties";

		st = con.createStatement();

		rs = st.executeQuery(sql);
		ls= new ArrayList<String>();
		while (rs.next()) {

			//System.out.println("add name :" + rs.getString("offer_nm"));
			 ls.add(rs.getString("offer_nm"));
			//ls.set(i++, (rs.getString("offer_nm")));
		}

		con.close();

		return ls;
	}

}

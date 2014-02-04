package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
	 Connection con = null;
	public Boolean validUser(String Uname, String Pass) {
		try {
			
			con = DBConnection.connectDB();

			try {
				String quary = "select * from admin where admin_unm = ?";
				PreparedStatement pst = con.prepareStatement(quary);

				pst.setString(1, Uname);

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					String PassDB = rs.getString("admin_pass");
					System.out.println("PassDB : " + PassDB);
					if (Pass.equals(PassDB)) {

						return true;
					}
				}

			} catch (SQLException s) {
				System.out.println();
				s.printStackTrace();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
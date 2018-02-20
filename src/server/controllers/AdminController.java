package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.Admin;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class AdminController {
	
	public static Admin getAdmin(String login, String password) throws SQLException {
		Admin result = null;
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.FIND_ADMIN_BY_LOGIN_AND_PASS);
			Statement.setString(1, login);
			Statement.setString(2, password);
			ResultSet resltset = Statement.executeQuery();
			if (resltset.next()) {
				result = new Admin(resltset);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			Statement.close();
			con.close();
		}
		return result;
	}

}

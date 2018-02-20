package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.Customer;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class CustomerController {

	public static Customer getCustomer(String userName, String password) throws SQLException {
		Customer result = null;
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_USERNAME_AND_PASS);
			Statement.setString(1, userName);
			Statement.setString(2, password);
			ResultSet resltset = Statement.executeQuery();
			if (resltset.next()) {
				result = new Customer(resltset);
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

package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.model.Customer;
import server.model.Owns;
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

	@SuppressWarnings("null")
	public static ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> users = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.GET_ALL_Customers);
			ResultSet rs = Statement.executeQuery();
			while (rs.next()) {
				users.add(new Customer(rs));
			}
			rs.close();
			Statement.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;

	}

	public static Customer getCustomer(int uid) throws SQLException {
		Customer result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_ID);
			stmt.setInt(1, uid);
			ResultSet resltset = stmt.executeQuery();
			if (resltset.next()) {
				result = new Customer(resltset);
			}
			resltset.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return result;
	}

	public static Customer getCustomerByNick(String nick) throws SQLException {
		Customer result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_NICKNAME);
			stmt.setString(1, nick);
			ResultSet resltset = stmt.executeQuery();
			if (resltset.next()) {
				result = new Customer(resltset);
			}
			resltset.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return result;
	}
	
	public static int removeCustomer(int uid) throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Removing Customer");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.DELETE_CUSTOMER);
				try {
					stmt.setInt(1, uid);
					if (stmt.executeUpdate() != 1) {
						throw new Exception();
					}
					System.out.println("Customer deleted, uid:" + uid);
				} catch (Exception e) {
				} finally {
					stmt.close();
				}
				stmt = con.prepareStatement(ApplicationConstants.DELETE_ALL_CUSTOMER_LIKES);
				try {
					stmt.setInt(1, uid);
					stmt.executeUpdate();
				} catch (Exception e) {
				} finally {
					stmt.close();
				}
				stmt = con.prepareStatement(ApplicationConstants.DELETE_ALL_CUSTOMER_REVIEWS);
				try {
					stmt.setInt(1, uid);
					stmt.executeUpdate();
				} catch (Exception e) {
				} finally {
					stmt.close();
				}
			} catch (Exception e) {
			} finally {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

}

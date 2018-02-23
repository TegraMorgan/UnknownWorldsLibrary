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

/**
 * The Class CustomerController.
 */
public class CustomerController {

	/**
	 * Gets the customer.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the customer
	 * @throws SQLException the SQL exception
	 */
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

	/**
	 * Gets the all customers.
	 *
	 * @return the all customers
	 */
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

	/**
	 * Gets the customer.
	 *
	 * @param uid the uid
	 * @return the customer
	 * @throws SQLException the SQL exception
	 */
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

	/**
	 * Gets the customer by nick.
	 *
	 * @param nick the nick
	 * @return the customer by nick
	 * @throws SQLException the SQL exception
	 */
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
	
	/**
	 * Gets the customer by email.
	 *
	 * @param email the email
	 * @return the customer by email
	 * @throws SQLException the SQL exception
	 */
	public static Customer getCustomerByEmail(String email) throws SQLException {
		Customer result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_EMAIL);
			stmt.setString(1, email);
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
	
	/**
	 * Gets the customer by username.
	 *
	 * @param usn the usn
	 * @return the customer by username
	 * @throws SQLException the SQL exception
	 */
	public static Customer getCustomerByUsername(String usn) throws SQLException {
		Customer result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_USERNAME);
			stmt.setString(1, usn);
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
	
	/**
	 * Removes the customer.
	 *
	 * @param uid the uid
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
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

package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.Admin;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class AdminController.
 */
public class AdminController {

	/**
	 * Gets the admin by login and password.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the admin object
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static Admin getAdmin(String login, String password) throws SQLException {
		Admin result = null;
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				Statement = con.prepareStatement(ApplicationConstants.FIND_ADMIN_BY_LOGIN_AND_PASS);
				try {
					Statement.setString(1, login);
					Statement.setString(2, password);
					ResultSet resltset = Statement.executeQuery();
					try {
						if (resltset.next()) {
							result = new Admin(resltset);
						}
					} catch (Exception e) {
					} finally {
						resltset.close();
					}
				} catch (Exception e) {
				} finally {
					Statement.close();
				}
			} catch (Exception e) {
			} finally {
				con.close();
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			Statement.close();
			con.close();
		}
		return result;
	}

	public static Admin getDupAdmin(String login) {
		Admin result = null;
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				Statement = con.prepareStatement(ApplicationConstants.FIND_ADMIN_BY_LOGIN);
				try {
					Statement.setString(1, login);
					ResultSet resltset = Statement.executeQuery();
					try {
						if (resltset.next()) {
							result = new Admin(resltset);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						resltset.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					Statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

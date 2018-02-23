package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import server.model.Transaction;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class TransactionController.
 */
public class TransactionController {

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public static ArrayList<Transaction> getTransactions() {
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.GET_TRANSACTIONS);
				try {
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							result.add(new Transaction(rs));
						}
					} catch (Exception e) {
					} finally {
						rs.close();
					}
				} catch (Exception e) {
				} finally {
					stmt.close();
				}
			} catch (Exception e) {
			} finally {
				con.close();
			}
		} catch (Exception e) {
		}
		return result;
	}
}

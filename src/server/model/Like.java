package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class Like.
 */
public class Like {
	
	/** The uid. */
	private int uid;
	
	/** The bid. */
	private int bid;
	
	/** The b name. */
	private String bName;
	
	/**
	 * Instantiates a new like.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	public Like(ResultSet rs) throws SQLException {
		this.bid=rs.getInt("bid");
		this.uid=rs.getInt("uid");
		this.bName=rs.getString("name");
	}
	
	/**
	 * Adds the like to DB.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int addLikeToDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a like to DB");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.INSERT_NEW_LIKE);
			int i = 1;
			stmt.setInt(i++, this.bid);
			stmt.setInt(i++, this.uid);
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("Like added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;
		
	}
	
	/**
	 * Delete like at DB.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int deleteLikeAtDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Removing like");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.DELETE_LIKE);
			int i = 1;
			stmt.setInt(i++, this.bid);
			stmt.setInt(i++, this.uid);
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("like deleted, uid:" + uid + " and bid:"+bid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;
	}

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uID the new uid
	 */
	public void setUid(int uID) {
		this.uid = uID;
	}

	/**
	 * Gets the bid.
	 *
	 * @return the bid
	 */
	public int getBid() {
		return bid;
	}

	/**
	 * Sets the bid.
	 *
	 * @param bID the new bid
	 */
	public void setBid(int bID) {
		this.bid = bID;
	}

	/**
	 * Gets the b name.
	 *
	 * @return the b name
	 */
	public String getbName() {
		return bName;
	}

	/**
	 * Sets the b name.
	 *
	 * @param bName the new b name
	 */
	public void setbName(String bName) {
		this.bName = bName;
	}
}

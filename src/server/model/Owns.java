package server.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class Owns.
 */
public class Owns implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uid. */
	private int uid;
	
	/** The bid. */
	private int bid;
	
	/** The date of. */
	private String dateOf;

	/**
	 * Instantiates a new owns.
	 */
	// Dummy constructor for json parsing
	public Owns() {};
	
	/**
	 * Instantiates a new owns.
	 *
	 * @param uId the u id
	 * @param bId the b id
	 * @param dAteOf the d ate of
	 */
	public Owns(int uId, int bId, String dAteOf) {
		this.uid = uId;
		this.bid = bId;
		this.dateOf = dAteOf;
	}

	/**
	 * Instantiates a new owns.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	public Owns(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("bid");
		this.dateOf = rs.getTimestamp("dateof").toString();
	}

	/**
	 * Adds the to DB.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int addToDB()  throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a own to DB");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.INSERT_NEW_OWN);
			int i = 1;
			stmt.setInt(i++, this.uid);
			stmt.setInt(i++, this.bid);
			/* parsing javascript timestamp to derby timestamp */
			String c = this.dateOf.replace("T", " ");
			c=c.substring(0, 19);
			/*LocalDateTime parseHelp = LocalDateTime.parse(c);*/
			stmt.setTimestamp(i++, Timestamp.valueOf(c));
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("Book added");
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
	 * @param uid the new uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
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
	 * @param bid the new bid
	 */
	public void setBid(int bid) {
		this.bid = bid;
	}

	/**
	 * Gets the date of.
	 *
	 * @return the date of
	 */
	public String getDateOf() {
		return dateOf;
	}

	/**
	 * Sets the date of.
	 *
	 * @param dateOf the new date of
	 */
	public void setDateOf(String dateOf) {
		this.dateOf = dateOf;
	}
}

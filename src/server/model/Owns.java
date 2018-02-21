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

public class Owns implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private int bid;
	private String dateOf;

	// Dummy constructor for json parsing
	public Owns() {};
	
	public Owns(int uId, int bId, String dAteOf) {
		this.uid = uId;
		this.bid = bId;
		this.dateOf = dAteOf;
	}

	public Owns(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("bid");
		this.dateOf = rs.getTimestamp("dateof").toString();
	}

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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getDateOf() {
		return dateOf;
	}

	public void setDateOf(String dateOf) {
		this.dateOf = dateOf;
	}
}

package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Like {
	private int uid;
	private int bid;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uID) {
		this.uid = uID;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bID) {
		this.bid = bID;
	}
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
			System.out.println("Book added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;
		
	}
}

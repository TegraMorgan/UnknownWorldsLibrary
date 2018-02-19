package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Like {
	private int uID;
	private int bID;
	
	public int getUid() {
		return uID;
	}
	public void setUid(int uID) {
		this.uID = uID;
	}
	public int getBid() {
		return bID;
	}
	public void setBid(int bID) {
		this.bID = bID;
	}
	public int addLikeToDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a like to DB");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.INSERT_NEW_LIKE);
			int i = 1;
			stmt.setInt(i++, this.bID);
			stmt.setInt(i++, this.uID);
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
		return this.bID;
		
	}
	public int deleteLikeAtDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a like to DB");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.DELETE_LIKE_BY_UID);
			int i = 1;
			stmt.setInt(i++, this.bID);
			stmt.setInt(i++, this.uID);
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("like deleted, uid:" + uID + " and bid:"+bID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bID;
	}
}

package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Like {
	private int uid;
	private int bid;
	
	
	public Like(ResultSet rs) throws SQLException {
		this.bid=rs.getInt("bid");
		this.uid=rs.getInt("uid");
	}
	
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
	public int deleteLikeAtDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a like to DB");
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
}

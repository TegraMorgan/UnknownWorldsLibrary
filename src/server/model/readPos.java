package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class readPos {
	int uid;
	int bid;
	long position;

	
	public readPos(int uid, int bid, long position) {
		this.uid = uid;
		this.bid = bid;
		this.position = position;
	}
	
	public readPos(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("Bid");
		this.position = rs.getLong("position");
	}
	
	/// getters and setters
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
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
}

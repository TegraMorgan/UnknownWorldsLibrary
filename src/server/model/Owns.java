package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Owns {
	private int uid;
	private int bid;
	private Timestamp dateOf;

	// Dummy constructor for json parsing
	public Owns() {};
	
	public Owns(int uId, int bId, Timestamp dAteOf) {
		this.uid = uId;
		this.bid = bId;
		this.dateOf = dAteOf;
	}

	public Owns(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("bid");
		this.dateOf = rs.getTimestamp("dateof");
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

	public Timestamp getDateOf() {
		return dateOf;
	}

	public void setDateOf(Timestamp dateOf) {
		this.dateOf = dateOf;
	}
}

package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class readPos.
 */
public class readPos {
	
	/** The uid. */
	int uid;
	
	/** The bid. */
	int bid;
	
	/** The position. */
	long position;

	
	/**
	 * Instantiates a new read pos.
	 *
	 * @param uid the uid
	 * @param bid the bid
	 * @param position the position
	 */
	public readPos(int uid, int bid, long position) {
		this.uid = uid;
		this.bid = bid;
		this.position = position;
	}
	
	/**
	 * Instantiates a new read pos.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	public readPos(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("Bid");
		this.position = rs.getLong("position");
	}
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	/// getters and setters
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
	 * Gets the position.
	 *
	 * @return the position
	 */
	public long getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(long position) {
		this.position = position;
	}
}

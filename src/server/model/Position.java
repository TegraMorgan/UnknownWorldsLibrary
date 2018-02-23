package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class Position.
 */
public class Position {

/** The bid. */
private int bid;

/** The uid. */
private int uid;

/** The position. */
private long position;

/**
 * Instantiates a new position.
 */
public Position() {};

/**
 * Instantiates a new position.
 *
 * @param rs the rs
 * @throws SQLException the SQL exception
 */
public Position(ResultSet rs) throws SQLException {
	this.bid=rs.getInt("bid");
	this.uid=rs.getInt("uid");
	this.position=rs.getLong("position");
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
};

}

package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Position {
private int bid;
private int uid;
private long position;

public Position() {};

public Position(ResultSet rs) throws SQLException {
	this.bid=rs.getInt("bid");
	this.uid=rs.getInt("uid");
	this.position=rs.getLong("position");
}

public int getBid() {
	return bid;
}

public void setBid(int bid) {
	this.bid = bid;
}

public int getUid() {
	return uid;
}

public void setUid(int uid) {
	this.uid = uid;
}

public long getPosition() {
	return position;
}

public void setPosition(long position) {
	this.position = position;
};

}

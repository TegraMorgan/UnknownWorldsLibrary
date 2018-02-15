package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {
private int uid;
private int bid;
private String nickname;
private String bookName;
private int approvedBy;

public Review(int uId,int bId,String nIckname, String bOokName,int aPprovedBy) {
	this.uid=uId;
	this.bid=bId;
	this.nickname=nIckname;
	this.bookName=bOokName;
	this.approvedBy=aPprovedBy;
}

public Review(ResultSet rs) throws SQLException {
	this.uid=rs.getInt("uid");
	this.bid=rs.getInt("bid");
	this.nickname=rs.getString("nickname");
	this.bookName=rs.getString("book_name");
	this.approvedBy=rs.getInt("approved_by");
}
}

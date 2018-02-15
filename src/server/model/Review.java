package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {
private int uid;
private int bid;
private String nickname;
private String bookName;
private int approvedBy;
private String reviewBody;

public Review(int uId,int bId,String nIckname, String bOokName,int aPprovedBy,String rEviewBody) {
	this.uid=uId;
	this.bid=bId;
	this.nickname=nIckname;
	this.bookName=bOokName;
	this.approvedBy=aPprovedBy;
	this.reviewBody=rEviewBody;
}

public Review(ResultSet rs) throws SQLException {
	this.uid=rs.getInt("uid");
	this.bid=rs.getInt("bid");
	this.nickname=rs.getString("nickname");
	this.bookName=rs.getString("book_name");
	this.approvedBy=rs.getInt("approved_by");
	this.reviewBody=rs.getString("text");
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

public String getNickname() {
	return nickname;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}

public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
}

public int getApprovedBy() {
	return approvedBy;
}

public void setApprovedBy(int approvedBy) {
	this.approvedBy = approvedBy;
}

public String getReviewBody() {
	return reviewBody;
}

public void setReviewBody(String reviewBody) {
	this.reviewBody = reviewBody;
}
}

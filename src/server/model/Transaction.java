package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transaction {
private Timestamp dateOf;
private String nickname;
private String bookName;
private double price;

public Transaction() {};

public Transaction(ResultSet rs) throws SQLException {
	this.dateOf=rs.getTimestamp("dateof");
	this.nickname=rs.getString("nickname");
	this.bookName=rs.getString("book_name");
	this.price=rs.getDouble("price");
}

public Timestamp getDateOf() {
	return dateOf;
}

public void setDateOf(Timestamp dateOf) {
	this.dateOf = dateOf;
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

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}
}

package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The Class Transaction.
 */
public class Transaction {

/** The date of. */
private Timestamp dateOf;

/** The nickname. */
private String nickname;

/** The book name. */
private String bookName;

/** The price. */
private double price;

/**
 * Instantiates a new transaction.
 */
public Transaction() {};

/**
 * Instantiates a new transaction.
 *
 * @param rs the rs
 * @throws SQLException the SQL exception
 */
public Transaction(ResultSet rs) throws SQLException {
	this.dateOf=rs.getTimestamp("dateof");
	this.nickname=rs.getString("nickname");
	this.bookName=rs.getString("book_name");
	this.price=rs.getDouble("price");
}

/**
 * Gets the date of.
 *
 * @return the date of
 */
public Timestamp getDateOf() {
	return dateOf;
}

/**
 * Sets the date of.
 *
 * @param dateOf the new date of
 */
public void setDateOf(Timestamp dateOf) {
	this.dateOf = dateOf;
}

/**
 * Gets the nickname.
 *
 * @return the nickname
 */
public String getNickname() {
	return nickname;
}

/**
 * Sets the nickname.
 *
 * @param nickname the new nickname
 */
public void setNickname(String nickname) {
	this.nickname = nickname;
}

/**
 * Gets the book name.
 *
 * @return the book name
 */
public String getBookName() {
	return bookName;
}

/**
 * Sets the book name.
 *
 * @param bookName the new book name
 */
public void setBookName(String bookName) {
	this.bookName = bookName;
}

/**
 * Gets the price.
 *
 * @return the price
 */
public double getPrice() {
	return price;
}

/**
 * Sets the price.
 *
 * @param price the new price
 */
public void setPrice(double price) {
	this.price = price;
}
}

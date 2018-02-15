package server.utils;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

public interface ApplicationConstants {

	public final String DB_CONTEXT = "java:comp/env/jdbc/ExampleDatasource";
	public final String FILE_FORMAT = ".json";

	//derby constants
		public final String DB_NAME = "DB_NAME";
		public final String DB_DATASOURCE = "DB_DATASOURCE";
		public final String PROTOCOL = "jdbc:derby:"; 
		public final String OPEN = "Open";
		public final String SHUTDOWN = "Shutdown";

		//sql statements
		public final String CREATE_ADMIN_TABLE = "CREATE TABLE admins (aid int NOT NULL,login varchar(30) DEFAULT NULL,password varchar(45) DEFAULT NULL,PRIMARY KEY (aid));";
		public final String CREATE_BOOK_TABLE = "CREATE TABLE books (bid int NOT NULL,name varchar(255) DEFAULT NULL,author varchar(45) DEFAULT NULL,genre varchar(45) DEFAULT NULL,image_url varchar(255) DEFAULT NULL,price double DEFAULT NULL,description varchar(500) DEFAULT NULL,filepath varchar(500) DEFAULT NULL,PRIMARY KEY (bid));";
		public final String CREATE_CUSTOMER_TABLE = "CREATE TABLE customers (uid int NOT NULL,username varchar(11) DEFAULT NULL,email varchar(40) DEFAULT NULL,phone varchar(20) DEFAULT NULL,password varchar(100) DEFAULT NULL,nickname varchar(20) DEFAULT NULL,description varchar(255) DEFAULT NULL,photo_url varchar(255) DEFAULT NULL,PRIMARY KEY (uid));";
		public final String CREATE_LIKES_TABLE = "CREATE TABLE likes (bid int NOT NULL,uid int NOT NULL,PRIMARY KEY (bid,uid));";
		public final String CREATE_OWNS_TABLE = "CREATE TABLE owns (uid int NOT NULL,bid int NOT NULL,dateof timestamp DEFAULT NULL,PRIMARY KEY (uid,bid));";
		public final String CREATE_REVIEWS_TABLE = "CREATE TABLE reviews (bid int NOT NULL,uid int NOT NULL,text varchar(500) DEFAULT NULL,approved_by int DEFAULT NULL,PRIMARY KEY (bid,uid));";
		
		public final String INSERT_NEW_CUSTOMER = "INSERT INTO customers VALUES (default,?,?,?,?,?,?,?)";
		public final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE username=?";
		public final String FIND_CUSTOMER_BY_USERNAME_AND_PASS = "SELECT * FROM customers WHERE username=? AND password=?";
		public final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE uid=?";
		public final String FIND_CUSTOMER_BY_NICKNAME = "SELECT * FROM customers WHERE nickname=?";
		public final String UPDATE_CUSTOMER_BY_ID = "UPDATE customers SET username=?, email=?, phone=?,password=?, nickname=?, description=?,photo_url=? WHERE uid=?;";
		
		public final String GET_ALL_BOOKS = "SELECT * FROM books";
		public final String GET_ALL_LIKES = "SELECT l.bid as bid, c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid ORDER BY l.bid";
		public final String GET_ALL_APPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.bid=c.bid ORDER BY r.bid WHERE approved_by IS NOT NULL";
		public final String GET_ALL_UNAPPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.bid=c.bid ORDER BY r.bid WHERE approved_by IS NULL"; 
		
		public final String GET_BOOK_BY_ID ="SELECT * FROM books WHERE bid=?";
		public final String GET_LIKES_FOR_BOOK ="SELECT c.nickname as nickname FROM likes l INNER JOIN customers c ON l.bid=c.bid WHERE l.bid=?";
		public final String GET_REVIEWS_FOR_BOOK ="SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.bid=c.bid WHERE r.bid=? AND r.approved_by NOT NULL";
		
}
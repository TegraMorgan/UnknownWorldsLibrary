package server.utils;

public interface ApplicationConstants {

	
	

		//derby constants
		public final String DB_CONTEXT = "java:comp/env/jdbc/ExampleDatasource";
		public final String DB_NAME = "DB_NAME";
		public final String DB_DATASOURCE = "DB_DATASOURCE";
		public final String PROTOCOL = "jdbc:derby:"; 
		public final String OPEN = "Open";
		public final String SHUTDOWN = "Shutdown";

		// files constants
		public final String FILE_FORMAT = ".json";
		public final String CUSTOMERS = "customers";
		public final String ADMINS = "admins";
		public final String BOOKS = "books";
		public final String LIKES = "likes";
		public final String OWNS = "owns";
		public final String REVIEWS = "reviews";
		public final String ADMINS_FILE = ADMINS + FILE_FORMAT;
		public final String CUSTOMERS_FILE = CUSTOMERS + FILE_FORMAT;
		public final String BOOKS_FILE = BOOKS + FILE_FORMAT;
		public final String LIKES_FILE = LIKES + FILE_FORMAT;
		public final String OWNS_FILE = OWNS + FILE_FORMAT;
		public final String REVIEWS_FILE = REVIEWS + FILE_FORMAT;
		//sql statements
		public final String CREATE_ADMIN_TABLE = "CREATE TABLE admins (aid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),login varchar(30) DEFAULT NULL,password varchar(45) DEFAULT NULL,PRIMARY KEY (aid))";
		public final String CREATE_BOOK_TABLE = "CREATE TABLE books (bid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(255) DEFAULT NULL,author varchar(45) DEFAULT NULL,genre varchar(45) DEFAULT NULL,image_url varchar(255) DEFAULT NULL,price double DEFAULT NULL,description varchar(1000) DEFAULT NULL,filepath varchar(500) DEFAULT NULL,PRIMARY KEY (bid))";
		public final String CREATE_CUSTOMER_TABLE = "CREATE TABLE customers (uid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),username varchar(11) DEFAULT NULL,email varchar(40) DEFAULT NULL,phone varchar(20) DEFAULT NULL,password varchar(100) DEFAULT NULL,nickname varchar(20) DEFAULT NULL,description varchar(255) DEFAULT NULL,photo_url varchar(255) DEFAULT NULL,PRIMARY KEY (uid))";
		public final String CREATE_LIKES_TABLE = "CREATE TABLE likes (bid int NOT NULL,uid int NOT NULL,PRIMARY KEY (bid,uid))";
		public final String CREATE_OWNS_TABLE = "CREATE TABLE owns (uid int NOT NULL,bid int NOT NULL,dateof timestamp DEFAULT NULL,PRIMARY KEY (uid,bid))";
		public final String CREATE_REVIEWS_TABLE = "CREATE TABLE reviews (bid int NOT NULL,uid int NOT NULL,text varchar(500) DEFAULT NULL,approved_by int DEFAULT NULL,PRIMARY KEY (bid,uid))";
		
		public final String GET_ALL_Customers = "SELECT * FROM customers";
		public final String INSERT_NEW_CUSTOMER = "INSERT INTO customers VALUES (default,?,?,?,?,?,?,?)";
		public final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE username=?";
		public final String FIND_CUSTOMER_BY_USERNAME_AND_PASS = "SELECT * FROM customers WHERE username=? AND password=?";
		public final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE uid=?";
		public final String FIND_CUSTOMER_BOOKS = "SELECT o.bid as bid, o.uid as uid, o.dateof as dateof FROM owns o WHERE o.uid=?";
		public final String FIND_CUSTOMER_BY_NICKNAME = "SELECT * FROM customers WHERE nickname=?";
		public final String UPDATE_CUSTOMER_BY_ID = "UPDATE customers SET username=?, email=?, phone=?,password=?, nickname=?, description=?,photo_url=? WHERE uid=?";
		public final String INSERT_NEW_ADMIN = "INSERT INTO admins VALUES (default,?,?)";
		
		public final String INSERT_BOOK = "INSERT INTO books VALUES (default,?,?,?,?,?,?,?)";
		public final String GET_ALL_BOOKS = "SELECT * FROM books";
		public final String GET_ALL_LIKES = "SELECT l.bid as bid, c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid ORDER BY l.bid";
		public final String GET_ALL_APPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY is not null ORDER BY r.bid";
		public final String GET_ALL_UNAPPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY IS NULL ORDER BY r.bid"; 
		
		public final String GET_BOOK_BY_ID ="SELECT * FROM books WHERE bid=?";
		public final String GET_LIKES_FOR_BOOK ="SELECT c.nickname as nickname FROM likes l INNER JOIN customers c ON l.bid=c.bid WHERE l.bid=?";
		public final String GET_REVIEWS_FOR_BOOK ="SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.bid=? AND r.approved_by IS NOT NULL";
		
		public final String INSERT_NEW_LIKE = "INSERT INTO likes VALUES (?,?)";
		public final String DELETE_LIKE = "DELETE FROM likes WHERE bid=? AND uid=?";
		public final String SELECT_LIKES_BY_UID = "SELECT * FROM likes WHERE uid=?";
		public final String SELECT_LIKES_BY_BID = "SELECT * FROM likes WHERE bid=?";
		
		public final String INSERT_NEW_REVIEW = "INSERT INTO reviews VALUES (?,?,?,?)";
		public final String SELECT_REVIEWS_BY_UID = "SELECT r.bid as bid, r.uid as uid, r.text as text, r.approved_by as approved_by, c.nickname as nickname,  b.name as book_name FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.uid=?";
		public final String UPDATE_REVIEW_BY_BID_AND_UID = "UPDATE reviews SET approved_by=?  WHERE bid=? AND uid=?";
		
		public final String INSERT_NEW_OWN = "INSERT INTO owns VALUES (?,?,?)";
		public final String SELECT_OWNS_BY_UID = "SELECT * FROM owns WHERE uid=?";
		
		public final String FIND_ADMIN_BY_LOGIN_AND_PASS = "SELECT * FROM admins WHERE login=? AND password=?";
		public final String DELETE_Customer = "DELETE FROM customers WHERE uid=?";

}
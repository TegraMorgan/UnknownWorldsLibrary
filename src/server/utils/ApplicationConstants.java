package server.utils;

/**
 * The Interface ApplicationConstants.
 */
public interface ApplicationConstants {

	
	

		/** The db context. */
		//derby constants
		public final String DB_CONTEXT = "java:comp/env/jdbc/ExampleDatasource";
		
		/** The db name. */
		public final String DB_NAME = "DB_NAME";
		
		/** The db datasource. */
		public final String DB_DATASOURCE = "DB_DATASOURCE";
		
		/** The protocol. */
		public final String PROTOCOL = "jdbc:derby:"; 
		
		/** The open. */
		public final String OPEN = "Open";
		
		/** The shutdown. */
		public final String SHUTDOWN = "Shutdown";

		/** The file format. */
		// files constants
		public final String FILE_FORMAT = ".json";
		
		/** The customers. */
		public final String CUSTOMERS = "customers";
		
		/** The admins. */
		public final String ADMINS = "admins";
		
		/** The books. */
		public final String BOOKS = "books";
		
		/** The likes. */
		public final String LIKES = "likes";
		
		/** The owns. */
		public final String OWNS = "owns";
		
		/** The reviews. */
		public final String REVIEWS = "reviews";
		
		/** The admins file. */
		public final String ADMINS_FILE = ADMINS + FILE_FORMAT;
		
		/** The customers file. */
		public final String CUSTOMERS_FILE = CUSTOMERS + FILE_FORMAT;
		
		/** The books file. */
		public final String BOOKS_FILE = BOOKS + FILE_FORMAT;
		
		/** The likes file. */
		public final String LIKES_FILE = LIKES + FILE_FORMAT;
		
		/** The owns file. */
		public final String OWNS_FILE = OWNS + FILE_FORMAT;
		
		/** The reviews file. */
		public final String REVIEWS_FILE = REVIEWS + FILE_FORMAT;
		
		/** The create admin table. */
		//sql statements
		public final String CREATE_ADMIN_TABLE = "CREATE TABLE admins (aid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),login varchar(30) DEFAULT NULL,password varchar(45) DEFAULT NULL,PRIMARY KEY (aid))";
		
		/** The create book table. */
		public final String CREATE_BOOK_TABLE = "CREATE TABLE books (bid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(255) DEFAULT NULL,author varchar(45) DEFAULT NULL,genre varchar(45) DEFAULT NULL,image_url varchar(255) DEFAULT NULL,price double DEFAULT NULL,description varchar(1000) DEFAULT NULL,filepath varchar(500) DEFAULT NULL,PRIMARY KEY (bid))";
		
		/** The create customer table. */
		public final String CREATE_CUSTOMER_TABLE = "CREATE TABLE customers (uid int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),username varchar(11) DEFAULT NULL,email varchar(40) DEFAULT NULL,phone varchar(20) DEFAULT NULL,password varchar(100) DEFAULT NULL,nickname varchar(20) DEFAULT NULL,description varchar(255) DEFAULT NULL,photo_url varchar(255) DEFAULT 'img/Users/Default.gif',st_name varchar(255) DEFAULT NULL,bl_num int DEFAULT NULL,city_nm varchar(255) DEFAULT NULL,zip varchar(7) DEFAULT NULL,PRIMARY KEY (uid))";
		
		/** The create likes table. */
		public final String CREATE_LIKES_TABLE = "CREATE TABLE likes (bid int NOT NULL,uid int NOT NULL,PRIMARY KEY (bid,uid))";
		
		/** The create owns table. */
		public final String CREATE_OWNS_TABLE = "CREATE TABLE owns (uid int NOT NULL,bid int NOT NULL,dateof timestamp DEFAULT NULL,PRIMARY KEY (uid,bid))";
		
		/** The create reviews table. */
		public final String CREATE_REVIEWS_TABLE = "CREATE TABLE reviews (bid int NOT NULL,uid int NOT NULL,text varchar(500) DEFAULT NULL,approved_by int DEFAULT NULL,PRIMARY KEY (bid,uid))";
		
		/** The create readpos table. */
		public final String CREATE_READPOS_TABLE = "CREATE TABLE readPos (bid int NOT NULL,uid BIGINT NOT NULL,position int NOT NULL, PRIMARY KEY (bid,uid))";
		
		/** The GE T AL L customers. */
		public final String GET_ALL_Customers = "SELECT * FROM customers";
		
		/** The insert new customer. */
		public final String INSERT_NEW_CUSTOMER = "INSERT INTO customers VALUES (default,?,?,?,?,?,?,?,?,?,?,?)";
		
		/** The find customer by username. */
		public final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE username=?";
		
		/** The find customer by username and pass. */
		public final String FIND_CUSTOMER_BY_USERNAME_AND_PASS = "SELECT * FROM customers WHERE username=? AND password=?";
		
		/** The find customer by id. */
		public final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE uid=?";
		
		/** The find customer books. */
		public final String FIND_CUSTOMER_BOOKS = "SELECT o.bid as bid, o.uid as uid, o.dateof as dateof FROM owns o WHERE o.uid=?";
		
		/** The find customer by nickname. */
		public final String FIND_CUSTOMER_BY_NICKNAME = "SELECT * FROM customers WHERE nickname=?";
		
		/** The find customer by email. */
		public final String FIND_CUSTOMER_BY_EMAIL = "SELECT * FROM customers WHERE email=?";
		
		/** The insert new admin. */
		public final String INSERT_NEW_ADMIN = "INSERT INTO admins VALUES (default,?,?)";
		
		/** The insert book. */
		public final String INSERT_BOOK = "INSERT INTO books VALUES (default,?,?,?,?,?,?,?)";
		
		/** The get all books. */
		public final String GET_ALL_BOOKS = "SELECT * FROM books";
		
		/** The get all likes. */
		public final String GET_ALL_LIKES = "SELECT l.bid as bid, c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid ORDER BY l.bid";
		
		/** The get all approved reviews. */
		public final String GET_ALL_APPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY is not null ORDER BY r.bid";
		
		/** The get all unapproved reviews. */
		public final String GET_ALL_UNAPPROVED_REVIEWS = "SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY IS NULL ORDER BY r.bid"; 
		
		/** The get book by id. */
		public final String GET_BOOK_BY_ID ="SELECT * FROM books WHERE bid=?";
		
		/** The get likes for book. */
		public final String GET_LIKES_FOR_BOOK ="SELECT c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid WHERE l.bid=?";
		
		/** The get reviews for book. */
		public final String GET_REVIEWS_FOR_BOOK ="SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.bid=? AND r.approved_by IS NOT NULL";
		
		/** The insert new like. */
		public final String INSERT_NEW_LIKE = "INSERT INTO likes VALUES (?,?)";
		
		/** The delete like. */
		public final String DELETE_LIKE = "DELETE FROM likes WHERE bid=? AND uid=?";
		
		/** The select likes by uid. */
		public final String SELECT_LIKES_BY_UID = "SELECT l.bid as bid, l.uid as uid, b.name as name FROM likes l INNER JOIN books b ON l.bid=b.bid WHERE uid=?";
		
		/** The select likes by bid. */
		public final String SELECT_LIKES_BY_BID = "SELECT l.bid as bid, l.uid as uid, b.name as name FROM likes l INNER JOIN books b ON l.bid=b.bid FROM likes WHERE bid=?";
		
		/** The insert new review. */
		public final String INSERT_NEW_REVIEW = "INSERT INTO reviews VALUES (?,?,?,?)";
		
		/** The select reviews by uid. */
		public final String SELECT_REVIEWS_BY_UID = "SELECT r.bid as bid, r.uid as uid, r.text as text, r.approved_by as approved_by, c.nickname as nickname,  b.name as book_name FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.uid=?";
		
		/** The update review by bid and uid. */
		public final String UPDATE_REVIEW_BY_BID_AND_UID = "UPDATE reviews SET approved_by=?  WHERE bid=? AND uid=?";
		
		/** The delete review by bid and uid. */
		public final String DELETE_REVIEW_BY_BID_AND_UID = "DELETE FROM reviews WHERE bid=? AND uid=?";
		
		/** The insert new own. */
		public final String INSERT_NEW_OWN = "INSERT INTO owns VALUES (?,?,?)";
		
		/** The select owns by uid. */
		public final String SELECT_OWNS_BY_UID = "SELECT * FROM owns WHERE uid=?";
		
		/** The find admin by login and pass. */
		public final String FIND_ADMIN_BY_LOGIN_AND_PASS = "SELECT * FROM admins WHERE login=? AND password=?";
		
		/** The delete customer. */
		public final String DELETE_CUSTOMER = "DELETE FROM customers WHERE uid=?";
		
		/** The delete all customer likes. */
		public final String DELETE_ALL_CUSTOMER_LIKES = "DELETE FROM likes WHERE uid=?";
		
		/** The delete all customer reviews. */
		public final String DELETE_ALL_CUSTOMER_REVIEWS = "DELETE FROM reviews WHERE uid=?";
		
		/** The get transactions. */
		public final String GET_TRANSACTIONS = "SELECT o.dateof as dateof, c.nickname as nickname, b.name as book_name, b.price as price FROM owns o INNER JOIN customers c ON o.uid=c.uid INNER JOIN books b ON o.bid=b.bid ORDER BY o.dateof desc";
		
		/** The get all readers positions. */
		public final String GET_ALL_READERS_POSITIONS = "SELECT * FROM readPos";
		
		/** The find readpos by uid bid. */
		public final String FIND_READPOS_BY_UID_BID = "SELECT r.bid as bid, r.uid as uid, r.position as position FROM readPos r WHERE r.bid=? AND r.uid=?";
		
		/** The insert new readpos. */
		public final String INSERT_NEW_READPOS = "INSERT INTO readPos VALUES (?,?,?)";
		
		/** The update readpos by bid and uid. */
		public final String UPDATE_READPOS_BY_BID_AND_UID = "UPDATE readPos SET position=? WHERE bid=? AND uid=? ";
		
		/** The delete readpos. */
		public final String DELETE_READPOS = "DELETE FROM readPos WHERE bid=? uid=?";
}
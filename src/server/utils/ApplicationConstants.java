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
		public final String CREATE_ADMIN_TABLE = "CREATE TABLE admin (aid int NOT NULL,login varchar(30) DEFAULT NULL,password varchar(45) DEFAULT NULL,PRIMARY KEY (aid));";
		public final String CREATE_BOOK_TABLE = "CREATE TABLE book (bid int NOT NULL,name varchar(255) DEFAULT NULL,author varchar(45) DEFAULT NULL,genre varchar(45) DEFAULT NULL,image_url varchar(255) DEFAULT NULL,price double DEFAULT NULL,description varchar(500) DEFAULT NULL,filepath varchar(500) DEFAULT NULL,PRIMARY KEY (bid));";
		public final String CREATE_CUSTOMER_TABLE = "CREATE TABLE customer (uid int NOT NULL,username varchar(11) DEFAULT NULL,email varchar(40) DEFAULT NULL,phone varchar(20) DEFAULT NULL,password varchar(100) DEFAULT NULL,nickname varchar(20) DEFAULT NULL,description varchar(255) DEFAULT NULL,photo_url varchar(255) DEFAULT NULL,PRIMARY KEY (uid));";
		public final String CREATE_LIKES_TABLE = "CREATE TABLE likes (bid int NOT NULL,uid int NOT NULL,PRIMARY KEY (bid,uid));";
		public final String CREATE_OWNS_TABLE = "CREATE TABLE owns (uid int NOT NULL,bid int NOT NULL,dateof timestamp DEFAULT NULL,PRIMARY KEY (uid,bid));";
		public final String CREATE_REVIEWS_TABLE = "CREATE TABLE reviews (bid int NOT NULL,uid int NOT NULL,text varchar(500) DEFAULT NULL,approved_by int DEFAULT NULL,PRIMARY KEY (bid,uid));";
		
		/*uid int 
		username varchar(11)
		email varchar(40)
		phone varchar(20) 
		password varchar(100) 
		nickname varchar(20) 
		description varchar(255) 
		photo_url varchar*/
		
		public final String INSERT_NEW_CUSTOMER = "INSERT INTO customer VALUES (default,?,?,?,?,?,?,?)";
		public final String FIND_CUSTOMER_BY_USERNAME_AND_PASS = "SELECT * FROM customer WHERE username=? AND password=?";
		public final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE uid=?";
		public final String FIND_CUSTOMER_BY_NICKNAME = "SELECT * FROM customer WHERE nickname=?";
		public final String UPDATE_CUSTOMER_BY_ID = "UPDATE customer SET username=?, email=?, phone=?,password=?, nickname=?, description=?,photo_url=? WHERE uid=?;";
		
		
}
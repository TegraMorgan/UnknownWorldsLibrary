
--
-- Table structure for table `admins`
--

CREATE TABLE admins (aid int GENERATED 
 ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) 
 ,login varchar(30) DEFAULT NULL,
 password varchar(45) DEFAULT NULL,PRIMARY KEY (aid))

--
-- Table structure for table `books`
--

CREATE TABLE books (bid int GENERATED 
 ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
 ,name varchar(255) DEFAULT NULL,author varchar(45) DEFAULT NULL
 ,genre varchar(45) DEFAULT NULL,image_url varchar(255) DEFAULT NULL
 ,price double DEFAULT NULL,description varchar(1000) DEFAULT NULL
 ,filepath varchar(500) DEFAULT NULL,PRIMARY KEY (bid))

--
-- Table structure for table `customers`
--

CREATE TABLE customers (uid int GENERATED ALWAYS AS IDENTITY 
 (START WITH 1, INCREMENT BY 1),username varchar(11) DEFAULT NULL
 ,email varchar(40) DEFAULT NULL,phone varchar(20) DEFAULT NULL
 ,password varchar(100) DEFAULT NULL,nickname varchar(20) DEFAULT NULL
 ,description varchar(255) DEFAULT NULL
 ,photo_url varchar(255) DEFAULT 'img/Users/Default.gif'
 ,st_name varchar(255) DEFAULT NULL,bl_num int DEFAULT NULL
 ,city_nm varchar(255) DEFAULT NULL,zip varchar(7) DEFAULT NULL,PRIMARY KEY (uid))

--
-- Table structure for table `likes`
--

CREATE TABLE likes (
  bid int NOT NULL,
  uid int NOT NULL,
  PRIMARY KEY (bid,uid)
);

--
-- Table structure for table `owns`
--

CREATE TABLE owns (uid int NOT NULL,bid int NOT NULL,
 dateof timestamp DEFAULT NULL,PRIMARY KEY (uid,bid))


--
-- Table structure for table `reviews`
--

CREATE TABLE reviews (bid int NOT NULL,
 uid int NOT NULL,text varchar(500) DEFAULT NULL,
 approved_by int DEFAULT NULL,PRIMARY KEY (bid,uid))


--
--Table structure for table `readpos`
--

CREATE TABLE readPos (bid int NOT NULL,
uid BIGINT NOT NULL,position int NOT NULL, 
PRIMARY KEY (bid,uid))

--
--Customer records manipulation
--

SELECT * FROM customers
INSERT INTO customers VALUES (default,?,?,?,?,?,?,?,?,?,?,?)

--When we check for duplicate username in db
SELECT * FROM customers WHERE username=?
-- Login function
SELECT * FROM customers WHERE username=? AND password=?
--When buying a book and retrieving detailed customer infomation
SELECT * FROM customers WHERE uid=?
--Selecting customers books to present in My Books section
SELECT o.bid as bid, o.uid as uid, o.dateof as dateof FROM owns o WHERE o.uid=?
-- When we need to click on the customer nickname to see his profile or to check that there is no duplicate nickname
SELECT * FROM customers WHERE nickname=?
--When we check for duplicate email
SELECT * FROM customers WHERE email=?

--This functions are used exlusevilely when population DB from json files for the first time
INSERT INTO admins VALUES (default,?,?)
INSERT INTO books VALUES (default,?,?,?,?,?,?,?)

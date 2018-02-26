
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
--INSERT_BOOK
INSERT INTO books VALUES (default,?,?,?,?,?,?,?)
--GET_ALL_BOOKS
SELECT * FROM books
--GET_ALL_LIKES
SELECT l.bid as bid, c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid ORDER BY l.bid
--GET_ALL_APPROVED_REVIEWS
SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY is not null ORDER BY r.bid
--GET_ALL_UNAPPROVED_REVIEWS
SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.APPROVED_BY IS NULL ORDER BY r.bid
--GET_BOOK_BY_ID 
SELECT * FROM books WHERE bid=?
--GET_LIKES_FOR_BOOK 
SELECT c.nickname as nickname FROM likes l INNER JOIN customers c ON l.uid=c.uid WHERE l.bid=?
--GET_REVIEWS_FOR_BOOK 
SELECT r.uid AS uid, r.bid AS bid, c.nickname AS nickname, b.name AS book_name, r.approved_by AS approved_by, r.text as text FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.bid=? AND r.approved_by IS NOT NULL
--INSERT_NEW_LIKE 
INSERT INTO likes VALUES (?,?)
--DELETE_LIKE 
DELETE FROM likes WHERE bid=? AND uid=?
--SELECT_LIKES_BY_UID 
SELECT l.bid as bid, l.uid as uid, b.name as name FROM likes l INNER JOIN books b ON l.bid=b.bid WHERE uid=?
--SELECT_LIKES_BY_BID 
SELECT l.bid as bid, l.uid as uid, b.name as name FROM likes l INNER JOIN books b ON l.bid=b.bid FROM likes WHERE bid=?
--INSERT_NEW_REVIEW 
INSERT INTO reviews VALUES (?,?,?,?)
--SELECT_REVIEWS_BY_UID 
SELECT r.bid as bid, r.uid as uid, r.text as text, r.approved_by as approved_by, c.nickname as nickname,  b.name as book_name FROM reviews r INNER JOIN books b ON r.bid=b.bid INNER JOIN customers c ON r.uid=c.uid WHERE r.uid=?
--UPDATE_REVIEW_BY_BID_AND_UID 
UPDATE reviews SET approved_by=?  WHERE bid=? AND uid=?
--DELETE_REVIEW_BY_BID_AND_UID 
DELETE FROM reviews WHERE bid=? AND uid=?
--INSERT_NEW_OWN 
INSERT INTO owns VALUES (?,?,?)
--SELECT_OWNS_BY_UID 
SELECT * FROM owns WHERE uid=?
--FIND_ADMIN_BY_LOGIN_AND_PASS 
SELECT * FROM admins WHERE login=? AND password=?
--DELETE_CUSTOMER 
DELETE FROM customers WHERE uid=?
--DELETE_ALL_CUSTOMER_LIKES 
DELETE FROM likes WHERE uid=?
--DELETE_ALL_CUSTOMER_REVIEWS 
DELETE FROM reviews WHERE uid=?
--GET_TRANSACTIONS 
SELECT o.dateof as dateof, c.nickname as nickname, b.name as book_name, b.price as price FROM owns o INNER JOIN customers c ON o.uid=c.uid INNER JOIN books b ON o.bid=b.bid ORDER BY o.dateof desc
--GET_ALL_READERS_POSITIONS 
SELECT * FROM readPos
--FIND_READPOS_BY_UID_BID 
SELECT r.bid as bid, r.uid as uid, r.position as position FROM readPos r WHERE r.bid=? AND r.uid=?
--INSERT_NEW_READPOS 
INSERT INTO readPos VALUES (?,?,?)
--UPDATE_READPOS_BY_BID_AND_UID 
UPDATE readPos SET position=? WHERE bid=? AND uid=?
--DELETE_READPOS 
DELETE FROM readPos WHERE bid=? uid=?

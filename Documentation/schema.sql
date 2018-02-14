
--
-- Table structure for table `admin`
--

CREATE TABLE admin (
  aid int NOT NULL,
  login varchar(30) DEFAULT NULL,
  password varchar(45) DEFAULT NULL,
  PRIMARY KEY (aid)
);

--
-- Table structure for table `book`
--

CREATE TABLE book (
  bid int NOT NULL,
  name varchar(255) DEFAULT NULL,
  author varchar(45) DEFAULT NULL,
  genre varchar(45) DEFAULT NULL,
  image_url varchar(255) DEFAULT NULL,
  price double DEFAULT NULL,
  description varchar(500) DEFAULT NULL,
  filepath varchar(500) DEFAULT NULL,
  PRIMARY KEY (bid)
);

--
-- Table structure for table `customer`
--

CREATE TABLE customer (
  uid int NOT NULL,
  username varchar(11) DEFAULT NULL,
  email varchar(40) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  nickname varchar(20) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  photo_url varchar(255) DEFAULT NULL,
  PRIMARY KEY (uid)
);

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

CREATE TABLE owns (
  uid int NOT NULL,
  bid int NOT NULL,
  dateof timestamp DEFAULT NULL,
  PRIMARY KEY (uid,bid)
);

--
-- Table structure for table `reviews`
--

CREATE TABLE reviews (
  bid int NOT NULL,
  uid int NOT NULL,
  text varchar(500) DEFAULT NULL,
  approved_by int DEFAULT NULL,
  PRIMARY KEY (bid,uid)
);

package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Book {
	private int bid;
	private String name;
	private String author;
	private String genre;
	private String imageUrl;
	private Double price;
	private String description;
	private String filepath;
	private LinkedList<String> likes;
	private LinkedList<Review> reviews;

	/*
	 * bid int NOT NULL, name varchar(255) DEFAULT NULL, author varchar(45) DEFAULT
	 * NULL, genre varchar(45) DEFAULT NULL, image_url varchar(255) DEFAULT NULL,
	 * price double DEFAULT NULL, description varchar(500) DEFAULT NULL, filepath
	 * varchar(500) DEFAULT NULL, PRIMARY KEY (bid)
	 */
	public Book(int bId, String nAme, String aUthor, String gEnre, String iMageUrl, Double pRice, String dEscription,
			String fIlepath) {
		this.bid = bId;
		this.name = nAme;
		this.author = aUthor;
		this.genre = gEnre;
		this.imageUrl = iMageUrl;
		this.price = pRice;
		this.description = dEscription;
		this.filepath = fIlepath;
		this.likes = this.GetMyLikes();
		this.reviews = this.GetMyApprovedReviews();
	}

	public Book(ResultSet rs) throws SQLException {
		this.bid = rs.getInt("bid");
		this.name = rs.getString("name");
		this.author = rs.getString("author");
		this.genre = rs.getString("genre");
		this.imageUrl = rs.getString("image_url");
		this.price = rs.getDouble("price");
		this.description = rs.getString("description");
		this.filepath = rs.getString("filepath");
		this.likes = this.GetMyLikes();
		this.reviews = this.GetMyApprovedReviews();
	}

	public Book(ResultSet rs, LinkedList<String> mYlikes, LinkedList<Review> mYreviews) throws SQLException {
		this.bid = rs.getInt("bid");
		this.name = rs.getString("name");
		this.author = rs.getString("author");
		this.genre = rs.getString("genre");
		this.imageUrl = rs.getString("image_url");
		this.price = rs.getDouble("price");
		this.description = rs.getString("description");
		this.filepath = rs.getString("filepath");
		this.likes = mYlikes;
		this.reviews = mYreviews;
	}

	private LinkedList<String> GetMyLikes() {
		LinkedList<String> result = new LinkedList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int i = 1;
		try {
			conn = (Connection) DataStructure.ds.getConnection();
			stmt = conn.prepareStatement(ApplicationConstants.GET_LIKES_FOR_BOOK);
			stmt.setInt(i++, this.bid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("nickname"));
			}
			rs.close();
			stmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private LinkedList<Review> GetMyApprovedReviews() {
		LinkedList<Review> result = new LinkedList<Review>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int i = 1;
		try {
			conn = (Connection) DataStructure.ds.getConnection();
			stmt = conn.prepareStatement(ApplicationConstants.GET_REVIEWS_FOR_BOOK);
			stmt.setInt(i++, this.bid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(new Review(rs));
			}
			rs.close();
			stmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public LinkedList<String> getLikes() {
		return likes;
	}

	public void setLikes(LinkedList<String> likes) {
		this.likes = likes;
	}

	public LinkedList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(LinkedList<Review> reviews) {
		this.reviews = reviews;
	}

	public int addBook() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a book");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.INSERT_BOOK);
			int i = 1;
			stmt.setString(i++, this.name);
			stmt.setString(i++, this.author);
			stmt.setString(i++, this.genre);
			stmt.setString(i++, this.imageUrl);
			stmt.setDouble(i++, this.price);
			stmt.setString(i++, this.description);
			stmt.setString(i++, this.filepath);
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("Book added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;
	}
}

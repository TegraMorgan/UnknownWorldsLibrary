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
	
	public Book(ResultSet rs,LinkedList<String> mYlikes, LinkedList<Review> mYreviews) throws SQLException {
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
			while(rs.next())
			{
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
	
	
	
	public static LinkedList<Book> GetAllBooks(){
		//TODO do this
		LinkedList<Book> result = new LinkedList<Book>();
		Connection conn = null;
		PreparedStatement bookStmt = null;
		PreparedStatement likesStmt = null;
		PreparedStatement reviewsStmt = null;
		int i = 1;
		try {
			conn = (Connection) DataStructure.ds.getConnection();
			bookStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_BOOKS);
			ResultSet bookSet = bookStmt.executeQuery();
			likesStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_LIKES);
			ResultSet likesSet = likesStmt.executeQuery();
			reviewsStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_APPROVED_REVIEWS);
			ResultSet reviewsSet = reviewsStmt.executeQuery();
			LinkedList<String> likedList = new LinkedList<String>();
			LinkedList<Review> reviewList = new LinkedList<Review>();
			while(bookSet.next())
			{
				likesSet.beforeFirst();
				reviewsSet.beforeFirst();
				likedList = filterLikes(bookSet.getInt("bid"), likesSet);
				reviewList = filterReviews(bookSet.getInt("bid"), reviewsSet);
				result.add(new Book(bookSet, likedList, reviewList));
			}
			bookSet.close();
			likesSet.close();
			reviewsSet.close();
			bookStmt.close();
			likesStmt.close();
			reviewsStmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static LinkedList<Review> filterReviews(int bId, ResultSet reviewsSet) throws SQLException {
		LinkedList<Review> result = new LinkedList<Review>();
		while(reviewsSet.next()) {
			if(reviewsSet.getInt("bid")==bId)
			{
				result.add(new Review(reviewsSet));
			}
		}
		return result;
	}

	private static LinkedList<String> filterLikes(int bId, ResultSet likesSet) throws SQLException {
		LinkedList<String> result = new LinkedList<String>();
		while(likesSet.next()) {
			if(likesSet.getInt("bid")==bId)
			{
				result.add(likesSet.getString("nickname"));
			}
		}
		return result;
	}
}

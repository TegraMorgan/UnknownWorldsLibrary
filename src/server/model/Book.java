package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class Book.
 */
public class Book {
	
	/** The bid. */
	private int bid;
	
	/** The name. */
	private String name;
	
	/** The author. */
	private String author;
	
	/** The genre. */
	private String genre;
	
	/** The image url. */
	private String imageUrl;
	
	/** The price. */
	private Double price;
	
	/** The description. */
	private String description;
	
	/** The filepath. */
	private String filepath;
	
	/** The likes. */
	private LinkedList<String> likes;
	
	/** The reviews. */
	private LinkedList<Review> reviews;

	/**
	 * Instantiates a new book.
	 *
	 * @param bId the b id
	 * @param nAme the n ame
	 * @param aUthor the a uthor
	 * @param gEnre the g enre
	 * @param iMageUrl the i mage url
	 * @param pRice the rice
	 * @param dEscription the d escription
	 * @param fIlepath the f ilepath
	 */
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

	/**
	 * Instantiates a new book.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
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

	/**
	 * Instantiates a new book.
	 *
	 * @param rs the rs
	 * @param mYlikes the m ylikes
	 * @param mYreviews the m yreviews
	 * @throws SQLException the SQL exception
	 */
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

	/**
	 * Gets the my likes.
	 *
	 * @return the linked list
	 */
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

	/**
	 * Gets the my approved reviews.
	 *
	 * @return the linked list
	 */
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

	/**
	 * Gets the bid.
	 *
	 * @return the bid
	 */
	public int getBid() {
		return bid;
	}

	/**
	 * Sets the bid.
	 *
	 * @param bid the new bid
	 */
	public void setBid(int bid) {
		this.bid = bid;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the genre.
	 *
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the genre.
	 *
	 * @param genre the new genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image url.
	 *
	 * @param imageUrl the new image url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the filepath.
	 *
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * Sets the filepath.
	 *
	 * @param filepath the new filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Gets the likes.
	 *
	 * @return the likes
	 */
	public LinkedList<String> getLikes() {
		return likes;
	}

	/**
	 * Sets the likes.
	 *
	 * @param likes the new likes
	 */
	public void setLikes(LinkedList<String> likes) {
		this.likes = likes;
	}

	/**
	 * Gets the reviews.
	 *
	 * @return the reviews
	 */
	public LinkedList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets the reviews.
	 *
	 * @param reviews the new reviews
	 */
	public void setReviews(LinkedList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Adds the book.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
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

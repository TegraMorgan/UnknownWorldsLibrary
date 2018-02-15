package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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
		this.likes = GetLikes(this.bid);
	}
	
	public Book(ResultSet rs) throws SQLException {
		this.bid=rs.getInt("bid");
		this.name=rs.getString("name");
		this.author=rs.getString("author");
		this.genre=rs.getString("genre");
		this.imageUrl=rs.getString("image_url");
		this.price=rs.getDouble("price");
		this.description=rs.getString("description");
		this.filepath=rs.getString("filepath");
	}
}

package server.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uid;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String nickname;
	private String description;
	private String photo_url;
	private ArrayList<Owns> owns;
	private ArrayList<Like> likes;
	private ArrayList<Review> reviews;

	public Customer(int uId, String userName, String eMail, String pHone, String pAssword, String nickName,
			String dEscription, String pHoto_url, ArrayList<Owns> oWns, ArrayList<Like> lIkes,
			ArrayList<Review> rEviews) {
		this.uid = uId;
		this.username = userName;
		this.email = eMail;
		this.phone = pHone;
		this.password = pAssword;
		this.nickname = nickName;
		this.description = dEscription;
		this.photo_url = pHoto_url;
		this.owns = oWns;
		this.likes = lIkes;
		this.reviews = rEviews;
	}

	public Customer(ResultSet rs, ArrayList<Owns> oWns, ArrayList<Like> lIkes, ArrayList<Review> rEviews)
			throws SQLException {
		this.uid = rs.getInt("uid");
		this.username = rs.getString("username");
		this.email = rs.getString("email");
		this.phone = rs.getString("phone");
		this.password = rs.getString("password");
		this.nickname = rs.getString("nickname");
		this.description = rs.getString("description");
		this.photo_url = rs.getString("photo_url");
		this.owns = oWns;
		this.likes = lIkes;
		this.reviews = rEviews;
	}

	public Customer(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.username = rs.getString("username");
		this.email = rs.getString("email");
		this.phone = rs.getString("phone");
		this.password = rs.getString("password");
		this.nickname = rs.getString("nickname");
		this.description = rs.getString("description");
		this.photo_url = rs.getString("photo_url");
		this.getMyBooks();
		this.getMyLikes();
		this.getMyreviews();
	}

	// get my books List from DB
	public void getMyBooks() {
		this.owns = new ArrayList<Owns>();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BOOKS);
			stmt.setInt(1, this.uid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				owns.add(new Owns(rs));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// get my likes from DB
	public void getMyLikes() {
		this.likes = new ArrayList<Like>();
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.SELECT_LIKES_BY_UID);
			Statement.setInt(1, uid);
			ResultSet resltset = Statement.executeQuery();
			while (resltset.next()) {
				likes.add(new Like(resltset));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get my reviews from DB
	public void getMyreviews() {
		this.reviews = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.SELECT_REVIEWS_BY_UID);
			Statement.setInt(1, uid);
			ResultSet resltset = Statement.executeQuery();
			while (resltset.next()) {
				this.reviews.add(new Review(resltset));
			}
			resltset.close();
			Statement.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// add this to the DB
	public int addCustomer() throws Exception {
		int st = 0;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		System.out.println("addCustomer 1");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			preparedStatement = con.prepareStatement(ApplicationConstants.INSERT_NEW_CUSTOMER);
			int i = 1;
			preparedStatement.setString(i++, this.username);
			preparedStatement.setString(i++, this.email);
			preparedStatement.setString(i++, this.phone);
			preparedStatement.setString(i++, this.password);
			preparedStatement.setString(i++, this.nickname);
			preparedStatement.setString(i++, this.description);
			preparedStatement.setString(i++, this.photo_url);
			st = preparedStatement.executeUpdate();
			System.out.println("addCustomer 2");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			con.close();
		}
		return st;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public ArrayList<Owns> getOwns() {
		return owns;
	}

	public void setOwns(ArrayList<Owns> owns) {
		this.owns = owns;
	}

	public ArrayList<Like> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<Like> likes) {
		this.likes = likes;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	
}

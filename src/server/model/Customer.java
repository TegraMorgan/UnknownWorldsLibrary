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

/**
 * The Class Customer.
 */
public class Customer implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uid. */
	private int uid;
	
	/** The username. */
	private String username;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;
	
	/** The password. */
	private String password;
	
	/** The nickname. */
	private String nickname;
	
	/** The description. */
	private String description;
	
	/** The photo url. */
	private String photo_url;
	
	/** The st name. */
	private String stName;
	
	/** The bl num. */
	private int blNum;


	/** The city name. */
	private String cityName;
	
	/** The zip. */
	private String zip;
	
	/** The owns. */
	private ArrayList<Owns> owns;
	
	/** The likes. */
	private ArrayList<Like> likes;
	
	/** The reviews. */
	private ArrayList<Review> reviews;

	/**
	 * Instantiates a new customer.
	 *
	 * @param uId the u id
	 * @param userName the user name
	 * @param eMail the e mail
	 * @param pHone the hone
	 * @param pAssword the assword
	 * @param nickName the nick name
	 * @param dEscription the d escription
	 * @param pHoto_url the hoto url
	 * @param sTName the s T name
	 * @param bLNum the b L num
	 * @param cItyName the c ity name
	 * @param zIp the z ip
	 * @param oWns the o wns
	 * @param lIkes the l ikes
	 * @param rEviews the r eviews
	 */
	public Customer(int uId, String userName, String eMail, String pHone, String pAssword, String nickName,
			String dEscription, String pHoto_url, String sTName, int bLNum, String cItyName, String zIp,
			ArrayList<Owns> oWns, ArrayList<Like> lIkes, ArrayList<Review> rEviews) {
		this.uid = uId;
		this.username = userName;
		this.email = eMail;
		this.phone = pHone;
		this.password = pAssword;
		this.nickname = nickName;
		this.description = dEscription;
		this.photo_url = pHoto_url;
		this.stName = sTName;
		this.blNum = bLNum;
		this.cityName = cItyName;
		this.zip = zIp;
		this.owns = oWns;
		this.likes = lIkes;
		this.reviews = rEviews;
	}

	/**
	 * Instantiates a new customer.
	 *
	 * @param rs the rs
	 * @param oWns the o wns
	 * @param lIkes the l ikes
	 * @param rEviews the r eviews
	 * @throws SQLException the SQL exception
	 */
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
		this.stName = rs.getString("st_name");
		this.blNum = rs.getInt("bl_num");
		this.cityName = rs.getString("city_nm");
		this.zip = rs.getString("zip");
		this.owns = oWns;
		this.likes = lIkes;
		this.reviews = rEviews;
	}

	/**
	 * Instantiates a new customer.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	public Customer(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.username = rs.getString("username");
		this.email = rs.getString("email");
		this.phone = rs.getString("phone");
		this.password = rs.getString("password");
		this.nickname = rs.getString("nickname");
		this.description = rs.getString("description");
		this.photo_url = rs.getString("photo_url");
		this.photo_url = rs.getString("photo_url");
		this.stName = rs.getString("st_name");
		this.blNum = rs.getInt("bl_num");
		this.cityName = rs.getString("city_nm");
		this.zip = rs.getString("zip");
		this.getMyBooks();
		this.getMyLikes();
		this.getMyreviews();
	}

	/**
	 * Gets the my books.
	 *
	 * @return the my books
	 */
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

	/**
	 * Gets the my likes.
	 *
	 * @return the my likes
	 * @throws SQLException the SQL exception
	 */
	// get my likes from DB
	public void getMyLikes() throws SQLException {
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
			resltset.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			Statement.close();
			con.close();
		}
	}

	/**
	 * Gets the myreviews.
	 *
	 * @return the myreviews
	 */
	// get my reviews from DB
	public void getMyreviews() {
		this.reviews = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				Statement = con.prepareStatement(ApplicationConstants.SELECT_REVIEWS_BY_UID);
				try {
					Statement.setInt(1, uid);
					ResultSet resltset = Statement.executeQuery();
					try {
						while (resltset.next()) {
							this.reviews.add(new Review(resltset));
						}
					} catch (Exception e) {
					} finally {
						resltset.close();
					}
				} catch (Exception e) {

				} finally {
					Statement.close();
				}
			} catch (Exception e) {

			} finally {
				con.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds the customer.
	 *
	 * @return the int
	 * @throws Exception the exception
	 */
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
			if (this.photo_url == null || this.photo_url.isEmpty())
				this.photo_url = "img/Users/Default.gif";
			preparedStatement.setString(i++, this.photo_url);
			preparedStatement.setString(i++, this.stName);
			preparedStatement.setInt(i++, this.blNum);
			preparedStatement.setString(i++, this.cityName);
			preparedStatement.setString(i++, this.zip);
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

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid the new uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 * Gets the photo url.
	 *
	 * @return the photo url
	 */
	public String getPhoto_url() {
		return photo_url;
	}
	/**
	 * Gets the bl num.
	 *
	 * @return the bl num
	 */
	public int getBlNum() {
		return blNum;
	}

	/**
	 * Sets the photo url.
	 *
	 * @param photo_url the new photo url
	 */
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	/**
	 * Gets the street name.
	 *
	 * @return the street name
	 */
	public String getStName() {
		return stName;
	}
	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */

	public String getCityName() {
		return cityName;
	}
	/**
	 * Gets the zip.
	 *
	 * @return the zip.
	 */

	public String getZip() {
		return zip;
	}

	/**
	 * Gets the owns.
	 *
	 * @return the owns
	 */
	public ArrayList<Owns> getOwns() {
		return owns;
	}

	/**
	 * Sets the owns.
	 *
	 * @param owns the new owns
	 */
	public void setOwns(ArrayList<Owns> owns) {
		this.owns = owns;
	}

	/**
	 * Gets the likes.
	 *
	 * @return the likes
	 */
	public ArrayList<Like> getLikes() {
		return likes;
	}

	/**
	 * Sets the likes.
	 *
	 * @param likes the new likes
	 */
	public void setLikes(ArrayList<Like> likes) {
		this.likes = likes;
	}

	/**
	 * Gets the reviews.
	 *
	 * @return the reviews
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets the reviews.
	 *
	 * @param reviews the new reviews
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

}

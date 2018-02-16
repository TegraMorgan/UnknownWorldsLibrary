package server.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public Customer(int uId, String userName, String eMail, String pHone, String pAssword, String nickName,
			String dEscription, String pHoto_url) {
		this.uid = uId;
		this.username = userName;
		this.email = eMail;
		this.phone = pHone;
		this.password = pAssword;
		this.nickname = nickName;
		this.description = dEscription;
		this.photo_url = pHoto_url;
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

	public int getCustomer(String userName, String password) throws SQLException {
		Connection con = null;
		PreparedStatement Statement = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			Statement = con.prepareStatement(ApplicationConstants.FIND_CUSTOMER_BY_USERNAME_AND_PASS);
			Statement.setString(1, userName);
			Statement.setString(2, password);
			ResultSet resltset = Statement.executeQuery();
			if (resltset.next()) {
				this.uid = resltset.getInt("uid");
				this.username = resltset.getString("username");
				this.email = resltset.getString("email");
				this.phone = resltset.getString("phone");
				this.password = resltset.getString("password");
				this.nickname = resltset.getString("nickname");
				this.description = resltset.getString("description");
				this.photo_url = resltset.getString("photo_url");
				return 1;
			} else
				return -1;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			Statement.close();
			con.close();
		}
		return -1;
	}

}

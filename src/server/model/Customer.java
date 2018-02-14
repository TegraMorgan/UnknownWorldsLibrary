package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Customer {
	private int uid;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String nickname;
	private String description;
	private String photo_url;

	public Customer() {
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

	public int addCustomer() throws Exception {
		int st = 0;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		System.out.println("addCustomer 1");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			preparedStatement = con.prepareStatement(ApplicationConstants.INSERT_NEW_CUSTOMER);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.phone);
			preparedStatement.setString(4, this.password);
			preparedStatement.setString(5, this.nickname);
			preparedStatement.setString(6, this.description);
			preparedStatement.setString(7, this.photo_url);
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

	public Customer(ResultSet rs) {

	}

}

package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class Admin.
 */
public class Admin {

/** The aid. */
private int aid;

/** The login. */
private String login;

/** The password. */
private String password;

/**
 * Instantiates a new admin.
 */
public Admin() {}

/**
 * Instantiates a new admin.
 *
 * @param resltset the resltset
 * @throws SQLException the SQL exception
 */
public Admin(ResultSet resltset) throws SQLException{
	this.aid = resltset.getInt("aid");
	this.login = resltset.getString("login");
	this.password = resltset.getString("password");

}

/**
 * Gets the aid.
 *
 * @return the aid
 */
public int getAid() {
	return aid;
}

/**
 * Sets the aid.
 *
 * @param aid the new aid
 */
public void setAid(int aid) {
	this.aid = aid;
}

/**
 * Gets the login.
 *
 * @return the login
 */
public String getLogin() {
	return login;
}

/**
 * Sets the login.
 *
 * @param login the new login
 */
public void setLogin(String login) {
	this.login = login;
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

}

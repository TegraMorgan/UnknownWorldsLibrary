package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Admin {
private int aid;
private String login;
private String password;

public Admin() {}

public Admin(ResultSet resltset) throws SQLException{
	this.aid = resltset.getInt("aid");
	this.login = resltset.getString("login");
	this.password = resltset.getString("password");

}

public int getAid() {
	return aid;
}

public void setAid(int aid) {
	this.aid = aid;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}

package server.model;

import java.sql.ResultSet;

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

public Customer(int uId, String userName,String eMail, String pHone, String pAssword, String nickName, String dEscription, String pHoto_url) {
	this.uid=uId;
	this.username=userName;
	this.email=eMail;
	this.phone=pHone;
	this.password=pAssword;
	this.nickname=nickName;
	this.description=dEscription;
	this.photo_url=pHoto_url;
}

public Customer(ResultSet rs) {
	
}

}

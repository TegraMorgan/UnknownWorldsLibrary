package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Admin;

public class AdminLogInResponse extends BasicResponse {
	private Admin admin;

	private static final long serialVersionUID = 1L;

	public AdminLogInResponse() {
		admin = null;
	}

	public AdminLogInResponse(String st, Admin admin) {
		super(st);
		this.admin = admin;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<AdminLogInResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}

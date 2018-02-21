package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Book;
import server.model.Customer;

public class DetailedUserResponse extends BasicResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer user;

	public DetailedUserResponse() {
		super();
		this.user = null;
	}

	public DetailedUserResponse(String su, Customer cust) {
		super(su);
		this.user = cust;
	}
	
	public java.lang.reflect.Type getType() {
		return new TypeToken<DetailedUserResponse>() {}.getType();
	}
	
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}
	
	
	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}
}

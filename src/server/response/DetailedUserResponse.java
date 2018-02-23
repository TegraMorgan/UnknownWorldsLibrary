package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Book;
import server.model.Customer;

/**
 * The Class DetailedUserResponse.
 */
public class DetailedUserResponse extends BasicResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user. */
	private Customer user;

	/**
	 * Instantiates a new detailed user response.
	 */
	public DetailedUserResponse() {
		super();
		this.user = null;
	}

	/**
	 * Instantiates a new detailed user response.
	 *
	 * @param su the su
	 * @param cust the cust
	 */
	public DetailedUserResponse(String su, Customer cust) {
		super(su);
		this.user = cust;
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<DetailedUserResponse>() {}.getType();
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#tojson()
	 */
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}
	
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Customer getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Customer user) {
		this.user = user;
	}
}

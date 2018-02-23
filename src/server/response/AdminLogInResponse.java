package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Admin;

/**
 * The Class AdminLogInResponse.
 */
public class AdminLogInResponse extends BasicResponse {
	
	/** The admin. */
	private Admin admin;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new admin log in response.
	 */
	public AdminLogInResponse() {
		admin = null;
	}

	/**
	 * Instantiates a new admin log in response.
	 *
	 * @param st the st
	 * @param admin the admin
	 */
	public AdminLogInResponse(String st, Admin admin) {
		super(st);
		this.admin = admin;
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<AdminLogInResponse>() {
		}.getType();
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
	 * Gets the admin.
	 *
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}

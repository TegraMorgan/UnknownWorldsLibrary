package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Customer;

/**
 * The Class GetAllUsersResponse.
 */
public class GetAllUsersResponse extends BasicResponse{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The customers. */
	private ArrayList<Customer> customers;
	
	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Sets the customers.
	 *
	 * @param customers the new customers
	 */
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Instantiates a new gets the all users response.
	 */
	public GetAllUsersResponse() {
		this.customers = null;
	}

	/**
	 * Instantiates a new gets the all users response.
	 *
	 * @param su the su
	 * @param customers the customers
	 */
	public GetAllUsersResponse(String su, ArrayList<Customer> customers) {
		super(su);
		this.customers = customers;
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetAllUsersResponse>() {}.getType();
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#tojson()
	 */
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}
	
}

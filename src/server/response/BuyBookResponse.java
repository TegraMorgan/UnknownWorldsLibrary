package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Customer;

/**
 * The Class BuyBookResponse.
 */
public class BuyBookResponse extends BasicResponse {
	
	/** The customer. */
	private Customer customer;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new buy book response.
	 */
	public BuyBookResponse() {
		this.customer = null;
	}

	/**
	 * Instantiates a new buy book response.
	 *
	 * @param su the su
	 * @param cust the cust
	 */
	public BuyBookResponse(String su, Customer cust) {
		super(su);
		this.customer = cust;
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<BuyBookResponse>() {
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
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

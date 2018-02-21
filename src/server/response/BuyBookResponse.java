package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Customer;

public class BuyBookResponse extends BasicResponse {
	private Customer customer;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuyBookResponse() {
		this.customer = null;
	}

	public BuyBookResponse(String su, Customer cust) {
		super(su);
		this.customer = cust;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<BuyBookResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

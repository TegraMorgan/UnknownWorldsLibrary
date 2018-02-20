package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Customer;

public class GetAllUsersResponse extends BasicResponse{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Customer> customers;
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public GetAllUsersResponse() {
		this.customers = null;
	}

	public GetAllUsersResponse(String su, ArrayList<Customer> customers) {
		super(su);
		this.customers = customers;
	}
	
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetAllUsersResponse>() {}.getType();
	}
	
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}
	
}

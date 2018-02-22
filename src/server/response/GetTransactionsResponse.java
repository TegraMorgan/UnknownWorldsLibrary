package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Transaction;

public class GetTransactionsResponse extends BasicResponse{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Transaction> transactions;
	
	public GetTransactionsResponse() {
		this.transactions = null;
	}

	public GetTransactionsResponse(String su, ArrayList<Transaction> tRansactions) {
		super(su);
		this.transactions = tRansactions;
	}
	
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetTransactionsResponse>() {}.getType();
	}
	
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> tRansactions) {
		this.transactions = tRansactions;
	}
	
}

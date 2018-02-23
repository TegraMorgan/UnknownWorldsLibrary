package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Transaction;

/**
 * The Class GetTransactionsResponse.
 */
public class GetTransactionsResponse extends BasicResponse{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The transactions. */
	private ArrayList<Transaction> transactions;
	
	/**
	 * Instantiates a new gets the transactions response.
	 */
	public GetTransactionsResponse() {
		this.transactions = null;
	}

	/**
	 * Instantiates a new gets the transactions response.
	 *
	 * @param su the su
	 * @param tRansactions the t ransactions
	 */
	public GetTransactionsResponse(String su, ArrayList<Transaction> tRansactions) {
		super(su);
		this.transactions = tRansactions;
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetTransactionsResponse>() {}.getType();
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
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Sets the transactions.
	 *
	 * @param tRansactions the new transactions
	 */
	public void setTransactions(ArrayList<Transaction> tRansactions) {
		this.transactions = tRansactions;
	}
	
}

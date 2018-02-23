package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Book;

/**
 * The Class GetAllBooksResponse.
 */
public class GetAllBooksResponse extends BasicResponse{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The books. */
	private ArrayList<Book> books;
	
	/**
	 * Instantiates a new gets the all books response.
	 */
	public GetAllBooksResponse() {
		this.books = null;
	}

	/**
	 * Instantiates a new gets the all books response.
	 *
	 * @param su the su
	 * @param bOok the b ook
	 */
	public GetAllBooksResponse(String su, ArrayList<Book> bOok) {
		super(su);
		this.books = bOok;
	}
	
	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetAllBooksResponse>() {}.getType();
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
	 * Gets the books.
	 *
	 * @return the books
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Sets the books.
	 *
	 * @param bOoks the new books
	 */
	public void setBooks(ArrayList<Book> bOoks) {
		this.books = bOoks;
	}
	
}

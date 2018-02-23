package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Admin;
import server.model.Book;

/**
 * The Class GetBookDetailResponse.
 */
public class GetBookDetailResponse extends BasicResponse {
	
	/** The book. */
	private Book book;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new gets the book detail response.
	 */
	public GetBookDetailResponse() {
		book = null;
	}

	/**
	 * Instantiates a new gets the book detail response.
	 *
	 * @param st the st
	 * @param book the book
	 */
	public GetBookDetailResponse(String st, Book book) {
		super(st);
		this.book = book;
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetBookDetailResponse>() {
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
	 * Gets the book.
	 *
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Sets the book.
	 *
	 * @param book the new book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

}

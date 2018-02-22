package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Admin;
import server.model.Book;

public class GetBookDetailResponse extends BasicResponse {
	private Book book;

	private static final long serialVersionUID = 1L;

	public GetBookDetailResponse() {
		book = null;
	}

	public GetBookDetailResponse(String st, Book book) {
		super(st);
		this.book = book;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<GetBookDetailResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}

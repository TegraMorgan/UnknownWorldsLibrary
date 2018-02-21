package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Book;

public class GetAllBooksResponse extends BasicResponse{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Book> books;
	
	public GetAllBooksResponse() {
		this.books = null;
	}

	public GetAllBooksResponse(String su, ArrayList<Book> bOok) {
		super(su);
		this.books = bOok;
	}
	
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetAllBooksResponse>() {}.getType();
	}
	
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> bOoks) {
		this.books = bOoks;
	}
	
}

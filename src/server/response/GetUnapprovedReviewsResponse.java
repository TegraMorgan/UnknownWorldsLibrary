package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Review;

public class GetUnapprovedReviewsResponse extends BasicResponse {
	private static final long serialVersionUID = 1L;

	private ArrayList<Review> reviews;

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public GetUnapprovedReviewsResponse() {
		this.reviews = null;
	}

	public GetUnapprovedReviewsResponse(String su, ArrayList<Review> reviews) {
		super(su);
		this.reviews = reviews;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<GetUnapprovedReviewsResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

}

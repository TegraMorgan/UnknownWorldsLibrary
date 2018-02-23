package server.response;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Review;

/**
 * The Class GetUnapprovedReviewsResponse.
 */
public class GetUnapprovedReviewsResponse extends BasicResponse {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reviews. */
	private ArrayList<Review> reviews;

	/**
	 * Gets the reviews.
	 *
	 * @return the reviews
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets the reviews.
	 *
	 * @param reviews the new reviews
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Instantiates a new gets the unapproved reviews response.
	 */
	public GetUnapprovedReviewsResponse() {
		this.reviews = null;
	}

	/**
	 * Instantiates a new gets the unapproved reviews response.
	 *
	 * @param su the su
	 * @param reviews the reviews
	 */
	public GetUnapprovedReviewsResponse(String su, ArrayList<Review> reviews) {
		super(su);
		this.reviews = reviews;
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetUnapprovedReviewsResponse>() {
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

}

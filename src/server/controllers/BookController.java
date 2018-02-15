package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import server.model.Book;
import server.model.Review;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class BookController {
	
	public static LinkedList<Book> GetAllBooks(){
		LinkedList<Book> result = new LinkedList<Book>();
		Connection conn = null;
		PreparedStatement bookStmt = null;
		PreparedStatement likesStmt = null;
		PreparedStatement reviewsStmt = null;
		int i = 1;
		try {
			conn = (Connection) DataStructure.ds.getConnection();
			bookStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_BOOKS);
			ResultSet bookSet = bookStmt.executeQuery();
			likesStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_LIKES);
			ResultSet likesSet = likesStmt.executeQuery();
			reviewsStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_APPROVED_REVIEWS);
			ResultSet reviewsSet = reviewsStmt.executeQuery();
			LinkedList<String> likedList = new LinkedList<String>();
			LinkedList<Review> reviewList = new LinkedList<Review>();
			while(bookSet.next())
			{
				likesSet.beforeFirst();
				reviewsSet.beforeFirst();
				likedList = filterLikes(bookSet.getInt("bid"), likesSet);
				reviewList = filterReviews(bookSet.getInt("bid"), reviewsSet);
				result.add(new Book(bookSet, likedList, reviewList));
			}
			bookSet.close();
			likesSet.close();
			reviewsSet.close();
			bookStmt.close();
			likesStmt.close();
			reviewsStmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LinkedList<Review> filterReviews(int bId, ResultSet reviewsSet) throws SQLException {
		LinkedList<Review> result = new LinkedList<Review>();
		while(reviewsSet.next()) {
			if(reviewsSet.getInt("bid")==bId)
			{
				result.add(new Review(reviewsSet));
			}
		}
		return result;
	}

	public static LinkedList<String> filterLikes(int bId, ResultSet likesSet) throws SQLException {
		LinkedList<String> result = new LinkedList<String>();
		while(likesSet.next()) {
			if(likesSet.getInt("bid")==bId)
			{
				result.add(likesSet.getString("nickname"));
			}
		}
		return result;
	}
}

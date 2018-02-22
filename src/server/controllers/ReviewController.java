package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

import server.model.Review;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class ReviewController {

	public static ArrayList<Review> getUnapprovedReviews() {
		ArrayList<Review> result = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.GET_ALL_UNAPPROVED_REVIEWS);
				try {
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							result.add(new Review(rs));
						}
					} catch (Exception e) {
					} finally {
						rs.close();
					}
				} catch (Exception e) {
				} finally {
					stmt.close();
				}
			} catch (Exception e) {
			} finally {
				con.close();
			}
		} catch (Exception e) {
		}
		return result;
	}

}

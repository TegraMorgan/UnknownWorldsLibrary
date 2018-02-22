package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;

import server.model.Book;
import server.model.Customer;
import server.model.Review;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class BookController {

	public static Book GetBookById(int bid) {
		Book result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.GET_BOOK_BY_ID);
				try {
					stmt.setInt(1, bid);
					ResultSet rs = stmt.executeQuery();
					try {
						if (rs.next()) {
							result = new Book(rs);
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

			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<Book> getAllBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		Connection conn = null;
		Connection conn2 = null;
		Connection conn3 = null;
		PreparedStatement bookStmt = null;
		PreparedStatement likesStmt = null;
		PreparedStatement reviewsStmt = null;
		int i = 1;
		try {
			conn = (Connection) DataStructure.ds.getConnection();
			try {
				conn2 = (Connection) DataStructure.ds.getConnection();
				try {
					conn3 = (Connection) DataStructure.ds.getConnection();
					try {
						bookStmt = conn.prepareStatement(ApplicationConstants.GET_ALL_BOOKS,
								ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						try {
							ResultSet bookSet = bookStmt.executeQuery();
							try {
								likesStmt = conn2.prepareStatement(ApplicationConstants.GET_ALL_LIKES,
										ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
								try {
									ResultSet likesSet = likesStmt.executeQuery();
									try {
										reviewsStmt = conn3.prepareStatement(
												ApplicationConstants.GET_ALL_APPROVED_REVIEWS,
												ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
										try {
											ResultSet reviewsSet = reviewsStmt.executeQuery();
											try {
												LinkedList<String> likedList = new LinkedList<String>();
												LinkedList<Review> reviewList = new LinkedList<Review>();
												while (bookSet.next()) {
													likesSet.beforeFirst();
													reviewsSet.beforeFirst();
													likedList = filterLikes(bookSet.getInt("bid"), likesSet);
													reviewList = filterReviews(bookSet.getInt("bid"), reviewsSet);
													result.add(new Book(bookSet, likedList, reviewList));
												}
											} catch (Exception e) {
											} finally {
												reviewsSet.close();
											}
										} catch (Exception e) {
										} finally {
											reviewsStmt.close();
										}
									} catch (Exception e) {
									} finally {
										likesSet.close();
									}
								} catch (Exception e) {
								} finally {
									likesStmt.close();
								}
							} catch (Exception e) {
							} finally {
								bookSet.close();
							}
						} catch (Exception e) {
						} finally {
							bookStmt.close();
						}
					} catch (Exception e) {
					} finally {
						conn3.close();
					}
				} catch (Exception e) {
				} finally {
					conn2.close();
				}
			} catch (Exception e) {
			} finally {
				conn.close();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LinkedList<Review> filterReviews(int bId, ResultSet reviewsSet) throws SQLException {
		LinkedList<Review> result = new LinkedList<Review>();
		while (reviewsSet.next()) {
			if (reviewsSet.getInt("bid") == bId) {
				result.add(new Review(reviewsSet));
			}
		}
		return result;
	}

	public static LinkedList<String> filterLikes(int bId, ResultSet likesSet) throws SQLException {
		LinkedList<String> result = new LinkedList<String>();
		while (likesSet.next()) {
			if (likesSet.getInt("bid") == bId) {
				result.add(likesSet.getString("nickname"));
			}
		}
		return result;
	}
}

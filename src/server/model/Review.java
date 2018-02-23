package server.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

/**
 * The Class Review.
 */
public class Review implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uid. */
	private int uid;
	
	/** The bid. */
	private int bid;
	
	/** The nickname. */
	private String nickname;
	
	/** The book name. */
	private String bookName;
	
	/** The approved by. */
	private int approvedBy;
	
	/** The review body. */
	private String reviewBody;

	/**
	 * Instantiates a new review.
	 *
	 * @param uId the u id
	 * @param bId the b id
	 * @param nIckname the n ickname
	 * @param bOokName the b ook name
	 * @param aPprovedBy the a pproved by
	 * @param rEviewBody the r eview body
	 */
	public Review(int uId, int bId, String nIckname, String bOokName, int aPprovedBy, String rEviewBody) {
		this.uid = uId;
		this.bid = bId;
		this.nickname = nIckname;
		this.bookName = bOokName;
		this.approvedBy = aPprovedBy;
		this.reviewBody = rEviewBody;
	}

	/**
	 * Instantiates a new review.
	 *
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	public Review(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("bid");
		this.nickname = rs.getString("nickname");
		this.bookName = rs.getString("book_name");
		this.approvedBy = rs.getInt("approved_by");
		this.reviewBody = rs.getString("text");
	}

	/**
	 * Adds the to DB.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int addToDB() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("Adding a review to DB");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.INSERT_NEW_REVIEW);
			int i = 1;
			stmt.setInt(i++, this.bid);
			stmt.setInt(i++, this.uid);
			stmt.setString(i++, this.reviewBody);
			if (this.approvedBy != 0)
				stmt.setInt(i++, this.approvedBy);
			else
				stmt.setNull(i++, java.sql.Types.INTEGER);
			if (stmt.executeUpdate() != 1) {
				throw new Exception();
			}
			System.out.println("Review added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;

	}

	/**
	 * Approve.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int approve() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("approving review");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			stmt = con.prepareStatement(ApplicationConstants.UPDATE_REVIEW_BY_BID_AND_UID);
			int i = 1;
			stmt.setInt(i++, this.approvedBy);
			stmt.setInt(i++, this.bid);
			stmt.setInt(i++, this.uid);
			if (stmt.executeUpdate() != 1) {
				throw new Exception();
			}
			System.out.println("Review approved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;

	}

	/**
	 * Disprove.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int disprove() throws SQLException {
		PreparedStatement stmt = null;
		Connection con = null;
		System.out.println("approving review");
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.DELETE_REVIEW_BY_BID_AND_UID);
				try {
					int i = 1;
					stmt.setInt(i++, this.bid);
					stmt.setInt(i++, this.uid);
					if (stmt.executeUpdate() != 1) {
						throw new Exception();
					}
					System.out.println("Review deleted");
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
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;

	}

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid the new uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * Gets the bid.
	 *
	 * @return the bid
	 */
	public int getBid() {
		return bid;
	}

	/**
	 * Sets the bid.
	 *
	 * @param bid the new bid
	 */
	public void setBid(int bid) {
		this.bid = bid;
	}

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Gets the book name.
	 *
	 * @return the book name
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * Sets the book name.
	 *
	 * @param bookName the new book name
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * Gets the approved by.
	 *
	 * @return the approved by
	 */
	public int getApprovedBy() {
		return approvedBy;
	}

	/**
	 * Sets the approved by.
	 *
	 * @param approvedBy the new approved by
	 */
	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * Gets the review body.
	 *
	 * @return the review body
	 */
	public String getReviewBody() {
		return reviewBody;
	}

	/**
	 * Sets the review body.
	 *
	 * @param reviewBody the new review body
	 */
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
}

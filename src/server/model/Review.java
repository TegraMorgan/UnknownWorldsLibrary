package server.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class Review implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uid;
	private int bid;
	private String nickname;
	private String bookName;
	private int approvedBy;
	private String reviewBody;

	public Review(int uId, int bId, String nIckname, String bOokName, int aPprovedBy, String rEviewBody) {
		this.uid = uId;
		this.bid = bId;
		this.nickname = nIckname;
		this.bookName = bOokName;
		this.approvedBy = aPprovedBy;
		this.reviewBody = rEviewBody;
	}

	public Review(ResultSet rs) throws SQLException {
		this.uid = rs.getInt("uid");
		this.bid = rs.getInt("bid");
		this.nickname = rs.getString("nickname");
		this.bookName = rs.getString("book_name");
		this.approvedBy = rs.getInt("approved_by");
		this.reviewBody = rs.getString("text");
	}

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
			stmt.setInt(i++, this.approvedBy);
			if (stmt.executeUpdate()!=1) {
				throw new Exception();
			}
			System.out.println("Book added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return this.bid;
		
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getReviewBody() {
		return reviewBody;
	}

	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
}

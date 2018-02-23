package server.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.crypto.provider.RSACipher;

import server.model.Position;
import server.utils.ApplicationConstants;
import server.utils.DataStructure;

public class PositionController {

	public static boolean savePosition(Position pos) {
		boolean result = false;
		boolean update = false;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.FIND_READPOS_BY_UID_BID);
				try {
					stmt.setInt(1, pos.getBid());
					stmt.setInt(2, pos.getUid());
					ResultSet rs = stmt.executeQuery();
					if (rs.next())
						update = true;
					rs.close();
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println(e);
				throw e;
			} finally {
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (update) {
			try {
				con = (Connection) DataStructure.ds.getConnection();
				try {
					stmt = con.prepareStatement(ApplicationConstants.UPDATE_READPOS_BY_BID_AND_UID);
					try {
						stmt.setLong(1, pos.getPosition());
						stmt.setInt(2, pos.getBid());
						stmt.setInt(3, pos.getUid());
						if (stmt.executeUpdate() == 1) {
							result = true;
						}
					} catch (Exception e) {
						System.out.println(e);
					} finally {
						stmt.close();
					}
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					con.close();
				}
			} catch (Exception e) {
				System.out.print(e);
			}
		} else {
			try {
				con = (Connection) DataStructure.ds.getConnection();
				try {
					stmt = con.prepareStatement(ApplicationConstants.INSERT_NEW_READPOS);
					try {
						stmt.setInt(1, pos.getBid());
						stmt.setInt(2, pos.getUid());
						stmt.setLong(3, pos.getPosition());
						if (stmt.executeUpdate() == 1)
							result = true;
					} catch (Exception e) {
					} finally {
						stmt.close();
					}
				} catch (Exception e) {
				} finally {
					con.close();
				}
			} catch (Exception e) {
			} finally {
			}
		}
		return result;
	}

	public static Position getOldPosition(Position pos) {
		Position result = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = (Connection) DataStructure.ds.getConnection();
			try {
				stmt = con.prepareStatement(ApplicationConstants.FIND_READPOS_BY_UID_BID);
				try {
					stmt.setInt(1, pos.getBid());
					stmt.setInt(2, pos.getUid());
					ResultSet rs = stmt.executeQuery();
					try {
						if (rs.next())
							result = new Position(rs);
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

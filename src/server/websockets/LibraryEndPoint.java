package server.websockets;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import server.utils.ApplicationConstants;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;;

public class LibraryEndPoint {

	
	/**
	 * Returns a connection to the database using the tomcat context
	 * @return the connection to the database
	 */
	private Connection getDataBaseConnection() {
		try {
			Context context = new InitialContext(); // obtain projectDB data source from Tomcat's context
			BasicDataSource ds = (BasicDataSource)context.lookup(AppConstants.DB_CONTEXT + AppConstants.OPEN);
			return ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

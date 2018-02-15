package server.listeners;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import server.utils.*;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.model.User;


/**
 * This class creates the database tables and populates it with the data
 */
@WebListener
public class listener1 implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public listener1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext cntx = event.getServletContext();
		try {
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource) context
					.lookup(cntx.getInitParameter(ApplicationConstants.DB_DATASOURCE) + ApplicationConstants.SHUTDOWN);
			ds.getConnection();
			ds = null;
		} catch (SQLException | NamingException e) {
			cntx.log("Error shutting down database", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext cntx = event.getServletContext();

		try {
			DataStructure.context = new InitialContext();
			DataStructure.ds = (BasicDataSource) DataStructure.context
					.lookup(cntx.getInitParameter(ApplicationConstants.DB_DATASOURCE) + ApplicationConstants.OPEN);
			Connection conn = DataStructure.ds.getConnection();
			System.out.println("data base create at listener1");
			boolean created = false;
			try {
				// create all tables
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_ADMIN_TABLE);
				stmt.executeUpdate(ApplicationConstants.CREATE_BOOK_TABLE);
				stmt.executeUpdate(ApplicationConstants.CREATE_CUSTOMER_TABLE);
				stmt.executeUpdate(ApplicationConstants.CREATE_LIKES_TABLE);
				stmt.executeUpdate(ApplicationConstants.CREATE_OWNS_TABLE);
				stmt.executeUpdate(ApplicationConstants.CREATE_REVIEWS_TABLE);

				// commit update

				conn.commit();
				// close statements
				stmt.close();
			}

			catch (SQLException e) {
				// check if exception thrown since table was already created (so
				// we
				// created the database already
				// in the past
				created = tableAlreadyExists(e);
				if (!created) {
					System.out.println("catch one");
					throw e; /////////////////////////////////////////////////////////////////////////////
					// re-throw the exception so it will be caught in the
					// external try..catch and recorded as error in the log*
				}

			}

			conn.close();

		} catch (SQLException | NamingException e) {
			// log error
			System.out.println("catch 2 Error during database initialization ");
			cntx.log("Error during database initialization", e);
		}

	}

	private boolean tableAlreadyExists(SQLException e) {
		boolean exists;
		if (e.getSQLState().equals("X0Y32")) {
			exists = true;
		} else {
			exists = false;
		}
		return exists;
	}
}

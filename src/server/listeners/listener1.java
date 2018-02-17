package server.listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import server.model.*;
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
	 * Default C'tor.
	 */
	public listener1() {}

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
			boolean newDB = false;
			try {
				// create all tables
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_ADMIN_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();

			}
			catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) { // if not a 'table already exists' exception, rethrow
					throw e;
				}

			}
			
			if (!newDB){
                // Create admin table with admin data from json file
    			try {
	                Collection<Admin> admins = loadAdmins(cntx.getResourceAsStream(File.separator + ApplicationConstants.ADMINS_FILE));
	                PreparedStatement pstmt2 = conn.prepareStatement(ApplicationConstants.INSERT_NEW_ADMIN);
	                int i;
	                for (Admin admin : admins) {
	                	i=1;
	                    pstmt2.setString(i++, admin.getLogin());
	                    pstmt2.setString(i++, admin.getPassword());
	                    pstmt2.executeUpdate();
	                }
	                conn.commit();
	                pstmt2.close();
    			} catch (IOException | NullPointerException e) { }
    		}
			
			try {	
				Statement stmt = conn.createStatement();
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
				if (!(newDB = tableAlreadyExists(e))) { // if not a 'table already exists' exception, rethrow
					throw e;
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
	
	/* this function should be made generic */
	private Collection<Admin> loadAdmins(InputStream is) throws IOException {
    	try {
    		if (is != null) {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    			StringBuilder jsonFileContent = new StringBuilder();

    			String nextLine = null;
    			while ((nextLine = reader.readLine()) != null) {
    				jsonFileContent.append(nextLine);
    			}

    			Gson gson = new Gson();
    			Type type = new TypeToken<Collection<Admin>>() { }.getType();
    			Collection<Admin> admins = gson.fromJson(jsonFileContent.toString(), type);
    			reader.close();
    			return admins;
    		}
    		return null;
		} catch (NullPointerException e) {
			return null;
		}
    }
}

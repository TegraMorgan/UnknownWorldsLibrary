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

import org.apache.derby.iapi.types.Like;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import server.model.*;
import server.utils.*;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.model.User;

/**
 * The Class listener1.
 */
@WebListener
public class listener1 implements ServletContextListener {

	/**
	 * Instantiates a new listener 1.
	 */
	public listener1() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
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

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
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
				// Create admin table
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_ADMIN_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();

			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) { // if not a 'table already exists' exception, rethrow
					throw e;
				}
			}

			if (!newDB) {
				// Populate admin table with admin data from json file
				try {
					Collection<Admin> admins = loadAdmins(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.ADMINS_FILE));
					PreparedStatement pstmt2 = conn.prepareStatement(ApplicationConstants.INSERT_NEW_ADMIN);
					int i;
					for (Admin admin : admins) {
						i = 1;
						pstmt2.setString(i++, admin.getLogin());
						pstmt2.setString(i++, admin.getPassword());
						pstmt2.executeUpdate();
					}
					conn.commit();
					pstmt2.close();
				} catch (IOException | NullPointerException e) {
				}
			}

			newDB = false;
			try {
				// create customer table
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_CUSTOMER_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
					throw e;
				}
			}

			if (!newDB) {
				// Populate customers table with customer data from json file
				try {
					Collection<Customer> customers = loadCustomers(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.CUSTOMERS_FILE));
					for (Customer customer : customers) {
						System.out.println(
								"Customer : " + customer.getUsername() + ", Password : " + customer.getPassword());
						customer.addCustomer();
					}
				} catch (Exception e) {
				}
			}

			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_BOOK_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
					throw e;
				}
			}

			if (!newDB) {
				// Populate customers table with customer data from json file
				try {
					Collection<Book> books = loadBooks(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.BOOKS_FILE));
					for (Book book : books) {
						System.out.println("Book name : " + book.getName() + ", Price : " + book.getPrice());
						book.addBook();
					}
				} catch (Exception e) {
				}
			}
			
			
			newDB = false;
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_LIKES_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
					throw e;
				}
			}

			if (!newDB) {
				// Populate Likes table with Likes data from json file
				try {
					Collection<server.model.Like> likes = loadLikes(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.LIKES_FILE));
					for (server.model.Like like : likes) {
						System.out.println("Bid : " + like.getBid() + ", Uid: " + like.getUid());
						like.addLikeToDB();
						
						
					}
				} catch (Exception e) {
				}
			}
			
			newDB = false;
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_OWNS_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
					throw e;
				}
			}
			
			if (!newDB) {
				// Populate Likes table with Likes data from json file
				try {
					Collection<Owns> owns = loadOwns(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.OWNS_FILE));
					for (Owns own : owns) {
						System.out.println("Bid : " + own.getBid() + ", Uid: " + own.getUid());
						own.addToDB();
					}
				} catch (Exception e) {
				}
			}
			
			newDB = false;
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(ApplicationConstants.CREATE_REVIEWS_TABLE);
				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
					throw e;
				}
			}
			
			if (!newDB) {
				// Populate Likes table with Likes data from json file
				try {
					Collection<Review> reviews = loadReviews(
							cntx.getResourceAsStream(File.separator + ApplicationConstants.REVIEWS_FILE));
					for (Review review : reviews) {
						System.out.println("review for book: " +review.getBookName() + ", Uid: " + review.getUid());
						review.addToDB();
					}
				} catch (Exception e) {
				}
			}
			try {
				Statement stmt = conn.createStatement();
				System.out.println("building read position");
				stmt.executeUpdate(ApplicationConstants.CREATE_READPOS_TABLE);

				// commit update
				conn.commit();
				// close statements
				stmt.close();
			} catch (SQLException e) {
				if (!(newDB = tableAlreadyExists(e))) {
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

	/**
	 * Table already exists.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	private boolean tableAlreadyExists(SQLException e) {
		boolean exists;
		if (e.getSQLState().equals("X0Y32")) {
			exists = true;
		} else {
			exists = false;
		}
		return exists;
	}

	/**
	 * Load admins.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
				Type type = new TypeToken<Collection<Admin>>() {
				}.getType();
				Collection<Admin> admins = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return admins;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Load customers.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Collection<Customer> loadCustomers(InputStream is) throws IOException {
		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder jsonFileContent = new StringBuilder();

				String nextLine = null;
				while ((nextLine = reader.readLine()) != null) {
					jsonFileContent.append(nextLine);
				}
				Gson gson = new Gson();
				Type type = new TypeToken<Collection<Customer>>() {
				}.getType();
				Collection<Customer> customers = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return customers;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Load books.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Collection<Book> loadBooks(InputStream is) throws IOException {
		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder jsonFileContent = new StringBuilder();

				String nextLine = null;
				while ((nextLine = reader.readLine()) != null) {
					jsonFileContent.append(nextLine);
				}
				Gson gson = new Gson();
				Type type = new TypeToken<Collection<Book>>() {
				}.getType();
				Collection<Book> books = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return books;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}

	}

	/**
	 * Load likes.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Collection<server.model.Like> loadLikes(InputStream is) throws IOException {
		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder jsonFileContent = new StringBuilder();
				String nextLine = null;
				while ((nextLine = reader.readLine()) != null) {
					jsonFileContent.append(nextLine);
				}
				Gson gson = new Gson();
				Type type = new TypeToken<Collection<server.model.Like>>() {}.getType();
				Collection<server.model.Like> likes = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return likes;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}

	}
	
	/**
	 * Load owns.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Collection<Owns> loadOwns(InputStream is) throws IOException {
		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder jsonFileContent = new StringBuilder();
				String nextLine = null;
				while ((nextLine = reader.readLine()) != null) {
					jsonFileContent.append(nextLine);
				}
				Gson gson = new Gson();
				Type type = new TypeToken<Collection<Owns>>() {}.getType();
				Collection<Owns> owns = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return owns;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}

	}

	/**
	 * Load reviews.
	 *
	 * @param is input file stream
	 * @return the collection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Collection<Review> loadReviews(InputStream is) throws IOException {
		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder jsonFileContent = new StringBuilder();
				String nextLine = null;
				while ((nextLine = reader.readLine()) != null) {
					jsonFileContent.append(nextLine);
				}
				Gson gson = new Gson();
				Type type = new TypeToken<Collection<Review>>() {}.getType();
				Collection<Review> reviews = gson.fromJson(jsonFileContent.toString(), type);
				reader.close();
				return reviews;
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}

	}

	
}

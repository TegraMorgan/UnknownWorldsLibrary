package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import server.model.*;
import server.response.LogInResponse;
import server.controllers.*;

/**
 * Servlet implementation class LogInServlet.
 */

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new log in servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logInRequest(request, response);
	}

	/**
	 * Log in request.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws JsonSyntaxException the json syntax exception
	 * @throws JsonIOException the json IO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void logInRequest(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new GsonBuilder().create();
		Customer credentials = gson.fromJson(request.getReader(), Customer.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		String data;

		try {// this method connect to the db and check the user data return 1 if user is
				// exist else -1
			LogInResponse resp = new LogInResponse();
			Customer cust;
			cust = CustomerController.getCustomer(credentials.getUsername(),credentials.getPassword());
			if (cust != null) {
				HttpSession session = request.getSession();
				session.setAttribute("customer", cust);
				request.setAttribute("httpSession", session);
				resp.setCustomer(cust);
				resp.setResultSuccess();
				data = resp.tojson();
			} else {
				resp.setResultFail();
				data = resp.tojson();
			}
			printWriter.println(data);
			printWriter.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

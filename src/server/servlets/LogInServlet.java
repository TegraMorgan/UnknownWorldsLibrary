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

/**
 * Servlet implementation class LogInServlet
 */

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logInRequest(request, response);
	}

	private void logInRequest(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new GsonBuilder().create();
		Customer customer = gson.fromJson(request.getReader(), Customer.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		String data;
		try {
			if (customer.getCustomer(customer.getUsername(), customer.getPassword()) > 0) {// this method connect to the
																							// db and check the user
																							// data return 1 if user is
																							// exist else -1
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				// request.setAttribute("httpSession", session);
				String CustomerInJson = gson.toJson(customer, Customer.class);
				data = "{\"result\": \"success\",\"customer\": " + CustomerInJson + "}";
				System.out.println("getting user 1>>>>>>>>>" + data);//////////
			} else {
				data = "{ \"result\": \"fail\"}";
			}
			printWriter.println(data);
			printWriter.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

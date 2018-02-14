package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

import server.model.Customer;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		signUpRequest(request, response);
	}

	private void signUpRequest(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		System.out.println("signUpRequest 1");
		Gson gson = new GsonBuilder().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
		Customer customer = gson.fromJson(request.getReader(), Customer.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		String data;
		try {
			
			// addUser is a method at User class wich add the user to the DB
			System.out.println("signUpRequest 2");
			if (customer.addCustomer() > 0) {
				HttpSession session = request.getSession();
				request.setAttribute("customer", customer);
				session.setAttribute("customer", customer); 
				request.setAttribute("httpSession", session);
				String UserInJson = gson.toJson(customer, Customer.class);
				data = "{\"result\": \"success\",\"user\": " + UserInJson + "}";
			} else {
				data = "{ \"result\": \"fail\"}";
			}
			printWriter.println(data);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

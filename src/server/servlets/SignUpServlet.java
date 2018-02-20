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

import server.controllers.CustomerController;
import server.model.Customer;
import server.response.RegisterResponse;

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
		Customer ncust = gson.fromJson(request.getReader(), Customer.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		String data;
		RegisterResponse resp = new RegisterResponse();
		Customer cust;
		try {
			// addUser is a method at User class which add the user to the DB
			System.out.println("signUpRequest 2");
			ncust.addCustomer();
			cust = CustomerController.getCustomer(ncust.getUsername(), ncust.getPassword());
			if (cust != null) {
				HttpSession session = request.getSession();
				request.setAttribute("customer", cust);
				session.setAttribute("customer", cust);
				request.setAttribute("httpSession", session);
				resp.setResultSuccess();
				resp.setCustomer(cust);
			} else {
				resp.setResultFail();
			}
			printWriter.println(resp.tojson());
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

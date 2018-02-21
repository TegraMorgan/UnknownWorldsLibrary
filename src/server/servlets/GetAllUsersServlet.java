package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.controllers.BookController;
import server.controllers.CustomerController;
import server.model.Book;
import server.model.Customer;
import server.response.GetAllUsersResponse;

/**
 * Servlet implementation class GetAllUsersServlet
 */
@WebServlet("/GetAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleRequest(request, response);	
	}

	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		
		GetAllUsersResponse resp = new GetAllUsersResponse();

		response.setContentType("application/json");
		PrintWriter printWriter = response.getWriter();
		String data;
		try {
			ArrayList<Customer> customers;
			customers= CustomerController.getAllCustomers();
			System.out.println("Got " + customers.size() + " customers");
			if(customers.size() >0)
			{
				HttpSession session = request.getSession();
				request.setAttribute("customers", customers);
				session.setAttribute("customers", customers);
				request.setAttribute("httpSession", session);
				resp.setCustomers(customers);
				resp.setResultSuccess();
				data = resp.tojson();
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else
			{
				resp.setResultFail();
				data = resp.tojson();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			printWriter.println(data);
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}

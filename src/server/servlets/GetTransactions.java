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
import server.controllers.ReviewController;
import server.controllers.TransactionController;
import server.model.Book;
import server.model.Customer;
import server.model.Review;
import server.model.Transaction;
import server.response.GetAllUsersResponse;
import server.response.GetTransactionsResponse;
import server.response.GetUnapprovedReviewsResponse;

/**
 * Servlet implementation class GetAllUsersServlet.
 */
@WebServlet("/GetTransactions")
public class GetTransactions extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new gets the transactions.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		// TODO Auto-generated method stub
		handleRequest(request, response);
	}

	/**
	 * Handle request.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetTransactionsResponse resp = new GetTransactionsResponse();
		response.setContentType("application/json");
		PrintWriter printWriter = response.getWriter();
		try {
			ArrayList<Transaction> transactions;
			transactions = TransactionController.getTransactions();
			System.out.println("Got " + transactions.size() + " reviews");
			response.setStatus(HttpServletResponse.SC_OK);
			HttpSession session = request.getSession();
			request.setAttribute("reviews", transactions);
			session.setAttribute("reviews", transactions);
			request.setAttribute("httpSession", session);
			resp.setTransactions(transactions);
			resp.setResultSuccess();
			printWriter.println(resp.tojson());
		} catch (Exception e) {
			System.out.println(e.toString());
			resp.setResultFail();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			printWriter.println(resp.tojson());
		} finally {
			printWriter.close();
		}
	}
}

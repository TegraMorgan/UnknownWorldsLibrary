package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.controllers.CustomerController;
import server.model.*;
import server.response.BasicResponse;
import server.response.BuyBookResponse;

/**
 * Servlet implementation class addLike.
 *
 * @author toshiba2015
 */
@WebServlet("/AddOwn")
public class addOwnServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
		insertRequest(request, response);
	}

	/**
	 * Insert request.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void insertRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().create();
		Owns own = gson.fromJson(request.getReader(), Owns.class);
		BuyBookResponse resp = new BuyBookResponse();
		try {
			if (own.addToDB() > 0)
			{
				resp.setResultSuccess();
				resp.setCustomer(CustomerController.getCustomer(own.getUid()));
			}
			else
				resp.setResultFail();
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setResultSuccess();
		}
		printWriter.println(resp.tojson());
		printWriter.close();
	}
}

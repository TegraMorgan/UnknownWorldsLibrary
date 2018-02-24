package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.controllers.AdminController;
import server.controllers.CustomerController;
import server.model.*;
import server.response.BasicResponse;
import server.response.DetailedUserResponse;

/**
 * Servlet implementation class addLike.
 */
@WebServlet("/TestForDuplicate")
public class TestDuplicateUsernameLoginServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getDetailedUser(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getDetailedUser(request, response);
	}

	/**
	 * Gets the detailed user.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the detailed user
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void getDetailedUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().create();
		String usn = gson.fromJson(request.getReader(), String.class);
		Customer cust = CustomerController.getCustomerByUsername(usn);
		Admin adm = AdminController.getDupAdmin(usn);
		BasicResponse resp = new BasicResponse();
		try {
			if (cust == null && adm == null)
				resp.setResultSuccess();
			else
				resp.setResultFail();
			printWriter.println(resp.tojson());
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			printWriter.close();
		}
	}

}

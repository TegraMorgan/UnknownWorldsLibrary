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

import server.controllers.CustomerController;
import server.model.*;
import server.response.DetailedUserResponse;

/**
 * Servlet implementation class addLike
 * 
 */
@WebServlet("/DetailedUserByEmail")
public class GetDetailerUserByEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getDetailedUser(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getDetailedUser(request, response);
	}

	private void getDetailedUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			Gson gson = new GsonBuilder().create();
			String email = gson.fromJson(request.getReader(), String.class);
			DetailedUserResponse resp = new DetailedUserResponse();
			resp.setUser(CustomerController.getCustomerByEmail(email));
			if (resp.getUser() != null) {
				resp.setResultSuccess();
				response.setStatus(HttpServletResponse.SC_OK);
				printWriter.println(resp.tojson());
			}
			else response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			printWriter.close();
		}
	}
}

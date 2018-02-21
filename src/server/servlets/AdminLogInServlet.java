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
import server.response.AdminLogInResponse;
import server.controllers.*;


/**
 * Servlet implementation class AdminLogInServlet
 */

@WebServlet("/AdminLogIn")
public class AdminLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		adminlogInRequest(request, response);
	}
	
	private void adminlogInRequest(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new GsonBuilder().create();
		Admin admin = gson.fromJson(request.getReader(), Admin.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		String data;

		try {
			AdminLogInResponse resp = new AdminLogInResponse();
			Admin newAdmin;
			newAdmin = AdminController.getAdmin(admin.getLogin() , admin.getPassword());
			if (newAdmin != null) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", newAdmin);
				request.setAttribute("httpSession", session);
				resp.setAdmin(newAdmin);
				resp.setResultSuccess();
				data = resp.tojson();
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setResultFail();
				data = resp.tojson();
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			printWriter.println(data);
			printWriter.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

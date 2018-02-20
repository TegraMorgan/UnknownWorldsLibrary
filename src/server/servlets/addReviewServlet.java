package server.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.model.*;
import server.response.BasicResponse;

/**
 * Servlet implementation class addLike
 * 
 *  @author toshiba2015
 *  
 */
@WebServlet("/AddReview")
public class addReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertRequest(request, response);
	}

	protected void insertRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().create();
		Review rev = gson.fromJson(request.getReader(), Review.class);
		BasicResponse resp = new BasicResponse();
		try {
			if(rev.addToDB()>0)
			{
				resp.setResultSuccess();
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else 
			{
				resp.setResultFail();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setResultFail();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		printWriter.println(resp.tojson());
		printWriter.close();
	}
}

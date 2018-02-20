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
import server.model.*;
import server.response.*;

/**
 * Servlet implementation class addLike
 * 
 *  @author toshiba2015
 *  
 */
@WebServlet("/AddLike")
public class addLike extends HttpServlet {
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
		Like like = gson.fromJson(request.getReader(), Like.class);
		BasicResponse resp = new BasicResponse();
		try {
			if(like.addLikeToDB()>0)
				resp.setResultSuccess();
			else resp.setResultFail();
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setResultFail();
		}
		printWriter.println(resp.tojson());
		printWriter.close();
	}
}

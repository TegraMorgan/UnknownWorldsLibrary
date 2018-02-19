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
		server.model.Like like = gson.fromJson(request.getReader(), server.model.Like.class);
		String data;
		try {
			if(like.addLikeToDB()>0)
				data="{\"result\": \"success\" }";
			else 
				data="{\"result\": \"fail\"}";
		} catch (SQLException e) {
			e.printStackTrace();
			data="{\"result\": \"fail\"}";
		}
		printWriter.println(data);
		printWriter.close();
	}
}

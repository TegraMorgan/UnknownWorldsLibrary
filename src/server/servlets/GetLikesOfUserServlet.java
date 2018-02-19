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

import server.model.*;

/**
 * Servlet implementation class addLike
 *  
 *  @author toshiba2015
 *  
 */
@WebServlet("/LikesByUid")
public class GetLikesOfUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 GetLikesOfUserRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 GetLikesOfUserRequest(request, response);
	}

	private void GetLikesOfUserRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();	
		Gson gson = new GsonBuilder().create();
		Customer customer = gson.fromJson(request.getReader(), Customer.class);
		String likesInJson="";
			{
				ArrayList<server.model.Like> likes = new ArrayList<server.model.Like>();
				likes=(ArrayList<server.model.Like>) customer.getMyLikes();
				Type type = new TypeToken<ArrayList<Like>>() {}.getType();
				likesInJson = gson.toJson(likes, type);
				
			}
			printWriter.println(likesInJson);
			printWriter.close();
	}

}

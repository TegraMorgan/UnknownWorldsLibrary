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
import server.response.BasicResponse;

/**
 * Servlet implementation class unLike
 *  
 */
@WebServlet("/unLike")
public class unLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deleteRequest(request, response);
	}

	private void deleteRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().create();
		server.model.Like like = gson.fromJson(request.getReader(), server.model.Like.class);
		String data;
		BasicResponse resp = new BasicResponse();
		try {
			if(like.deleteLikeAtDB()>0) 
			{
				resp.setResultSuccess();
				response.setStatus(HttpServletResponse.SC_OK);	
			}
			else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.setResultFail();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setResultFail();
		}
		data=resp.tojson();
		printWriter.println(data);
		printWriter.close();
	}


}

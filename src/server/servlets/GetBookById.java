package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.*;
import server.response.GetAllBooksResponse;
import server.response.GetBookDetailResponse;
import server.controllers.*;

/**
 * Servlet implementation class testServlet.
 */
@WebServlet(urlPatterns = "/GetBookId")
public class GetBookById extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new gets the book by id.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBookById() {
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MMM-dd HH:mm:ss").create();
		response.setContentType("application/json");
		int bid = gson.fromJson(request.getReader(), int.class);
		GetBookDetailResponse resp = new GetBookDetailResponse();
		resp.setBook(BookController.GetBookById(bid));

		if (resp.getBook() != null) {
			response.setStatus(HttpServletResponse.SC_OK);
			resp.setResultSuccess();
			PrintWriter pw = response.getWriter();
			try {
				HttpSession session = request.getSession();
				request.setAttribute("books", resp.getBook());
				session.setAttribute("books", resp.getBook());
				request.setAttribute("httpSession", session);
				pw.println(resp.tojson());
				pw.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				resp.setResultFail();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			resp.setResultFail();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}

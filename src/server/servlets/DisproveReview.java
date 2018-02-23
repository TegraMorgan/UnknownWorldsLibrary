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
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import server.model.Review;
import server.response.BasicResponse;

/**
 * Servlet implementation class ApproveReviewServlet.
 */
@WebServlet("/DisproveReview")
public class DisproveReview extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new disprove review.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DisproveReview() {
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
		handleApprove(request, response);
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
		handleApprove(request, response);
	}

	/**
	 * Testing.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws JsonSyntaxException the json syntax exception
	 * @throws JsonIOException the json IO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void testing(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		BasicResponse resp = new BasicResponse();
		try {
			resp.setResultSuccess();
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setResultFail();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			printWriter.println(resp.tojson());
			printWriter.close();
		}
	}

	/**
	 * Handle approve.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws JsonSyntaxException the json syntax exception
	 * @throws JsonIOException the json IO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handleApprove(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().create();
		Review rev = gson.fromJson(request.getReader(), Review.class);
		BasicResponse resp = new BasicResponse();
		try {
			rev.disprove();
			resp.setResultSuccess();
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setResultFail();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		printWriter.println(resp.tojson());
		printWriter.close();
	}
}

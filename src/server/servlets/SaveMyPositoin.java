package server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

import server.controllers.PositionController;
import server.model.Position;
import server.response.BasicResponse;

/**
 * Servlet implementation class UserServlet.
 */
@WebServlet("/saveMyPosition")
public class SaveMyPositoin extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new save my positoin.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveMyPositoin() {
		super();
		// TODO Auto-generated constructor stub
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
		signUpRequest(request, response);
	}

	/**
	 * Sign up request.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws JsonSyntaxException the json syntax exception
	 * @throws JsonIOException the json IO exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void signUpRequest(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
		Position pos = gson.fromJson(request.getReader(), Position.class);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		BasicResponse resp = new BasicResponse();
		try {
			if (PositionController.savePosition(pos)) {
				System.out.printf("saving position %d \n",pos.getPosition());
				HttpSession session = request.getSession();
				request.setAttribute("httpSession", session);
				resp.setResultSuccess();
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setResultFail();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			printWriter.println(resp.tojson());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			printWriter.close();
		}

	}
}

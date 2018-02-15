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

import server.model.Customer;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("testServlet started");
		Gson gson = new GsonBuilder().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
		Customer cc = gson.fromJson(request.getReader(), Customer.class);
		System.out.printf(" Nickname = %s, Username = %s",cc.getNickname(),cc.getUsername());
		response.setContentType("application/json);charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String data;
		try {
			HttpSession session = request.getSession();
			request.setAttribute("customer", cc);
			session.setAttribute("customer", cc); 
			request.setAttribute("httpSession", session);
			String CustomerInJson = gson.toJson(cc, Customer.class);
			data = "{\"result\": \"success\",\"user\": " + CustomerInJson + "}";
			pw.println(data);
			pw.close();
		} catch (Exception e) {

		}
	}

}

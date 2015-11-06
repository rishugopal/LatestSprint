package com.verizon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class getFieldsListServlet
 */
@WebServlet("/FieldsListServlet")
public class FieldsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FieldsListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("reached here");
		HttpSession session = request.getSession();
		String filePath = session.getAttribute("filePath").toString();
		List<String> ls = FieldsListRetriever.getFieldsList(filePath);

		session.setAttribute("noIds", ls.size());
		session.setAttribute("idsList", ls);

		PrintWriter out = response.getWriter();
		out.println("<center><h3>Assign URLs for context sensitive video help for the form's fields</h3></center>");
		out.println("<form action='CreateResourceFileServlet' method=get>");
		out.println("<table align='center'>");

		for (int i = 0; i < ls.size(); i++) {
			// String idName="id_"+i;
			out.println("<tr align='center'><td>" + ls.get(i) + "<td><td><input type='text' name=" + ls.get(i) + " id="
					+ ls.get(i) + "></td></tr>");

		}

		out.println("<tr><td></td><td><input type='submit' name='submit' value='submit'></td></tr>");
		out.println("</form>");
		out.println("</table>");

		getServletContext().getRequestDispatcher("/fieldsList.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class signup_servlet
 */
@WebServlet("/signup_servlet")
public class signup_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		member_info in = new member_info();
		in.setID(request.getParameter("ID"));
		in.setPassword(request.getParameter("password"));
		in.setName(request.getParameter("name"));
		in.setBirth(request.getParameter("birth"));
		in.setGender(request.getParameter("gender"));
		in.setMail(request.getParameter("mail"));
		in.setPhone(request.getParameter("phone"));
		
		signup info = new signup();
		info.insert(in);
		
		response.sendRedirect("/practice/login_form.jsp");
	
	}
}


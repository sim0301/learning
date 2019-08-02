package bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class write_servlet
 */
@WebServlet("/write_servlet")
public class write_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public write_servlet() {
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
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		session.getAttribute("ID");
		
		try {
		dto content = new dto();
		content.setID((String)session.getAttribute("ID"));
		content.setSubject(request.getParameter("subject"));
		content.setContent(request.getParameter("content"));
		
		dao up = new dao();
		up.write(content);
		
		response.sendRedirect("main.jsp");
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생 ::" +e.getMessage());
		}
	}

}

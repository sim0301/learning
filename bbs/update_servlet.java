package bbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class update_servlet
 */
@WebServlet("/update_servlet")
public class update_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update_servlet() {
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
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
		dto update = new dto();
		update.setSubject(request.getParameter("subject"));
		update.setContent(request.getParameter("content"));
		update.setNum(Integer.parseInt(request.getParameter("num")));
	
		dao up = new dao();
		up.update(update);
		
		response.sendRedirect("/practice/content_servlet?num=" + num);
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생 ::" +e.getMessage());
		}
	
	}

}

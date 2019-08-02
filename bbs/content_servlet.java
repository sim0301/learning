package bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bbs.dao;
/**
 * Servlet implementation class content_servlet
 */
@WebServlet("/content_servlet")
public class content_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public content_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		try {
		dao bbs = new dao();
		dto dto = bbs.getData(num);
		
		request.setAttribute("hit", dto.getHit());
		request.setAttribute("ID", dto.getID());
		request.setAttribute("content", dto.getContent());
		request.setAttribute("subject", dto.getSubject());
		request.setAttribute("date", dto.getDate());
		
		bbs.Hit(num);
		
		RequestDispatcher go = request.getRequestDispatcher("/content.jsp?num=" + num);
        go.forward(request, response);
        
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생 ::" +e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

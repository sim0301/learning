package bbs;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.dao;

/**
 * Servlet implementation class login_servlet
 */
@WebServlet("/login_servlet")
public class login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_servlet() {
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
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			
		String ID = request.getParameter("ID");
		String password = request.getParameter("password");
		
		dao result = new dao();
		int check = result.loginCheck(ID, password);
		
		if(check == 1){
			
			session.setAttribute("ID",ID);
			response.sendRedirect("main.jsp");
			
		}else if(check == 0){
			
			out.println("<script type = \"text/javascript\">");
			out.println("alert('패스워드를 확인하세요');");
			out.println("history.back()");
			out.println("</script>");
			
			
		}else{
			out.println("<script type = \"text/javascript\">");
			out.println("alert('등록되지 않은 아이디입니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생 ::" +e.getMessage());
		}

}
}

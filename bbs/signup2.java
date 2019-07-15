package bbs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signup2
 */
@WebServlet("/signup2")
public class signup2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup2() {
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
		String ID = request.getParameter("ID");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		
		try{
			String dbURL="jdbc:mariadb://localhost:3306/bbs";
			String dbID = "root";
			String dbPassword="0000";
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
			
			System.out.println("접속");
			
			
			
			String SQL = "INSERT INTO member VALUES(?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1,ID);
			pstmt.setString(2,password);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5,gender);
			pstmt.setString(6, mail);
			pstmt.setString(7, phone);
			
			pstmt.executeUpdate();
			System.out.println(name + "님이 회원가입하였습니다.");
			
	
			}catch (SQLException e) {
				System.out.println("데이터 입력 오류.. 원인 ::" +e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("클래시를 찾을 수 없습니다."+e.getMessage());
			} finally{
				if(conn != null)
					try { 
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(pstmt != null)
					try {
						pstmt.close();
					}catch (SQLException e){
						e.printStackTrace();
					}
				}
		}

	
		
		
		// TODO Auto-generated method stub
		
}



package bbs;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class signup {
	
		//데이터베이스 관련 인스턴스
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//DBCP를 이용한 connection 메서드
		public void connect() {
			try {
				Context initContext = new InitialContext();
				DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/bbs");
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//connection 반환 메서드
		public void disconnect() {
			try {
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("데이터베이스 접속 종료");
		}
	
		
	//회원가입
	public void insert(member_info in) {
		
	try {
	
	connect();
	
	
	String SQL = "insert into member values(?,?,?,?,?,?,?)" ;
	
	pstmt = conn.prepareStatement(SQL);
	
	pstmt.setString(1,in.getID());
	pstmt.setString(2,in.getPassword());
	pstmt.setString(3, in.getName());
	pstmt.setString(4, in.getBirth());
	pstmt.setString(5,in.getGender());
	pstmt.setString(6, in.getMail());
	pstmt.setString(7, in.getPhone());
	pstmt.executeUpdate();
	System.out.println(in.getID() + "님의 데이터가 입력되었습니다.");

	}
	catch (SQLException e) {
		System.out.println("데이터 입력 오류.. 원인 ::" +e.getMessage());
	} finally{

		disconnect();
	}
}

	//로그인 체크
	public int loginCheck(String ID, String password)
	{
		
		int check = -1;
		
		try {
			
			connect();
			
			String sql = "select password from member where ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				if(rs.getString(1).equals(password)) {
					check = 1;
					
				}else { 
					check = 0;
				}
			}
		}	catch (SQLException e) {
					System.out.println("데이터 입력 오류.. 원인 ::" +e.getMessage());
				} finally{
					disconnect();
				}
		return check;
		}
	
	
	
	//글쓰기
	public void write(member_info up) {
		
		
		try {
			connect();
			
			String sql = "insert into board values(?,?,?,?,now(),?)" ;
					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 0);
			pstmt.setString(2, up.getSubject());
			pstmt.setString(3, up.getContent());
			pstmt.setInt(4,0);
			pstmt.setString(5, up.getID());
			pstmt.executeUpdate();
			System.out.println(up.getID() + "님의 글이 등록되었습니다.");
			
		}
		catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" +e.getMessage());
		} finally{

			disconnect();
		}
		
	}
	
	
	
	
	
	
	
	
}






	

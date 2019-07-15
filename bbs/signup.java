package bbs;

import java.sql.*;

public class signup {
	private Connection getConnection() throws SQLException{
		 Connection conn = null;
		 try {
				Class.forName("org.mariadb.jdbc.Driver");
				String dbURL="jdbc:mariadb://localhost:3306/bbs";
				String dbID = "root";
				String dbPassword="0000";
				conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		 }catch (ClassNotFoundException e) {
			 System.out.println("드라이버 로딩 실패");
		 }
		 return conn;
	}
	
	public void insert(member_info in) {
		
	PreparedStatement pstmt = null;
	Connection conn = null;
	
	try {
	
	conn = getConnection();
	
	
	
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
}

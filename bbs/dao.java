package bbs;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class dao {

	// 데이터베이스 관련 인스턴스
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// DBCP를 이용한 connection 메서드
	public void connect() {
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/bbs");
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// connection 반환 메서드
	public void disconnect() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	public void insert(dto in) {

		try {

			connect();

			String SQL = "insert into member values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, in.getID());
			pstmt.setString(2, in.getPassword());
			pstmt.setString(3, in.getName());
			pstmt.setString(4, in.getBirth());
			pstmt.setString(5, in.getGender());
			pstmt.setString(6, in.getMail());
			pstmt.setString(7, in.getPhone());
			pstmt.executeUpdate();
			System.out.println(in.getID() + "님의 데이터가 입력되었습니다.");

		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// 로그인 체크
	public int loginCheck(String ID, String password) {

		int check = -1;

		try {

			connect();

			String sql = "select * from member where ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(2).equals(password)) {
					check = 1;

				} else {
					check = 0;
				}
			}
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {
			disconnect();
		}
		return check;
	}

	// 글쓰기
	public void write(dto up) {

		try {
			connect();

			String sql = "insert into board values(?,?,?,?,now(),?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 0);
			pstmt.setString(2, up.getSubject());
			pstmt.setString(3, up.getContent());
			pstmt.setInt(4, 0);
			pstmt.setString(5, up.getID());
			pstmt.executeUpdate();
			System.out.println(up.getID() + "님의 글이 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}

	}

	// 글 목록 가져오기
	public ArrayList<dto> getList(String search, String search_text) {
		ArrayList<dto> list = new ArrayList<dto>();
		try {
			connect();

			if (search_text == null || search_text == "") {
				String sql = "select * from board order by num desc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else {
				String sql = "select * from board where " + search + " like '%" + search_text + "%' order by num desc;";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				dto bbs = new dto();
				bbs.setNum(rs.getInt(1));
				bbs.setSubject(rs.getString(2));
				bbs.setContent(rs.getString(3));
				bbs.setHit(rs.getInt(4));
				bbs.setDate(rs.getString(5));
				bbs.setID(rs.getString(6));
				list.add(bbs);
			}
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
		return list;
	}

	// 글 읽기
	public dto getData(int num) {
		dto data = new dto();

		try {
			connect();

			String sql = "select * from board where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setSubject(rs.getString("subject"));
				data.setContent(rs.getString("content"));
				data.setHit(rs.getInt("hit"));
				data.setDate(rs.getString("date"));
				data.setID(rs.getString("ID"));

			} else {
				num = 0;
			}
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
		return data;
	}

	// 조회수
	public void Hit(int num) {
		try {
			connect();

			String sql = "update board set hit=hit+1 where num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {
			disconnect();

		}
	}

	// comment 작성
	public void comment(dto comment) {
		try {
			connect();

			String sql = "insert into comment values(?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 0);
			pstmt.setString(2, comment.getComment_content());
			pstmt.setString(3, comment.getID());
			pstmt.setInt(4, comment.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// comment 삭제
	public void deleteComment(int comment_num) {
		try {
			connect();

			String sql = "delete from comment where comment_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// comment 불러오기
	public ArrayList<dto> getComment(int num) {

		ArrayList<dto> comment_list = new ArrayList<dto>();

		try {
			connect();

			String sql = "select * from comment where num = " + num + " order by comment_num asc;";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto comment = new dto();
				comment.setComment_num(rs.getInt(1));
				comment.setComment_content(rs.getString(2));
				comment.setComment_date(rs.getString(3));
				comment.setID(rs.getString(4));
				comment.setNum(rs.getInt(5));
				comment_list.add(comment);

			}
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
		return comment_list;
	}

	// 글 삭제
	public void delete(int num) {
		try {
			connect();

			String sql = "delete from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// 글 수정
	public void update(dto up) {
		try {
			connect();

			String sql = "update board set subject = ?, content = ? where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, up.getSubject());
			pstmt.setString(2, up.getContent());
			pstmt.setInt(3, up.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// 재정렬
	public void lineUp() {
		try {
			connect();

			String sql = "set @count = 0";
			String sql2 = "update board set num = @count:=@count+1;";
			String sql3 = "alter table board auto_increment=1";
			pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql2);
			pstmt = conn.prepareStatement(sql3);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
	}

	// 전체 게시물 수
	public int total(String search, String search_text) {
		int totalCount = 0;
		try {
			connect();
			if (search_text == null || search_text == "") {
				String sql = "select count(*) from board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} else {
				String sql = "select count(*) from board where " + search + " like '%" + search_text
						+ "%' order by num desc;";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(totalCount);
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}
		return totalCount;
	}

	
	public void info(String ID) {

		try {
			connect();

			String sql = "select * from member where ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto vo = new dto();

				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setMail(rs.getString("mail"));
				vo.setPhone(rs.getString("phone"));

			}
		} catch (SQLException e) {
			System.out.println("데이터 입력 오류.. 원인 ::" + e.getMessage());
		} finally {

			disconnect();
		}

	}

}

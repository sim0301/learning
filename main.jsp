<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.dao"%>
<%@ page import="java.util.*"%>
<%@ page import="bbs.dto"%>
<%@ page import="bbs.search_servlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>

<script type="text/javascript">
	function goWrite() {
		location.href = "write_form.jsp";
	}
</script>
<script type="text/javascript">
	function check() {
		if (!document.search_form.search_text.value
				|| document.search_form.search_text.value.indexOf(" ") >= 0) {
			alert("검색어를 입력하세요");

			return false;
		}
		document.search_form.submit();
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String ID = (String) session.getAttribute("ID");
		String search = "";
		String search_text = "";

		if (request.getParameter("search_text") != null) {
			search = request.getParameter("search");
			search_text = (String) request.getParameter("search_text");
		}
	%>


	<jsp:include page="top.jsp" flush="false" /><br>

	<form name="search_form" method="get" action="search_servlet">

		<select name="search">
			<option value="subject">제목</option>
			<option value="ID">작성자</option>
			<option value="content">내용</option>
		</select> <input type="text" ID="search_text" name="search_text"> <input
			type="submit" value="검색" onClick="check()">
	</form>
	<br>



	<table width="100%" border="0" cellpadding="3" cellspacing="0">
		<tr bgcolor="#c0c0c0" height="120%" class="bold">
			<td align="center" width="50">번호</td>
			<td align="center" width="300">제목</td>
			<td align="center" width="50">이름</td>
			<td align="center" style="width: 10%">날짜</td>
			<td align="center" width="50">조회수</td>
		</tr>
		<%
			dao dao = new dao();
			ArrayList<dto> list = dao.getList(search, search_text);
			int nowPage = 0;
			if (request.getParameter("nowPage") == null) {
				nowPage = 1;
			} else {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));//현재 페이지
			}

			int countList = 5;//한 화면에 표시될 게시물 수
			int countPage = 5;//한 화면에 표시될 페이지 수

			//게시물
			int totalCount = dao.total(search, search_text); //전체 게시물 수 
			int beginNum = (nowPage - 1) * countList;//페이지에서 시작될 게시물 번호
			int endNum = beginNum + countList;
			//페이지
			int totalPage = totalCount / countList;//전체 페이지 수
			/* int startPage = ((nowPage - 1)/countPage) * countPage + 1; */
			int startPage = nowPage - (countPage / 2);
			int endPage = startPage + countPage - 1;
			int nextPage = startPage + countPage;

			if (nowPage <= (countPage / 2)) {
				startPage = 1;
				endPage = startPage + countPage - 1;
			}
			if (totalCount % countList > 0) {
				totalPage++;
			}
			if (totalPage < nowPage) {
				nowPage = totalPage;
			}
			if (endPage > totalPage) {
				endPage = totalPage;
			}
		%>


		<%
			if (list.isEmpty()) {
		%>
		<tr>
			<td align="center" colspan=5>등록된 글이 없습니다.</td>
		</tr>

		<%
			} else {
				if (totalCount < endNum) {
					endNum = totalCount;
				}

				for (int i = beginNum; i < endNum; i++) {
		%>
		<tr align="center">
			<td><%=list.get(i).getNum()%></td>
			<td><a href="content_servlet?num=<%=list.get(i).getNum()%>"><%=list.get(i).getSubject()%></a></td>
			<td><%=list.get(i).getID()%></td>
			<td><%=list.get(i).getDate()%></td>
			<td><%=list.get(i).getHit()%></td>
		</tr>
		<%
			}
			}
		%>

	</table>
	<br>
	<table width="100%" border="0" cellpadding="3" cellspacing="3">
		<tr align="center">
			<td>
				<%
					if (nowPage != 1) {
				%> <a
				href="main.jsp?nowPage=<%=nowPage - 1%>&search=<%=search%>&search_text=<%=search_text%>">이전
					페이지</a> <%
 	}
 	for (int i = startPage; i <= endPage; i++) {
 %> <a
				href="main.jsp?nowPage=<%=i%>&search=<%=search%>&search_text=<%=search_text%>"><%=i%></a>
				<%
					}
					if (nowPage != totalPage) {
				%><a
				href="main.jsp?nowPage=<%=nowPage + 1%>&search=<%=search%>&search_text=<%=search_text%>">다음
					페이지</a> <%
 	}
 %>
			</td>
		</tr>
		<tr>
			<td colspan="4" height="5"></td>
		</tr>
		<tr align="center">
			<td><input type="button" value="글쓰기" onClick="goWrite()">
			</td>
		</tr>
	</table>

</body>
</html>
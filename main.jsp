<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<% 
request.setCharacterEncoding("utf-8");
String ID = (String)session.getAttribute("ID");
%>
<script>
function goWrite(){
	location.href = "write_form.jsp";
}
</script>
<jsp:include page="top.jsp" flush="false"/>


<table width=100% border=0 cellpadding=3 cellspacing=0>
<tr bgcolor=#c0c0c0 height=120% class="bold">
<td align="center" width=50>번호</td>
<td align="center" width=300>제목</td>
<td align="center" width=50>이름</td>
<td align="center" width=80>날짜</td>
<td align="center" width=50>조회수</td>
</tr>



</table>

<input type="button" value="글쓰기" onClick="goWrite()">


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bbs.dto" %>
<%@ page import = "bbs.dao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	if(session.getAttribute("ID") == null){
%>
<script type = "text/javascript">
alert("로그인 하세요");
history.go(-1);
</script>
<%
	}
%>
<%
	request.setCharacterEncoding("utf-8");
int num = Integer.parseInt(request.getParameter("num"));
dao dao = new dao();
dto data = dao.getData(num);

String ID = data.getID();
String subject = data.getSubject();
String content = data.getContent();
%>

</head>
<body>
<script type="text/javascript">
function Update(){
	location.href = "update_servlet?num=" + <%=num %>;
}
</script>
<jsp:include page="top.jsp" flush="false"/>
<br>

<form name="update_form" method="post" action="update_servlet?num=<%=num%>">
<table width="600" align="center" cellspacing="0" cellpadding="3">
<tr>
<td bgcolor="#c0c0c0" height="25" align="center" class="bold">

<font>수정하기</font>
</td></tr><tr>
<br><br><br>
<td align="center">
<table border="0" width="100%" align="center">
<tr>

<td width="10%">아이디</td>
<td width="90%"><%=session.getAttribute("ID") %> 
</td></tr><tr>

<td>제목</td>
<td><input type="text" id="subject" name="subject" size="60" maxlength="40" value = "<%=subject %>">

</td></tr><tr>

<td>내용</td>
<td><textarea id="content" name="content" rows="20" cols="61"><%=content%></textarea>
</td></tr><tr>

<td colspan=2  align="center">
<input type="submit" value="수정하기">
<input type="button" value="다시쓰기" onClick="reset()">
<input type="button" value="뒤로가기" onClick="history.back()">
</td>
</tr>
</table>
</td></tr>
</table>

</form>

</body>
</html>
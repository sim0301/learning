<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1><a href = "main.jsp">SIM&nbsp;BBS</a></h1> 

<%
if(session.getAttribute("ID") == null){
	%>
<span style = "float:right;"><a href = "login_form.jsp">로그인</a> </span>
<% 
}else{
%>
<span style="float:right;"><%=session.getAttribute("ID") %> 님         <a href = "logout_servlet">로그아웃</a>
<br><br></span>
<%} 
%>

</body>
</html>
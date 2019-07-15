<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="login_form" method="post" action="">
<table align = "center" border ="1" cellspacing="0" cellpadding="5">
<tr>
<td align = "center" colspan="2"><b>로그인</b></td>
</tr>
<tr>
<td>아이디 </td>
<td><input type="text" name="ID" size = "10"></td>
</tr>
<tr>
<td>패스워드 </td>
<td><input type="password" name="pass" size="10"></td>
</tr>
<tr>
<td align = "center" colspan="1">
<input type="button" value="로그인"></td>

<td align = "center" colspan="1">
<input type="button" value="회원가입" onClick="location='signup_form.jsp'"></td>
</tr>
	
</table>
</form>

</body>
</html>
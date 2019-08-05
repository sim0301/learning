<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>

</head>
<body>
<script type = "text/javascript">
function check(){
	if(!document.login_form.ID.value || document.login_form.ID.value.indexOf(" ")>=0) {
		alert("ID를 입력하세요");
	
		return false;
	}else if(!document.login_form.password.value || document.login_form.password.value.indexOf(" ")>=0) {
		alert("password를 입력하세요");
	
		return false;
	}
	document.login_form.submit();
}
</script>

<script type = "text/javascript">
function goSignup(){
	location.href = "signup_form.jsp";
}
</script>


<form name="login_form" method="post" action="login_servlet" >
<table align = "center" border ="1" cellspacing="0" cellpadding="5">
<tr>
<td align = "center" colspan="2"><b>로그인</b></td>
</tr>
<tr>
<td>아이디 </td>
<td><input type="text" ID= "ID" name="ID" size = "10"></td>
</tr>
<tr>
<td>패스워드 </td>
<td><input type="password" ID="password" name="password" size="10"></td>
</tr>
<tr>
<td align = "center" colspan="1" >
<input type="button" value="로그인"  onClick="check()"></td>

<td align = "center" colspan="1" >
<input type="button" value="회원가입" onClick="goSignup()"></td>
</tr>
	
</table>
</form>

</body>
</html>
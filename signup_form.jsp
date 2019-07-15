<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
</head>
<body>

<form name="signup_form" method="post" action="signup_servlet">


<h1>회원가입</h1>
<hr>

아이디<br>
<input type="text" name="ID" size = "8">
<br>

비밀번호 <br>
<input type="password" name="password" size="8">
<br>
이름<br>
<input type="text" name="name" size = "8">
<br>
생년월일<br>
<input type="text" name="birth" size = "8">
<br>

휴대전화<br>
<input type="text" name="phone" size = "8">
<br>
성별<br> <select name="gender">
<option value="M"> 남자 </option>
<option value="F"> 여자 </option>
</select>
<br>
메일<br>
<input type="email" name="mail" size = "17">
<br>
<br>
<input type="submit" value="회원가입">

</form>


</body>

</html>
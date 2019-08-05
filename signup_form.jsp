<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
</head>
<body>
<script type = "text/javascript">
function check(){
	form = document.signup_form
	if(!form.ID.value || form.ID.value.indexOf(" ")>=0){
		alert("ID를 입력하세요");
		
		return false;
	}
	else if(!form.password.value || form.ID.password.indexOf(" ")>=0){
		alert("패스워드를 입력하세요");
		
		return false;
	}
	else if(!form.name.value || form.name.value.indexOf(" ")>=0){
		alert("이름을 입력하세요");
		
		return false;
	}
	else if(!form.birth.value || form.birth.value.indexOf(" ")>=0){
		alert("생년월일을 입력하세요");
		
		return false;
	}
	else if(!form.phone.value || form.phone.value.indexOf(" ")>=0){
		alert("전화번호를 입력하세요");
		
		return false;
	}
	else if(!form.mail.value || form.mail.value.indexOf(" ")>=0){
		alert("메일을 입력하세요");
		
		return false;
	} 
	form.submit();
}
</script>

<form name="signup_form" method="post" action="signup_servlet">
<h1>회원가입</h1>
<hr>

* 아이디<br>
<input type="text" ID="ID" name="ID" size = "15">
<br>

* 패스워드 <br>
<input type="password" ID="password" name="password" size="15">
<br>
* 이름<br>
<input type="text" ID="name" name="name" size = "15">
<br>
* 생년월일<br>
<input type="text" ID="birth" name="birth" size = "15">
<br>
* 휴대전화<br>
<input type="text" id="phone" name="phone" size = "15">
<br>
* 성별<br> <select id="gender" name="gender" >
<option value="M"> 남자 </option>
<option value="F"> 여자 </option>
</select>
<br>
* 메일<br>
<input type="email" id="mail" name="mail" size = "15">
<br>
<br>
<input type="button" value="회원가입" onClick="check()">

<input type="button" value="뒤로가기" onClick="history.back()">

</form>


</body>

</html>
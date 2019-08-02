<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "bbs.dto" %>
<%@ page import = "bbs.dao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("ID") == null){
%>
<script type = "text/javascript">
alert("로그인 하세요");
history.go(-1);
</script>
<%}else {
request.setCharacterEncoding("utf-8");
int num = Integer.parseInt(request.getParameter("num"));
String ID = (String)request.getAttribute("ID");
String date = (String)request.getAttribute("date");
String subject = (String)request.getAttribute("subject");
String content = (String)request.getAttribute("content");
int hit =(Integer)request.getAttribute("hit");

dao dao = new dao();
ArrayList<dto> comment_list = dao.getComment(num);
%>
<script language = "javascript">
function goDelete(){
	ok = confirm("정말 삭제하시겠습니까?");
	if(ok){
	location.href = "delete_servlet?num=" + <%=num %>;
	} else {
		return false;
	}
}
</script>
<script language= "javascript">
function goUpdate(){
	location.href = "update.jsp?num=" + <%=num %>;
}
</script>
<jsp:include page="top.jsp" flush="false"/><br>
<br><br><br>
<table align = "center" width ="600" border="1" cellspacing="0" cellpadding="3">
<tr>
<td align = "center" bgcolor="#c0c0c0" height="25" class=bold>글 읽기</td>
</tr><tr>
<td>
<table width="600" border="0" cellspacing="0" cellpadding="3">
<tr>
<td align = "center" width = "20" bgcolor="#c0c0c0">글 번호</td>
<td align = "center" width = "30"><%=num %></td>
<td align = "center" width = "10" bgcolor="#c0c0c0">조회수</td>
<td align = "center" width = "40"><%=hit %></td>
</tr>
<tr>
<td align = "center" width = "10" bgcolor="#c0c0c0">ID</td>
<td align = "center" width = "40"><%=ID %></td>
<td align = "center" width = "10" bgcolor="#c0c0c0">등록일</td>
<td align = "center" width = "40"><%=date %></td>
</tr>
<tr>

<td align = "center" style= "width : 13%" bgcolor="#c0c0c0">제목</td>
<td colspan="3" align = "left"><%= subject %></td>
</tr>


</table>

<tr><td height ="350" style="word-break:break-all">
<br><%= content.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") %><br></td></tr>
<tr><td>

<table width="600" border="0" cellspacing="0" cellpadding="0">
<tr>

<%
for(int i= 0; i< comment_list.size() ;i++){
%>
	<tr>
<td align = "left" width = "10" ><%=comment_list.get(i).getID() %></td>
<td align = "right" width = "10"><%=comment_list.get(i).getComment_date() %></td></tr>
<tr>
<td align = "left"><%=comment_list.get(i).getComment_content() %></td>
<%
if(session.getAttribute("ID").equals(comment_list.get(i).getID())){
%>
<td align = "right"><a href = "comment_delete_servlet?num=<%=num%>&comment_num=<%=comment_list.get(i).getComment_num() %>">삭제</a>
<%
}%>
</td></tr>
<tr><td colspan="2" height="2" bgcolor="#c0c0c0"></td></tr>
<%
}
%>
</table>

<!-- 댓글달기 -->
<script type="text/javascript">
function goComment(){
	if(!document.comment_form.comment_content.value || document.comment_form.comment_content.value.indexOf(" ")>=0){
		alert("내용을 입력하세요");
		
		return false;
	}
	document.comment_form.submit();
}
</script>
<form name="comment_form" method="post" action="comment_servlet?num=<%=num%>">
<table border = "1" bgcolor="#C0C0C0" width ="600" height="80" align="center" cellspacing="2">
<tr>
<td><textarea id="comment_content" style = "resize:none" name="comment_content" rows="5" cols="70"></textarea>
</td>
<td><input type="button" value="댓글달기" onClick="goComment()" ></td>
</tr>
</table>
</form></td>
</tr>
<tr>
<td align="center" >
<input type = "button" value ="뒤로가기" onClick="history.back()">
<%
if(session.getAttribute("ID").equals(ID)){
%>

<input type = "button" value ="수정하기" onClick = "goUpdate()">
<input type = "button" value ="삭제하기" onClick = "goDelete()"></td>
</tr>
<%
}
}
%>
</table>

</body>
</html>
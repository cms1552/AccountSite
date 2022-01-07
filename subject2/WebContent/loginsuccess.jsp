<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="./css/common.css" rel="stylesheet">
<title>welcome</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
	if(session==null || session.getAttribute("id")==null){
		response.sendRedirect("index.jsp");
	}
	String id = (String)session.getAttribute("id");
%>
<div class="container row text-center"">
	<h1>ȯ���մϴ�. <%=id %>!</h1>
</div>
<br>
<div class="container text-center" style="margin-top: 100px;">
	<button class="btn btn-lg btn-primary btn-block" type="button" name="logout" onclick="location='Logout'">�α׾ƿ�</button>
	<button class="btn btn-lg btn-primary btn-block" type="button" name="SignOut" onclick="location='SignOut'">ȸ��Ż��</button>
	<button class="btn btn-lg btn-primary btn-block" type="button" name="updateUser" onclick="location='modify.jsp'">ȸ������</button>
	<!-- <button class="btn btn-lg btn-primary btn-block" type="button" name="updateAccount" onclick="location='/subject2/AccountSelect'" >�뵷����</button> -->
	<button class="btn btn-lg btn-primary btn-block" type="button" name="updateAccount" onclick="location='shownote.jsp'" >�뵷����</button>
</div>
</body>
</html>
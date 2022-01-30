<!-- 로그인 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="./css/signin.css" rel="stylesheet">
<link href="./css/common.css" rel="stylesheet">


<title>money money</title>

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">

		<form class="form-signin" name="signin" method="post" action="SignIn">
			<h2 class="form-signin-heading">로그인</h2>
			<label class="sr-only">아이디</label> 
			<input type="text" name="id" class="form-control" required autofocus> 
			
			<label for="inputPassword" class="sr-only">비밀번호</label> 
			<input type="password" name="password" class="form-control" placeholder="Password" required>
			
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<div>
			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="loginCheck()">로그인</button>
			<a href="signUp.jsp"><button type="button" class="btn btn-lg btn-block btn-primary">회원 가입</button></a>
			</div>
		</form>
		
	</div>
</body>
<script>
	function loginCheck(){
		var form = document.signin;
		var id = form.id.value;
		var password = form.password.value;
		var httpRequest = new XMLHttpRequest();
		var param = 'id='+id+'&password='+password;
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState == 4){
				var resultText = httpRequest.responseText;
				//alert(resultText);
				if(resultText == 0){
					alert("아이디가 존재하지 않습니다.");
					return false;
				}else if(resultText == 1){
					alert("아이디와 패스워드가 일치하지 않습니다.");
					return false;
				}else{
					form.submit();
				}
			}
		}
		//외부접속에서 로그인이 안되길래 봤더니 localhost임 -> 변경 완료
		httpRequest.open("POST", "./Login", true);
		//httpRequest.open("POST", "http://localhost:8000/subject2/Login", true);
		httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		httpRequest.send(param);
	}
</script>
</html>
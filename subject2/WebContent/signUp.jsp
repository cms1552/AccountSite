<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style rel="stylesheet" src="css/signup.css"></style>

<meta charset="EUC-KR">
<title>Sign Up</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="bg-light">
	<jsp:include page="header.jsp" />
	<div class="container">
	<main>
			<div class="py-5 text-center">
				<h2>회원 가입</h2>
			</div>

	<div class="row g-5">

			<div class="col-md-7 col-lg-8">

				<form class="needs-validation" name="signup" method="post" action="SignUp">
					<div class="row g-3">
							<div class="col-sm-6">
								<label for="firstName" class="form-label">이름</label> <input
									type="text" class="form-control" name="name" required>
							</div>

							<div class="col-12">
								<label for="id" class="form-label">아이디</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" name="id" onkeydown="inputIdChk()" required>
									<input type="button" value="중복 확인" onclick="openIdChk()">
									<input type="hidden" value="idUncheck" name="idDuplication" >
								</div>
							</div>

							<div class="col-12">
								<label for="password" class="form-label">비밀번호</label> <input
									type="password" class="form-control" name="password" required>
							</div>

							<div class="col-12">
								<label for="pwcheck" class="form-label">비밀번호 확인</label> <input
									type="text" class="form-control" name="pwcheck">
							</div>

							<div class="col-12">
								<label for="email" class="form-label">Email <span
									class="text-muted">(Optional)</span></label> <input type="email"
									class="form-control" name="email" placeholder="you@example.com">
							</div>

							<div class="col-md-4">
								<label class="form-label">핸드폰 번호</label> <select
									class="form-select" name="phone1" required>
									<option value="">Choose...</option>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="019">019</option>
								</select>
							</div>

							<div class="col-md-4">
								<label class="form-label"></label> <input type="text"
									class="form-control" name="phone2" maxlength="4" required>
							</div>

							<div class="col-md-3">
								<label class="form-label"></label> <input type="text"
									class="form-control" name="phone3" maxlength="4" required>
							</div>
						</div>

						<hr class="my-4">

						<h4 class="mb-3">성 별</h4>

						<div class="my-3">
							<div class="form-check">
								<input id="man" name="sex" type="radio" class="form-check-input"
									checked required> <label class="form-check-label"
									for="sex">남 자</label>
							</div>
							<div class="form-check">
								<input id="woman" name="sex" type="radio"
									class="form-check-input" required> <label
									class="form-check-label" for="sex">여 자</label>
							</div>
						</div>


				<hr class="my-4">

				<button class="w-50 btn btn-primary btn-md" type="button" onclick="checkForm()">회원
					가입</button>
				</form>
						
			</div>
	</div>
	</main>

	<footer class="my-5 pt-5 text-muted text-center text-small">
		<p class="mb-1">&copy; 2017–2021 Company Name</p>
		<ul class="list-inline">
			<li class="list-inline-item"><a href="#">Privacy</a></li>
			<li class="list-inline-item"><a href="#">Terms</a></li>
			<li class="list-inline-item"><a href="#">Support</a></li>
		</ul>
	</footer>
	</div>


	<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

	<script type="text/javascript" src="./js/form-validation.js"></script>

</body>
</html>
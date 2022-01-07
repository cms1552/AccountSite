/**
 * 
 */
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
	function checkForm(){
		var form = document.signup;
		if(form.pwcheck.value==""){
			alert("비밀번호 확인을 입력해주세요.");
			signup.pwcheck.focus();
			return false;
		}
		if(form.name.value.length < 2 || form.name.value.length > 5){
			alert("이름 길이가 맞지 않습니다.");
			form.name.focus();
			return false;
		}
		if(form.id.value.length > 10){
			alert("아이디 길이가 너무 깁니다.");
			form.id.focus();
			return false;
		}
		if(form.password.value.length < 6 || form.password.value.length > 10){
			alert("패스워드는 6자에서 10자 사이로 설정 해야 합니다.");
			form.password.focus();
			return false;
		}
		if(form.password.value != form.pwcheck.value){
			alert("비밀번호 확인이 맞지 않습니다.");
			form.pwcheck.focus();
			return false;
		}
		
		if(form.idDuplication.value != 'idCheck'){
			alert("form.idDuplication.value : " + form.idDuplication.value);
			//alert("아이디 중복확인을 해주세요.");
			return false;
		}
		if (isNaN(form.phone2.value) || isNaN(form.phone3.value)){
			alert("핸드폰 번호는 숫자만 입력 가능합니다.");
		}
		alert("submit success");
		form.submit();
	}
	function inputIdChk(){
		document.signup.idDuplication.value = "idUncheck";
	}
	function openIdChk(){
		window.name = "parentForm";
		window.open("idCheckForm.jsp", "chkForm", "width=500, height=300, resizable=no, scorllbars=no");
	}
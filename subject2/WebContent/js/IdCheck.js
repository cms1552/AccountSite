/**
 * 
 */
function pValue(){
	document.getEelementById("userId").value = opener.document.signup.id.value;
}
function idCheck(){
	var id = document.getElementById("userId").value;
	if(!id){
		alert("아이디를 입력하지 않았습니다.");
		return false;
	}
	else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){
		alert("한글 및 특수문자는 아이디로 사용할 수 없습니다.");
		return false;
	}else{
		var param="id="+id

		httpRequest = new XMLHttpRequest();

		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState == 4){
				var resultText = httpRequest.responseText;

				if(resultText == 0){
					alert("사용할 수 없는 아이디입니다.");
					document.getElementById("cancelBtn").style.visibility="visible";
					document.getElementById("useBtn").style.visibility='hidden';
					document.getElementById("msg").innerHTML="";
				}
				else if(resultText == 1){
					document.getElementById("cancelBtn").style.visibility="hidden";
					document.getElementById("useBtn").style.visibility='visible';
					document.getElementById("msg").innerHTML="사용 가능한 아아디입니다.";			
				}
			}
		}
		httpRequest.open("POST", "/subject2/IdCheck", true);
		httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		httpRequest.send(param);
	}
}

function sendCheckValue(){

	opener.document.signup.idDuplication.value = "idCheck";
	opener.document.signup.id.value = document.getElementById("userId").value;
	if(opener!=null){
		opener.chkForm = null;
		self.close();
	}
}
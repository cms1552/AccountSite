/**
 * 
 */
function noteDel(obj){
		var id = obj.value;
		//id = id.substr(1,2); 이렇게 하면 번호가 십의자리 숫자 이상이 되면 오류 발생 ex)d10, d11, d12...
		id = id.substr(1);
		var param = 'id='+id;
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/subject2/AccountDelete", true);
		//xhr.open("post", "http://localhost:8000/subject2/AccountDelete", true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send(param);
		location.reload(true);
	}


	function confirmBtn(obj){
		var cfId = obj.value;
		var cf = document.getElementById(cfId);
		cf.innerHTML = "y";
		//obj.innerHTML = "y"
		var no = cfId.substring(2);
		var param = 'no='+no;
		var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			
		}
		xhr.open("POST", "/subject2/AccountSelect", true);
		//xhr.open("POST", "http://localhost:8000/subject2/AccountSelect", true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send(param);
		
	}
	
	function insertNote(){
		if(document.getElementById("1")==null && document.getElementById("2")==null){
			var form = document.createElement("form");
			form.setAttribute("id", "1");
			form.setAttribute("class", "form");
			form.setAttribute("charset", "utf-8");
			form.setAttribute("method", "post");
			form.setAttribute("action", "/subject2/AccountInsert");
			
			/*
			//No
			
			//<div class="input-group mb-3">
			var NoGroup = document.createElement("div");
			NoGroup.setAttribute("class", "input-group mb-3");

			
			//<span class="input-group-text" id="basic-addon1">@</span>
			var NoSpan = document.createElement("span");
			NoSpan.setAttribute("class", "input-group-text");
			NoSpan.innerHTML = "No";
			NoGroup.appendChild(NoSpan);
			
			var NoField = document.createElement("input");
			NoField.setAttribute("aria-label", "No");
			NoField.setAttribute("class", "form-control");
			NoField.setAttribute("type", "text");
			NoField.setAttribute("name", "no");

			NoGroup.appendChild(NoField);
			
			form.appendChild(NoGroup);
			*/
			
			//email
			var EmailGroup = document.createElement("div");
			EmailGroup.setAttribute("class", "input-group mb-3");

			var EmailSpan = document.createElement("span");
			EmailSpan.setAttribute("class", "input-group-text");
			EmailSpan.innerHTML = "Email";
			
			var EmailField = document.createElement("input");
			EmailField.setAttribute("aria-label", "Email");
			EmailField.setAttribute("class", "form-control");
			EmailField.setAttribute("type", "text");
			EmailField.setAttribute("name", "email");
			
			EmailGroup.appendChild(EmailSpan);
			EmailGroup.appendChild(EmailField);
			form.appendChild(EmailGroup);
			
			//money
			var MoneyGroup = document.createElement("div");
			MoneyGroup.setAttribute("class", "input-group mb-3");

			var MoneySpan = document.createElement("span");
			MoneySpan.setAttribute("class", "input-group-text");
			MoneySpan.innerHTML = "Money";
			
			var MoneyField = document.createElement("input");
			MoneyField.setAttribute("aria-label", "Money");
			MoneyField.setAttribute("class", "form-control");
			MoneyField.setAttribute("type", "text");
			MoneyField.setAttribute("name", "money");
			
			MoneyGroup.appendChild(MoneySpan);
			MoneyGroup.appendChild(MoneyField);
			form.appendChild(MoneyGroup);
			
			//ino
			var InoGroup = document.createElement("div");
			InoGroup.setAttribute("class", "input-group mb-3");

			var InoSpan = document.createElement("span");
			InoSpan.setAttribute("class", "input-group-text");
			InoSpan.innerHTML = "Ino";
			
			var InoField = document.createElement("input");
			InoField.setAttribute("aria-label", "Money");
			InoField.setAttribute("class", "form-control");
			InoField.setAttribute("type", "text");
			InoField.setAttribute("name", "Ino");
			
			InoGroup.appendChild(InoSpan);
			InoGroup.appendChild(InoField);
			form.appendChild(InoGroup);
			
			//note
			var NoteGroup = document.createElement("div");
			NoteGroup.setAttribute("class", "input-group mb-3");

			var NoteSpan = document.createElement("span");
			NoteSpan.setAttribute("class", "input-group-text");
			NoteSpan.innerHTML = "Note";
			
			var NoteField = document.createElement("input");
			NoteField.setAttribute("aria-label", "Money");
			NoteField.setAttribute("class", "form-control");
			NoteField.setAttribute("type", "text");
			NoteField.setAttribute("name", "Note");
			
			NoteGroup.appendChild(NoteSpan);
			NoteGroup.appendChild(NoteField);
			form.appendChild(NoteGroup);
			
			//iodate
			var IoDateGroup = document.createElement("div");
			IoDateGroup.setAttribute("class", "input-group mb-3");

			var IoDateSpan = document.createElement("span");
			IoDateSpan.setAttribute("class", "input-group-text");
			IoDateSpan.innerHTML = "IoDate";
			
			var IoDateField = document.createElement("input");
			IoDateField.setAttribute("aria-label", "Money");
			IoDateField.setAttribute("class", "form-control");
			IoDateField.setAttribute("type", "text");
			IoDateField.setAttribute("name", "IoDate");
			
			IoDateGroup.appendChild(IoDateSpan);
			IoDateGroup.appendChild(IoDateField);
			form.appendChild(IoDateGroup);
			
			//memo
			var MemoGroup = document.createElement("div");
			MemoGroup.setAttribute("class", "input-group mb-3");

			var MemoSpan = document.createElement("span");
			MemoSpan.setAttribute("class", "input-group-text");
			MemoSpan.innerHTML = "Memo";
			
			var MemoField = document.createElement("input");
			MemoField.setAttribute("aria-label", "Money");
			MemoField.setAttribute("class", "form-control");
			MemoField.setAttribute("type", "text");
			MemoField.setAttribute("name", "Memo");
			
			MemoGroup.appendChild(MemoSpan);
			MemoGroup.appendChild(MemoField);
			form.appendChild(MemoGroup);
			

			var submitBtn = document.createElement("input")
			submitBtn.setAttribute("type", "submit");
			submitBtn.setAttribute("class", "btn btn-light btn-lg");
			
			form.appendChild(submitBtn);
			
			document.body.appendChild(form);
		}else{
			var form = document.getElementById('1');
			body  = document.body;
			body.removeChild(form);
		}
		
	}
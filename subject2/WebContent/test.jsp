<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

</head>
<body>
	<%
		int i = 0*10;
	%>
	<%=i %>
	<div class="reservation">
		<div>
			<p>날짜</p>
			<input class="dateSelector" placeholder = "ex)2020-09-21"/>
			<input class="dateSelector2" />
		</div>
	</div>
</body>
<script type="text/javascript">
	var date1 = flatpickr(".dateSelector", {
		dateFormat:"Y-m-d",
		minDate:"Today",
		maxDate:new Date().fp_incr(30),
		onChange: function(dateObj, dateStr){
			console.log(dateStr);
		}
	})
	
	var date2 = flatpickr(".dateSelector2", {
		dateFormat:"Y-m-d",
		mindate:"Today",
		maxDate: new Date().fp_incr(30),
		onChange: function(dateObj, dateStr){
			console.log(dateStr);
		}
	})
</script>
</html>
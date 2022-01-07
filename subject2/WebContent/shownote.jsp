<%@page import="com.sun.jdi.Location"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.model.MoneyNoteDTO" %>
<%@ page import="com.model.MoneyNoteDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.util.DBconnection" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="./css/common.css" rel="stylesheet">

<script type="text/javascript" src="./js/shownote.js"></script>
<!-- 달력 flatpickr -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<head>
<meta charset="EUC-KR">
<title>Show note</title>
</head>
<body>
<%
	/* ID CHECK   -------------------------------------------------------------------------*/
	if(session==null || session.getAttribute("id")==null){
		response.sendRedirect("index.jsp");
	}
	else
		System.out.println("session : "+ session.getAttribute("id"));
	/* ----------------------------------------------------------------------------------- */
	
	String id = (String)session.getAttribute("id");
	MoneyNoteDAO dao = new MoneyNoteDAO();
	String date = response.getHeader("date");
	String condition = response.getHeader("condition");
	String conORmemo = response.getHeader("conORmemo");
	String innout = response.getHeader("innout");
	String ynn = response.getHeader("ynn");
	ArrayList<MoneyNoteDTO> list = null;
	
	/* AccountSearch 에서 받아오는 정보 유무 확인 후 함수 결정 */
	if(response.getHeader("date")!=null || response.getHeader("conORmemo")!=null || response.getHeader("innout")!=null || response.getHeader("ynn")!=null){
		list = dao.getMoneyNote(id, date, condition, innout, ynn, conORmemo);	
	}else{
		list = dao.getMoneyNoteAll(id);
	}
	
	/* --------------------------------------------------------------------------------------- */
	
	
	/* 페이징 기능 추가 */
	int noteNumOfPage = 10;
	int noteNum = list.size();
	int pageNum = (noteNum/10) + 1;
	int notePage  = 1;
	if(request.getParameter("notePage")!=null){
		notePage = Integer.parseInt(request.getParameter("notePage"));
	}
	
	/* ------------------------------------------------------------------------------------ */

	String sql ="select no from moneynote limit 1";
	Connection conn = new DBconnection().getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	int firstNo = 0;
	while(rs.next()){
		firstNo = rs.getInt("No");
	}


%>
<jsp:include page="header.jsp"/>

<!-- 조건 검색 폼 ------------------------------------------------------------------------->
<form action="/subject2/AccountSearch" method="get">
<input name="dateSelect" class="dateSelector" placeholder = "ex)2020-09-21"/>
<select name="condition">
	<option value="note">내용</option>
	<option value="memo">메모</option> 
</select>
<input name="conORmemo" type="text" />
<label class="form-check-label">수입지출</label>
<input type="radio" name="innout" value="in" class="form-check-input">In
<input type="radio" name="innout" value="out" class="form-check-input">Out
<label class="form-check-label">변경 가능 여부</label>
<input type="radio" name="ynn" value="y" class="form-check-input"/>Y
<input type="radio" name="ynn" value="n" class="form-check-input"/>N
<input type="submit" class="btn btn-primary btn-sm" value="검색"/>
</form>
<!--  ------------------------------------------------------------------------------------->

<div class="table-responsive">
<table name="AcTable" class="table table-striped align-middle" border="1">
	<tr>
		<th scope="col">번호</th>
		<th scope="col">이메일</th>
		<th scope="col">용돈</th>
		<th scope="col">수입지출</th>
		<th scope="col">내용</th>
		<th scope="col">수입지출일</th>
		<th scope="col">메모</th>
		<th scope="col">변경가능여부</th>
	</tr>
	<%	//페이징 기능을 위한 계산식, for문 중간식에 0보다 작은수를 만들지 않기 위한 식
		int mid = list.size() - (notePage*noteNumOfPage);
		if(mid < 0) mid = 0;
		for(int i=list.size() - ((notePage-1)*noteNumOfPage)-1; i >= mid; i--){
	%>
	<tr>
		<td><%=list.get(i).getNo() %></td>
		<td><%=list.get(i).getEmail() %></td>
		<td><%=list.get(i).getMoney() %>원</td>
		<td><%=list.get(i).getIno() %></td>
		<td><%=list.get(i).getNote() %></td>
		<td><%=list.get(i).getIodate() %></td>
		<td><%=list.get(i).getMemo() %></td>
		<td id="cf<%=list.get(i).getNo()%>"><%=list.get(i).getConfirm() %>
		<%
			if(list.get(i).getConfirm().equals("n")){ 
		%>
		<button value="cf<%=list.get(i).getNo() %>" id="confirmBtn" type="button" class="btn btn-sm" onclick="confirmBtn(this)">√</button>
		<%
			} 
		%></td>
		<td><button id="mBtn" type="button" class="btn btn-sm" value="m<%=list.get(i).getNo()%>">수정</button></td>
		<td><button  id="dBtn" type="button" class="btn btn-sm"  value="d<%=list.get(i).getNo()%>" onclick="noteDel(this)">삭제</button></td>
	</tr>
	<%
		}
	%>
</table>
</div>
<div class="container">
		<button type="button" class="btn btn-primary btn-lg" onclick="insertNote()">기록입력</button>
		<%
			for(int i = 0; i < pageNum; i++){
		%>
			<a href="shownote.jsp?notePage=<%=i+1%>"><%=i+1%></a>
		<%
			}
		%>
</div>

</body>
<script>
	
	var dateS = "";
	var date1 = flatpickr(".dateSelector", {
		mode: "range",
		dateFormat:"Y-m-d",
		minDate:"Today",
		maxDate:new Date().fp_incr(30),
		onChange: function(dateObj, dateStr){
			dateS = dateStr;
			console.log(dateS, typeof(dateS));
		}
	})
</script>
</html>
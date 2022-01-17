# AccountSite
가계부 사이트
 ##깃허브와 함께 시작한 프로젝트가 아니라서 한번에 올림.
영상 : https://youtu.be/oNxS0AR2xq4
 
 # 프로젝트 하면서 적어둔 기록장
 
 21.8.23
moneyweb프로젝트 다시 부활
기본적인 구조부터 다시 확인 함
지금 다시 보니까 user별로 각각의 데이터가 달라야함
어느 user로 로그인해서 데이터를 입력해도 모든 user가 데이터를 모두 공유 하고있는 오류가 존재함
->member 테이블의 pk를  moneynote 테이블의 pk, fk로 설정?

8.24
moneynote 테이블에 id 속성 FK로 추가
insert select delete컨트롤러 모두 sql구조 바꿔야 할 듯
로그인떄 설정하는 세션을 통해 아이디를 알아낸 후
사용하면 될 듯

insertController 수정 완료 -> 세션 id 값 추가
그리고 moneynote 테이블 confirm 속성 기본 값을 'n'으로 설정 함(예전에 이 방식 말고 다른 방식으로 기본 값 'n'으로 설정 해놓은거 같은데 기억이 안남) + insert 할 때 confirm 기본값 설정안되있다고 에러나서 그냥 설정 해줌. 나중에 찾아야 할 듯

MoneyNoteDTO, DAO 둘다 id 필드를 추가 해야함
DAO 의 getMoneyNoteAll() 함수도 id 파라미터를 추가해서 그 id에 해당하는 데이터만 ArrayList에 추가 함 

로그인 id 에 해당하는 데이터만 select 완료

deleteController 수정해야함, No 컬럼으로 어떤 데이터인지  인식하고 삭제 또는 수정 하는데, No 컬럼이 사용되는 곳이 많음 No 컬럼은 모든 user의 데이터를 통합해서 인덱싱하는 번호인데 No컬럼을 사용하면 오류가 생김

8.25
No 문제
생각해보니까 그냥 테이블에 No 를 안보이게 해놓고
굳이 No 를 수정안하는 방향으로 결정

삭제 후 새로고침
데이터 삭제하면 바로 반영되서 확인할 수 있게끔 수정해야함.  삭제 후 새로고침을 위해 AccountDeleteController 마지막에 reponse.sendRedirect 해보고 addHeader로 새로고침 해봐도 안되길래 응담이 shownote.jsp 의 noteDel()함수로 최종적으로 돌아가는건가 의심스러워서 noteDel() 마지막에 새로고침 기능 추가했더니 바로 새로고침기능 완료

ASC 불필요
AccountSelectController 보니까 아무 기능이 없이 바로
loginsuccess -> ASC -> shownote 였음 그래서 ASC 삭제 후 loginsuccess -> shownote 바로 이동

shownote는 세션이 있는경우만 요청가능하게 해야함

8.26
세션이 있는경우
shownote, loginsuccess 세션이 있는경우만 요청가능하게 수정 완료

해야할 일
AccountSearchController 

8.27
조건 검색
검색을 셀렉트박스를 통해 어떤 조건으로 검색할지 셀렉트 후 조건에 맞는 텍스트를 입력하여 조건 검색.
날짜는 datepicker를 사용하여 검색


8.29
달력
flatpickr 사용해서 달력 만듦 shownote에서 날짜 조건으로 검색 가능 ,미완

8.31
flatpickr는 js로 작동해서 조건 폼에서 컨트롤러로 넘어갈 때 어떻게 js 데이터를 넘겨 줘야할지 생각했는데 
그냥 getParameter로 넘겨 받아주면 됬음. 똑같이 input취급

MoneyNoteDAO 에 search용 함수 하나 만들어야할듯

AccountSearchCtroller : sendRedirect를 forward로 바꿈
shownote에서 조건 입력하고 검색하면 AccountSearch로 넘어가는데 여기서 넘어온 데이터를 다시 shownote로 넘겨줘야하는데 Header에 데이터 추가해서  response.sendRedirect로 넘겨주려는데 안됨 -> sendRedirect는 기존의 request와 response객체가 소멸되버림, 그래서 객체안의 헤더도 소멸. 그래서 forward방식으로 바꾸니까 잘됨.

조건 파라미터를 MoneyNoteDAO.getMoneyNote()함수에 넣어서 ArrayList 반환

2021-09-16 to 2021-09-23
1.date만 있을 때
2.내용, 메모 내용만 있을 때
3.수입 지출만 있을 때 
4.변경 가능 여부만 있을 때

완성
1, 2, 3, 4

9.02
조건 검색 기능 완성
검색 시 getMoneyNote()에서 알맞은 sql로 검색
검색 없을 시 getMoneyNoteAll()에서 모두 검색 

9.3
수정 버튼 기능 만들어야함

9.5
문제점 발견
로그아웃 하면 로그인 페이지로 이동하는데 
여기서 뒤로가기 누르면 다시 뒤로가짐
해결 방안 : 필터 사용? or 공통 메소드 추가?

9.7
로그아웃 뒤로가기 문제 보류
해결하려면 js로 브라우저에서 해결해야 할듯함
지금 현재 뒤로가기가 가능해도 어차피 삽입, 삭제, 수정 불가능

페이징 기능
하는중

9.9
페이징 기능 완성
한 페이지에 10개씩 페이지를 나누었음
페이지 aref에 get 파라미터(notePage=1)를 넘겨줌
DAO 함수에 파라미터를 추가해서 만들어낼까 했는데
그러면 페이지가 바뀔때마다 함수에 입력해서 rs값을 뽑아내야되는데 비효율적이라고 생각함 
그래서 그냥 테이블 표현 for문에 계산식을 추가해서 완성
for(int i=list.size() - ((페이지넘버-1)*한페이지당노트수)-1; i >= list.size() - (페이지넘버*한페이지당노트수); i--)

note를 개별적으로 식별하기 위한 i + firtsNo 삭제
페이징 하면서 오류가 생기길래 다시 확인해봤더니
그냥 기본키 No 로 식별하면 되는거였음

9.12
수정 버튼 기능 완성해야함

기록 입력도 유효성 검사 추가해야함

다른 기기에서 db에 접속불가
db주소가 localhost로 되있음 이 부분은 ip 주소로 바꿔줘야함

9.17
 (농장 ip) 에 외부접속 허용 가능 하게 설정
twin ip 설정 -> 포트포워딩(톰캣) -> 방화벽 인바운드 규칙 설정

외부 db접속 문제
외부에서 db접속이 안됬던게 ajax로 로그인 체크 하는데
js 부분에 XMLHttpRequest에서 컨트롤러에 요청할 때 주소가 localhost로 되있었음 이것도 ip주소로 바꿔줘야 함

삭제 안되는 문제
session id를 못 읽어옴

9.27
삭제 안되는 문제
갑자기 됨

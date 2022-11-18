<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>MVC 게시판</title>
<style type="text/css">


#hhh{
   text-align: center;
   margin-top:30px;
   color:black;
  
}


#td_left {
    text-align:center;
   font-size: 12px; 
}
#commandCell {
   text-align: center;  
}

p{text-align: center;}

#b1{margin-bottom: 10px;}
#table-first{ margin:0 auto; width: 60%;  border-color: #e1e4e1; background-color: white;}

#address{width: 50%;}
#writeForm{background-color: white;}   

 @media all and (max-width: 768px){
     #table-first{width:80%;}
    }
 @media all and (max-width: 415px){
     #table-first{width:100%;}
    }   
</style>
</head>



<body>
<%@include file="/userHeader.jsp" %>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
   <!-- 게시판 등록 -->

   <section id="writeForm">
      <h4 id="hhh">QNA문의하기</h4>
        <h4 class="qb"><a href="main.jsp" class="btn btn-outline-dark" style="font-size:12px;">홈으로</a></h4>
              
      <form action="qboardWritePro.qna" method="post" name="boardform">
        <input type="hidden" id="loginID" name="loginID" value="${mem_id}">
         <table id="table-first" class="table table-bordered" >
         
                 <tr>
                      <td id="td_left">작성자 ID</td>
                      <td style="text-align:left;">${mem_id}</td>                   
                 </tr>         
            <tr>
                 <td id="td_left"><label for="QBOARD_SUBJECT">제목</label></td>
                 <td>
             <input name="QBOARD_SUBJECT" type="text"id="QBOARD_SUBJECT" required="required" size="67" class="form-control"/>
                  </td>
            </tr>
         
             <tr>
               <td id="td_left"><label for="QBOARD_CONTENT">내용</label></td>
               <td><textarea id="QBOARD_CONTENT" name="QBOARD_CONTENT"
                cols="80" rows="10" required="required" class="form-control"style="font-size:12px;"></textarea>
                </td>
            </tr>
                
         </table>
         <div id="commandCell">
            <input type="submit"  class="btn btn-outline-dark" id="b1" value="등록" id="b1" style="font-size:12px;">&nbsp;&nbsp;   <!-- 유효성 체크함수로 주소입력버튼을 클릭안하면 등록버튼 누를 수 없게 방지 -->
            <input type="reset" class="btn btn-outline-dark"  id="b1" value="다시쓰기" id="b1" style="font-size:12px;"/>
         </div>

</form>
</section>
<%@include file="/footer.jsp" %>
</body>
</html>

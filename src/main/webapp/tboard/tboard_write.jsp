<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   text-align: center; color:black; margin-top:30px;
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
#table-first{ margin:0 auto; width: 60%; border-color: black;  border-color: #e1e4e1; background-color: white;}
#writeForm{background-color: white; width: 70%; margin:0 auto; }

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
      <h4 id="hhh">테니스 모임 게시판 글쓰기</h4>
        <h4><a href="main.jsp" class="btn btn-outline-dark" style="font-size:12px;">홈으로</a></h4>
              
      <form action="tboardWritePro.bo" method="post" enctype="multipart/form-data" name="boardform"> <%--multypart/form-data : 파일이나 이미지전송할때 --%>
      <input type="hidden" id="loginID" name="loginID" value="${mem_id}">
         <table id="table-first" class="table table-bordered" >      
                 <tr>
                      <td id="td_left">작성자 ID</td>
                      <td style="text-align: left">${mem_id}</td>                   
                 </tr>            
            <tr>
                 <td id="td_left"><label for="TBOARD_SUBJECT">제목</label></td>
                 <td>
                      <input name="TBOARD_SUBJECT" type="text"id="TBOARD_SUBJECT" required="required" size="67" class="form-control"/>
                  </td>
            </tr>         
             <tr>
                <td id="td_left"><label for="BOARD_CONTENT">내용</label></td>
                <td><textarea id="TBOARD_CONTENT" name="TBOARD_CONTENT"
                cols="80" rows="10" required="required" class="form-control"style="font-size:12px;">**********************************************
필수 작성 란    
모임날짜와 시간: 
상세장소 : 
모임최대인원 : 명
테니스 최소 구력 : 년이상 , 또는 개월 이상
전화번호 : 010-xxxx-xxxx 
참석 하실분은 답변 글 달아주세요~! 
**********************************************</textarea>
         
               <img id="preview" style="width:100%; height: 150px; display: none;"/>
     
                      
               
                </td>
            </tr>
            <tr>
               <td id="td_left"><label for="TBOARD_FILE"> 파일 첨부 </label></td>
               <td><input type="file" name="TBOARD_FILE" class="form-control" id="TBOARD_FILE" style="font-size:12px;" /></td>
            </tr>
            <tr>
               <td id="td_left"><label for="TBOARD_IMAGE"> 내용 이미지 첨부 </label></td>
               <td><input type="file" name="TBOARD_IMAGE" class="form-control" id="TBOARD_IMAGE" style="font-size:12px;"
               accept="image/gif, image/jpeg, image/png, image/jfif" onchange="fileCheck(this),readURL(this)" />
               
               </td>
            </tr>
              
             <tr>
               <td id="td_left">모임 장소</td>
               <td>   
               <div class="input-group mb-3">                  
                  <input type="text" name="detailAddress" id="address" required="required"  class="form-control">   
                   <button type="button" id="searchBtn" class="btn btn-outline-dark" style="font-size:12px;" >주소입력</button>   
               </div>         
               </td> 
                          
            </tr>
           <tr>
             <td colspan="2">
             
                  <div id="map" style="width:100%;height:250px;"></div>
             </td>
            </tr>                     
         </table>
         
         <div id="commandCell">
               <!-- <input hidden="hidden" /> --> <!-- 엔터방지 -->
            <input type="button"  class="btn btn-outline-dark" id="b1" value="등록" id="b1" onclick="check();" style="font-size:12px;">&nbsp;&nbsp;   
            <input type="reset" class="btn btn-outline-dark"  id="b1" value="다시쓰기" id="b1" style="font-size:12px;"/>
         </div>
         
   <%--카카오 API 의 위도, 경도값이 담긴값을 hidden으로 form에 실어 보낸다. --%>      
    <input type="hidden" id="TBOARD_PLACELA" name="TBOARD_PLACELA" value="">
    <input type="hidden" id="TBOARD_PLACEMA" name="TBOARD_PLACEMA" value="">      
</form>


   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b4a735c0046067425af367f34003e43&libraries=services"></script>
   <script type="text/javascript" src="tboard/javascript/tboard_write_script.js"></script>
   
</section>
<%@include file="/footer.jsp" %>
</body>
</html>


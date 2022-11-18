<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
 isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>




<!DOCTYPE HTML>
<html>
	<head>
	<title>Tennis shop</title>
   <meta charset="utf-8">
   
<style>
#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:3px;
    width:75%;
    height:75%;
    margin:3%;
    
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 1em;
    color: transparent;
     text-shadow: 0 0 0 #553f3f;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.button {
    display: flex;
    justify-content: center;
}
label {
    cursor: pointer;
    font-size: 1em;
}

/* 못생긴 기존 input 숨기기 */
#chooseFile {
    visibility: hidden;
}
.image-upload {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.button {
    display: flex;
    justify-content: center;
}

label {
    cursor: pointer;
    font-size: 1em;
}

#chooseFile {
    visibility: hidden;
}

.fileContainer {
    display: flex;
    justify-content: center;
    align-items: center;
}

.fileInput {
    display: flex;
    align-items: center;
    border-bottom: solid 2px black;
    width: 60%;
    height: 30px;
}

#fileName {
    margin-left: 5px;
}

.buttonContainer {
    width: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 10px;
    background-color: black;
    color: white;
    border-radius: 30px;
    padding: 10px;
    font-size: 0.8em;

    cursor: pointer;
}

.image-show {
    z-index: -1;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    width: 100%;
    height: 100%;
}

.img {
    position: absolute;
}
</style>

<script type="text/javascript">
var submit = document.getElementById('submitButton');
submit.onclick = showImage;     //Submit 버튼 클릭시 이미지 보여주기

function showImage() {
    var newImage = document.getElementById('image-show').lastElementChild;
    newImage.style.visibility = "visible";
    
    document.getElementById('image-upload').style.visibility = 'hidden';

    document.getElementById('fileName').textContent = null;     //기존 파일 이름 지우기
}


function loadFile(input) {
    var file = input.files[0];

    var name = document.getElementById('fileName');
    name.textContent = file.name;

    var newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');

    newImage.src = URL.createObjectURL(file);   

    newImage.style.width = "70%";
    newImage.style.height = "70%";
    newImage.style.visibility = "hidden";   //버튼을 누르기 전까지는 이미지 숨기기
    newImage.style.objectFit = "contain";

    var container = document.getElementById('image-show');
    container.appendChild(newImage);
};


function checkReview(){
	


	if(myform.rev_content.value==""){
		alert("상품평 내용을 입력해 주세요");
		return myform.rev_content.focus();
	}
	
	
	if(
          (myform.rev_score[0].checked == false)&&(myform.rev_score[1].checked == false)
          &&(myform.rev_score[2].checked == false)&&(myform.rev_score[3].checked == false)
          &&(myform.rev_score[4].checked == false)
         ){
           alert("별점을 선택해주세요.");
           return;
       }
	
	
	
	myform.submit();
} 

</script>


</head>



	<body>
	<jsp:include page="../userHeader.jsp"></jsp:include>
		<jsp:include page="../boot.jsp"></jsp:include>

	
	<form class="mb-3" name="myform" id="myform" method="post" action="revUpdatePro.do?board_no=${article.board_no}&rev_fileName=${article.rev_fileName}" enctype="multipart/form-data" accept-charset="UTF-8">	
			
			
			<div id="page">
				
				<div class="colorlib-product">
					<div class="container">
						<div class="row">
						
						
							<div class="col-sm-8 offset-sm-2 text-center colorlib-heading">
								<h2>Review</h2><!-- css h2 fontfamily 7340 -->
								
								
								 <select name="prod_id" id="prod_id" style="width: 100px; height:30px;" >
							  		 
           						    <c:forEach var="list" items="${orderList}" varStatus="status" >
             					    	 <option value ="${list.key}" ${list.key==article.prod_id?"selected":""}>${list.value}</option>
            					   </c:forEach>
          						  </select>
							
								
								
								
								
								
								
							<input type="submit" value="수정" onclick="checkReview(); return false;">
							<input type="reset" value="취소" onclick="history.back();">
						
							</div>
							
						</div>
						
							<fieldset>
				<span class="text-bold">별점을 선택해주세요</span>
				<input type="radio" name="rev_score" value="5" id="rate1"><label
					for="rate1">★</label>
				<input type="radio" name="rev_score" value="4" id="rate2"><label
					for="rate2">★</label>
				<input type="radio" name="rev_score" value="3" id="rate3"><label
					for="rate3">★</label>
				<input type="radio" name="rev_score" value="2" id="rate4"><label
					for="rate4">★</label>
				<input type="radio" name="rev_score" value="1" id="rate5"><label
					for="rate5">★</label>
			</fieldset>				
							
						
			
			<div class="button">
		        <label for="chooseFile">
		            👉 CLICK HERE!
		        </label>
		    </div>
		 
			 <%-- <input type="hidden" name=board_no value="${article.board_no}"> --%>
			 <!-- 차이점 : 단순 글 등록할땐 로그인상태의 아이디를 넘겨줬지만,update문에서 where조건에 게시글번호를 적용할거기 때문에 게시글번호를 넘김-->
		   
		   
		     <input type="file" id="chooseFile" name="rev_fileName" accept="image/*" onchange="loadFile(this)">
		 	
            <div class="fileContainer">
                <div class="fileInput">	
                    <p>FILE NAME: </p>
                    <p id="fileName">${article.rev_origfileName}</p>
                </div>
                
            </div>
       
        
       	
  		 
			
			
			
			<div>
			<font size="4" strong>후기사진을 함께올려주세요	</font>
			</div>
			
			<fieldset>
			<div>
				<textarea class="col-auto form-control" name="rev_content" type="text" required id="reviewContents" placeholder="상품평을 입력 해 주세요"
				cols="40" rows="15" required >${article.rev_content }</textarea>
			</div>
			</fieldset>

				</div>
			</div>
				
								
												
		</div>
							
					
					
			
			
	</form>
	

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
	</div>
	
	

	</body>
</html>


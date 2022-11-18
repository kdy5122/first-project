<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
 isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="vo.ProductBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE HTML>
<html>
	<head>
	<title>제품상세</title>
   <meta charset="utf-8">
  

<style>
p{
display:inline-block;}

</style>

<!-- 
<h5 layout:fragment="script">
<script h5=javascript">
$(documnet).ready(function(){
	
	calculateTotalPrice();
	
	$("#quantity").chage(function(){
		calculateTotalPrice();
		
	});
	
});

function calculateTotalPrice{
	let quantitiy=$("#quantity").val();
	let prcie=$(".price").val();
	let totalprice=prcie*quantitiy;
	$(""#totalPrice").html(totalPrice+'원');
}
	 -->
	 
<script>


function btnFunc(){
	var o_amount=document.getElementById("quantity").value;
	var prod_id=document.getElementById("prod_id").value;
	var prod_amount=document.getElementById("prod_amount").value;
	
	
	if(confirm("장바구니에 담으시겠습니까?")==true){
		location.href="cartAdd.od?o_amount="+o_amount+"&prod_id="+prod_id+"&prod_amount="+prod_amount;
	}else{
	return false;
	}
}


</script>

<script type="text/javascript">
// 변경된 값을 저장
var  prod_price;
var o_amount;
var prod_amout;

// init 초기값을 지정할 수 있다.
function init () {
prod_price = document.order.prod_price.value;
o_amount = document.order.o_amount.value;
document.order.total_price.value = prod_price;
change();
}


function add () {
o_amount = document.order.o_amount;
total_price= document.order.total_price;
o_amount.value ++ ;

total_price.value = parseInt(o_amount.value) *prod_price+"원";

}


function del () {

o_amount = document.order.o_amount;

total_price = document.order.total_price;



// 에러 처리 : 음수 값

if (o_amount.value > 1) {

  o_amount.value -- ;

  total_price.value = parseInt(o_amount.value) * prod_price+"원";

}

}



function change () {



o_amount = document.order.o_amount;

total_price = document.order.total_price;



if (o_amount.value < 0) {

  o_amount.value = 0;

}

total_price.value = parseInt(o_amount.value) * prod_price +"원";

} 



		


</script>


	</head>
	<body onload="init();">
		<jsp:include page="../userHeader.jsp"></jsp:include>
		<jsp:include page="../boot.jsp"></jsp:include>

	<div id="page">
		<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg product-detail-wrap">
				
				
					<div class="col-sm-8" >		
							<!-- 이미지 첨부파일 -->
							<div class="item">
								<div class="product-entry border">
								<!-- 이미지 들어 갈 곳 -->
									<a href="#" class="prod-img">
										<img src="productUpload/${goods.prod_image}" class="img-fluid" alt="Free html5 bootstrap 4 template">
									</a>
								</div>
							</div>	
					</div>
					
					
			
				<div class="col-sm-4">
				<form action="productOrderform.po" name="order" method="post"> 
						<div class="product-desc">
									<h2>${goods.prod_name}</h2>
									<p class="price">
										<span>￦${goods.prod_price}</span></br>
										
									</p>
									<p>
									${goods.prod_content }
									</br>
									</br>
									</p>
								
				                     <div class="input-group mb-4">
				                     	
				                     	<span class="input-group-btn">
				                        	<input type="button"  style="width:50px; height:45px;"  class="quantity-left-minus btn" value="-" onclick="del();" data-type="minus" data-field="">
				                          
				                        	
				                    		</span> 
				                     	
				                     	<input type="text" id="quantity" name="o_amount" class="form-control input-number"  onchange="change();" value="1" min="1" max="${goods.prod_amount}">
				                     	
				                     <span class="input-group-btn ml-1">
				                        	<input type="button"  style="width:50px; height:45px;"  class="quantity-right-plus btn" value="+" onclick="add();" data-type="plus" data-field="">
				                             
				                         
				                     	</span> 
				                     	
				                  	</div>
				                  	
				                  	<div >
				                     	<input class="one-eight text-center" type="text"  id="total_price" name="total_price"  readonly />
				                     </div>
				                     </br>
				                  	<div class="row">
					                  	<div class="col-sm-12 text-center">
												<p class="addtocart">
													 <input type="button" class="btn btn-primary btn-addtocart"	value="장바구니"  onclick="btnFunc();" />
												</p>  
												<p class="addtocart">
													<input  type="submit" name="" class="btn btn-primary btn-addtocart" value="구매하기"/>
												</p>			
										</div>			
									</div>
								
									<input type="hidden" id="prod_id" name="prod_id" value="${goods.prod_id}" />			
									<input type="hidden" name="prod_name" value="${goods.prod_name}" />			
									<input type="hidden" name="prod_price" value="${goods.prod_price}" />
									<input type="hidden" id="prod_amount" name="prod_amount" value="${goods.prod_amount}" />
									<input type="hidden" name="prod_image" value="${goods.prod_image}" />
												
											
								 </div>
					</form>
					</div>
				
				
					
					
					
					
					
					</div>
				</div>
			</div>
		</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
	</div>


	</body>
</html>


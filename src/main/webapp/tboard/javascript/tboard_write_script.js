	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { //맵설정
	        center: new kakao.maps.LatLng(35.931692346116584, 128.54664831044244), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // map에 설정한값을 담는다.

	
	$('#searchBtn').click(function(){
		// 주소입력 버튼을 click했을때의 함수
				
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 요청
		//addressSearch(addr(변환할 주소명), callback(검색 결과를 받을 함수))
		geocoder.addressSearch($('#address').val(), function(result, status) { //input한 id address의 주소 정보에해당하는 좌표값을 호출하는 함수
			  //result : 결과목록(예를들어 '대구' 검색했을때 나오는 주소결과들 배열)  ,  status : 응답코드 
	         //addressSerach : 주소 정보에 해당하는 좌표값을 요청
		  
		     if (status === kakao.maps.services.Status.OK) {// 정상적으로 검색이 완료됐다면 
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x); //검색한 주소의 첫번째 배열의 위도 경도값을 담는다.
		       
		        
		        let lat = result[0].y; //위도값 변수에담음 
		        let lng = result[0].x; //경도값 변수에담음 
		        
		        // 추출한 좌표를 통해 도로명 주소 추출
		        function getAddr(lat,lng){
		            let geocoder = new kakao.maps.services.Geocoder();
	
		            let coord = new kakao.maps.LatLng(lat, lng);
		            let callback = function(result, status) {
		                if (status === kakao.maps.services.Status.OK) {
		                	// 추출한 도로명 주소를 해당 input의 value값으로 적용
		                    $('#address').val(result[0].road_address.address_name);
		                }
		            }
		            geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
		            //coord2Address(X , Y , callBack); : 좌표 값에 해당하는 구 주소와 도로명 주소 정보를 요청한다. 도로명 주소는 좌표에 따라서 표출되지 않을 수 있다.
		        }
		        
		        // 결과값으로 받은 위치를 마커로 표시
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">테니스 모임 장소</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동
		        map.setCenter(coords);
		        
		        TBOARD_PLACELA.value=result[0].y; //위도값을 hidden의  BOARD_PLACELA.value 에 담는다 
	            TBOARD_PLACEMA.value=result[0].x; //경도값을 hidden의  BOARD_PLACEMA.value 에 담는다   
		 
		    } 
		});  
	});
	  
	  
	  
        function check() {//주소입력 클릭 함수
		
		if(boardform.TBOARD_SUBJECT.value==""){
			alert("제목을 입력해주세요.")
			return boardform.TBOARD_SUBJECT.focus();
		}
	    if(boardform.TBOARD_CONTENT.value==""){
	    	alert("내용을 입력해주세요.")
	    	return boardform.TBOARD_CONTENT.focus();
	    }		
		if (boardform.address.value !="" && boardform.TBOARD_PLACEMA.value=="") { //주소는 적었지만 주소입력을 클릭하지 않은경우 체크 
			alert("주소입력을 클릭해 주세요!");			
			return boardform.address.focus();
		}
		boardform.submit();
				
	}
	
	

	
	function fileCheck(obj){ //이미지 내용추가 파일체크 함수
	  
	  pathpoint = obj.value.lastIndexOf('.');
	  filepoint = obj.value.substring(pathpoint+1,obj.length);
	  filetype = filepoint.toLowerCase();
	  if(filetype=='jpg'||filetype=='gif'||filetype=='png'||filetype=='jpeg'||filetype=='bmb'||filetype=='jfif'||filetype==''){//취소했을경우포함
		  //정상적인 이미지 확장자 파일일 경우
	  }else {
		  alert('이미지 파일만 선택할 수 있습니다.');
		  parentObj = obj.parentNode;
		  node = parentObj.replaceChild(obj.cloneNode(true),obj);
		  return false;
	  }
	  if(filetype=='bmb'){
		  upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n 그래도 계속하시겠습니까?');
		  if(!upload)return false;
	  }
       
	}	
	
	
	
	function readURL(input) { //내용 이미지 미리 보기 함수 
		
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function(e) {
		      document.getElementById('preview').src = e.target.result;
		    };
		    reader.readAsDataURL(input.files[0]);
		     document.getElementById("preview").style.display="block";//실행시 이미지 display none 에서 block으로 
		  } else {
		    document.getElementById('preview').src = "";
		     document.getElementById("preview").style.display="none";//취소했을 경우 none
		  }
		  		  
	             
	}

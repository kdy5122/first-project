var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(35.931692346116584,128.54664831044244 ), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	$('#searchBtn').click(function(){
		// 버튼을 click했을때
		
		// 지도를 생성합니다    

		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch($('#address').val(), function(result, status) {
	
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 추출한 좌표를 통해 도로명 주소 추출
		        let lat = result[0].y;
		        let lng = result[0].x;
		        getAddr(lat,lng);
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
		        }
		        
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">테니스 모임 장소</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		        
		        TBOARD_PLACELA.value=result[0].y; //위도값을 hidden의  TBOARD_PLACELA.value 에 담는다  
	            TBOARD_PLACEMA.value=result[0].x; //경도값을 hidden의  TBOARD_PLACEMA.value 에 담는다   
		 
		    } 
		});  
	});
	
	
	
	function modifyboard(){
		if(modifyform.TBOARD_SUBJECT.value==""){
			alert("제목을 입력해주세요.")
			return modifyform.TBOARD_SUBJECT.focus();
		}
	    if(modifyform.TBOARD_CONTENT.value==""){
	    	alert("내용을 입력해주세요.")
	    	return modifyform.TBOARD_CONTENT.focus();
	    }		
		if (modifyform.address.value !="" && modifyform.TBOARD_PLACEMA.value=="") {//주소는 적었지만 주소입력을 클릭하지 않은경우 체크 
			alert("주소입력을 클릭해 주세요!");			
			return modifyform.address.focus();
		}
		modifyform.submit();

	}
	function fileCheck(obj){ //이미지 내용추가 파일체크 함수
		  pathpoint = obj.value.lastIndexOf('.');
		  filepoint = obj.value.substring(pathpoint+1,obj.length);
		  filetype = filepoint.toLowerCase();
		  if(filetype=='jpg'||filetype=='gif'||filetype=='png'||filetype=='jpeg'||filetype=='bmb'||filetype=='jfif'||filetype==''){//취소했을경우포함
			  //정상적인 이미지 확장자 파일일 경우...
		  }else{
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
			     document.getElementById("previewP").style.display="none";//기존에 가져온 이미지가 있다면 none
			     document.getElementById("preview").style.display="block"; 
			  } else {
			    document.getElementById('preview').src = "";
			    document.getElementById("preview").style.display="none"; //취소했을 경우 none
			  }
        
			 
			  
		}
		
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
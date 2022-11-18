

// 이미지 지도에서 마커가 표시될 위치입니다 
//가져온 좌표값으로 마커 포지션선정
var markerPosition  = new kakao.maps.LatLng(TBOARD_PLACELA.value, TBOARD_PLACEMA.value); 

let lat = TBOARD_PLACELA.value; //위도에 불러온 위도값을 대입
let lng = TBOARD_PLACEMA.value; //경도에 불러온 경도값을 대입
/*----------------가져온 좌표값으로 지도출력----------------- */
// 이미지 지도에 표시할 마커입니다
// 이미지 지도에 표시할 마커는 Object 형태입니다
var marker = {
    position: markerPosition
};

var mapContainer  = document.getElementById('map'), // 이미지 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lng), // 이미지 지도의 중심좌표
        level: 3, // 이미지 지도의 확대 레벨
        marker: marker // 이미지 지도에 표시할 마커 
        
    };    
//이미지 지도를 생성합니다
var map = new kakao.maps.StaticMap(mapContainer, mapOption);  //StaticMap으로 설정하여 클릭시 길찾기기능 등 손쉽게 찾아올수 있게 해놓음

/*-----------가져온 좌표값으로 주소출력 -----------*/      
getAddr(lat,lng);
function getAddr(lat,lng){
    let geocoder = new kakao.maps.services.Geocoder();

    let coord = new kakao.maps.LatLng(lat, lng);
    let callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            console.log(result[0].address.address_name); //좌표에 대한 콘솔에 찍힌 주소를 jsp로 넘겨줘야함
               address.value=result[0].address.address_name;   //address.value에 주소넘김  
        }
    }
    geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
}


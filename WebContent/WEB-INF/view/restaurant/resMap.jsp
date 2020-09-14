<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.label {margin-bottom: 96px;}
.label * {display: inline-block;vertical-align: top;}
.label .left {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;display: inline-block;height: 24px;overflow: hidden;vertical-align: top;width: 7px;}
.label .center {background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;display: inline-block;height: 24px;font-size: 12px;line-height: 24px;}
.label .right {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0  no-repeat;display: inline-block;height: 24px;overflow: hidden;width: 6px;}
</style>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6bb62151ddeddee6978f9dd897043e8e"></script>

<div id="mapContainer"></div>

<script type="text/javascript">
	// 지도 표시 기능
	var container = document.getElementById('mapContainer'); //지도를 담을 영역의 DOM 레퍼런스
	container.style.width = '100%';
	container.style.height = '100%';
	const options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(35.8641294, 128.5942331), //지도의 중심좌표.
		level : 5 //지도의 레벨(확대, 축소 정도)
	};
	var map = new kakao.maps.Map(mapContainer, options); // 옵션을 바탕으로 새 지도 생성
	// 지도 표시 기능

	// 지도 마커 기능
	function getRestaurantList() {
		axios.get('/restaurant/ajaxGetList').then(function(res) {
			console.log(res.data);

			res.data.forEach(function(item) {
				createMarker(item);
			});
		});
	}
	getRestaurantList();
	// 지도 마커 기능
	
	// 마커 생성 함수
	function createMarker(item) {
		var ctnt = `<div class="label"><span class="left"></span><span class="center">\${item.nm}</span><span class="right"></span></div>`;
//		console.log(content);
		
		var mPos = new kakao.maps.LatLng(item.lat, item.lng);
//		console.log(mPos);
		
		var marker = new kakao.maps.CustomOverlay({
		    position: mPos,
		    content: ctnt
		});
//		console.log(marker);
		
		marker.setMap(map);
	}
	// 마커 생성 함수
	
	// 내 위치로 지도 이동
	if (navigator.geolocation) {
		console.log('Geolocation is supported!');

		var startPos;
		navigator.geolocation.getCurrentPosition(function(pos) {
			startPos = pos;
//			console.log('lat : ' + startPos.coords.latitude);
//			console.log('lng : ' + startPos.coords.longitude);
			
			if(map) {
				var moveLatLng =  new kakao.maps.LatLng(startPos.coords.latitude, startPos.coords.longitude);
				map.panTo(moveLatLng);
			}
		});

	} else {
		console.log('Geolocation is not supported for this Browser/OS.');
	}
	// 내 위치로 지도 이동
</script>
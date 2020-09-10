<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6bb62151ddeddee6978f9dd897043e8e"></script>

<div id="map"></div>

<script type="text/javascript">
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	container.style.width = '100%';
	container.style.height = '100%';
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(35.8641294, 128.5942331), //지도의 중심좌표.
		level : 3 //지도의 레벨(확대, 축소 정도)
	};
	var map = new kakao.maps.Map(container, options); // 옵션을 바탕으로 새 지도 생성
</script>
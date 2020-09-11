<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sectionContainerCenter">
	<div id="resRegContainer">
		<h1 id="regTitle">등록하기</h1>
		<form id="frm" class="regGrid" action="/restaurant/regProc" method="post">
			<input id="regGrid1" type="text" name="nm" placeholder="가게명">
			<input id="regGrid2" type="text" name="addr" placeholder="주소">
			<button id="regGrid3" onclick="getLatLng()">좌표가져오기</button>
			<input type="hidden" name="lat" value="0"> <input
				type="hidden" name="lng" value="0">
			<div id="regGrid4">
				<span>카테고리:</span>
				<select name="cd_category" id="">				
					<c:forEach items="${categoryList}" var="item">
						<option value="${item.cd}">${item.val}</option>
					</c:forEach>
				</select>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6bb62151ddeddee6978f9dd897043e8e&libraries=services"></script>
	<script type="text/javascript">
		// 검색된 주소 좌표 찍기 구현
		const geocoder = new kakao.maps.services.Geocoder()
	
		function getLatLng() {
			event.preventDefault()
			const addrStr = frm.addr.value
			
			if(addrStr.length < 9) {
				alert('주소를 확인해주세요')
				frm.addr.focus()
				return
			}
			
			geocoder.addressSearch(addrStr, function(result, status) {
				if(status === kakao.maps.services.Status.OK) {
					console.log(result)
				}
			})
		}
		// 검색된 주소 좌표 찍기 구현
	</script>
</div>
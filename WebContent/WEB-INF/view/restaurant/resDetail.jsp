<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 추천 메뉴 리스트 -->
<div class="recMenuContainer">
    <c:forEach items="${recommendMenuList}" var="item">
        <div class="recMenuItem" id="recMenuItem_${item.seq}">
            <div class="pic">
                <c:if test="${item.menu_pic != null && item.menu_pic != ''}">
                    <img src="/res/img/restaurant/${data.i_rest}/${item.menu_pic}" alt="">
                </c:if>
            </div>
            <div class="info">
                <div class="nm">${item.menu_nm}</div>
                <div class="price"><fmt:formatNumber type="number" value="${item.menu_price}"/></div>
            </div>
           	<c:if test="${loginUser.i_user == data.i_user && item.menu_pic != null}">
	       		<div class="delIconContainer" onclick="delRecMenu(${data.i_rest}, ${item.seq})">
    	       		<span class="material-icons">clear</span>
    	   		</div>
   			</c:if>
        </div>
    </c:forEach>
</div>
<!-- 추천 메뉴 리스트 -->
<div id="sectionContainer">
	<c:if test="${loginUser.i_user == data.i_user}">
		<div>
			<a href="/restaurant/resMod"><button>수정</button></a>
			<button onclick="isDel()">삭제</button>
			<!-- 가게 기본 정보 등록창 -->
			<form action="/restaurant/addRecMenusProc" id="recFrm" enctype="multipart/form-data" method="POST">
        		<div><button type="button" onclick="addRecMenu()">메뉴 추가</button></div>
        		<input type="hidden" name="i_rest" value="${data.i_rest}">
        		<div id="recItem"></div>
        		<div>
        			<input type="submit" value="등록">
        		</div>
    		</form>
    		<!-- 가게 기본 정보 등록창 -->
		</div>
	</c:if>
	<div class="restaurant-detail">
		<div id="detail-header">
			<div class="restaurant_title_wrap">
				<span class="title">
					<h1 class="restaurant_name">${data.nm}</h1>
				</span>
			</div>
			<div class="status branch_none">
				<span class="cnt hit">${data.cntHits}</span>
				<!-- <span class="cnt review"></span> -->
				<span class="cnt favorite">${data.cntFavorite}</span>
			</div>
		</div>
		<div>
			<table>
				<caption>레스토랑 상세 정보</caption>
				<tbody>
					<tr>
						<th>주소</th>
						<td>${data.addr}</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>${data.cd_category_nm}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	var idx = 0; // 메뉴 추가할 때 쓰는거
	
	// 메뉴 추가
	function addRecMenu() {
	    var div = document.createElement('div');
	
    	var inputNm = document.createElement('input');
	    inputNm.setAttribute("type", "text");
		inputNm.setAttribute('name', 'menu_nm');
	    var inputPrice = document.createElement('input');
	    inputPrice.setAttribute("type", "number");
		inputPrice.setAttribute('name', 'menu_price');
	    var inputPic = document.createElement('input');
	    inputPic.setAttribute("type", "file");
	    inputPic.setAttribute("name", "menu_pic_" + idx++);

	    div.append(' 메뉴: ');
	    div.append(inputNm);
	    div.append(' 가격: ');
	    div.append(inputPrice);
	    div.append(' 사진: ');
	    div.append(inputPic);

	    recItem.append(div);
	}
	addRecMenu();
	
	// 메뉴 삭제
	function delRecMenu(i_rest, seq) {
		// console.log('i_rest : ' + i_rest);
		// console.log('seq : ' + seq);
		// console.log('fileNm : ' + fileNm);
		
		axios.get('/restaurant/ajaxDelRecMenu', {
			params: {
				i_rest, seq
			}
		}).then(function(res) {
			if(res.data.result == 1) {
				// 엘리먼트 삭제
				const ele = document.querySelector('#recMenuItem_' + seq);
				ele.remove();
			}
		})
	}

	// 가게 삭제
	function isDel() {
		if(confirm('삭제하시겠습니까?')) {
			location.href = '/restaurant/resDel?i_rest=${data.i_rest}';
		}
	}
</script>
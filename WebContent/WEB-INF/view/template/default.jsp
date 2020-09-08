<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
</head>
<body>
	<!-- Default로 보여주는 공통 부분 -->	
	<div id="container">
		<header>
			~~님 환영합니다. (로그아웃 / 메뉴들 추가하기)
		</header>
		<section>
			<!-- view로 보여주는 가변 부분, 컨테이너 안에서 표시 -->
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
		</section>
		<footer>
			회사 정보
		</footer>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/res/css/layout.css">
</head>
<body>
    <!-- Default로 보여주는 공통 부분 -->
    <div id="bgTag"></div>
    <main class="container">
        <header class="hWrapper">
            머리
        </header>
        <section class="sWrapper">
            <article class="arWrapper">
                <!-- view로 보여주는 가변 부분, 컨테이너 안에서 표시 -->
	            <jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
            </article>
            <aside class="asWrapper">
                광고용
            </aside>
        </section>
        <footer class="fWrapper">
            회사
        </footer>
    </main>
</body>
</html>
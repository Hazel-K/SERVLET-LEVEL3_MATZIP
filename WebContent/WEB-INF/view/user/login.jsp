<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
		<div class="msg">${msg}</div>
		<form action="/user/loginProc" name="frm" class="frm" method="post">
		<!-- action에 / 붙이면 localhost:3036 다음부터 시작(처음부터), 안붙이면 해당 주소 다음부터 이어서나감 -->
			<div>
				<input type="text" name="user_id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="user_pw" placeholder="비밀번호">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
		</form>
	</div>
</div>
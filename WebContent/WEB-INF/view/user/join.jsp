<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
	<form class="frm" id="frm" name="frm" action="/user/joinProc" method="post">
	<!-- action에 / 붙이면 localhost:3036 다음부터 시작(처음부터), 안붙이면 해당 주소 다음부터 이어서나감 -->
		<div>
			<div id="idChkResult"></div>
			<input type="text" name="user_id" placeholder="아이디"><input type="button" onclick="chkId()" value="중복확인">
		</div>
		<div>
			<input type="password" name="user_pw" placeholder="비밀번호">
		</div>
		<div>
			<input type="password" name="user_pwre" placeholder="비밀번호 확인">
		</div>
		<div>
			<input type="text" name="nm" placeholder="이름">
		</div>
		<div>
			<input type="submit" value="회원가입">
		</div>
	</form>
	<div>
		<a href="/user/login">로그인</a>
	</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		function chkId() {
			const user_id = frm.user_id.value
			axios.get('/user/ajaxIdChk', {
				params: {
					user_id
				}
			}).then(function(res) {
				console.log(res)
				if(res.data.result == 2) {
					idChkResult.innerText = '사용할 수 있는 아이디입니다.'
				} else if (res.data.result == 3) {
					idChkResult.innerText = '이미 사용중인 아이디입니다.'
				}
			})
		}
	</script>
</div>
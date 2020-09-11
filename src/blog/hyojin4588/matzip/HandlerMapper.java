package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.restaurant.restaurantController;
import blog.hyojin4588.matzip.user.UserController;

public class HandlerMapper {
	// 각 페이지로 접속을 돕는 멤버 클래스
	private UserController userCon;
	private restaurantController resCon;
	// 각 페이지로 접속을 돕는 멤버 클래스

	// 멤버 클래스의 싱글톤 화
	public HandlerMapper() {
		userCon = new UserController();
		resCon = new restaurantController();
	}
	// 멤버 클래스의 싱글톤 화

	// 호출 당하는 것을 만들면 FrameWork, 호출만 할 거라면 library
	public String nav(HttpServletRequest request) {

		// 호출 시 URI 이름으로 구성된 배열 생성 <시작>
		String[] uriArr = request.getRequestURI().split("/");
		// 호출 시 URI 이름으로 구성된 배열 생성 <끝>

		// uriArr 안에 있는 값들을 확인하는 for문 <시작>
//		System.out.println("배열 길이 : " + uriArr.length);
//		for(int i = 0; i < uriArr.length; i++) {
//			System.out.println(String.format("uriArr[%d] : %s", i, uriArr[i]));  
//		}
		// uriArr 안에 있는 값들을 확인하는 for문 <끝>

		// http://location:8090/(컨트롤러 구분)/(호출할 메소드) 구조이므로, 길이는 항상 3이상이어야 한다.
		if (uriArr.length < 3) {
			return "405"; // 에러 코드 반환
		}
		// http://location:8090/(컨트롤러 구분)/(호출할 메소드) 구조이므로, 길이는 항상 3이상이어야 한다.

		// 페이지 접속기 <시작>
		switch (uriArr[1]) {
		// user로 시작하는 경우 스위치문 <시작>
		case ViewRef.URI_USER:

			// user/*으로 접속을 돕는 스위치문 <시작>
			switch (uriArr[2]) {

			// user/login으로 접속하는 경우
			case "login":
				return userCon.login(request);
			// user/login으로 접속하는 경우

			// user/loginProc으로 접속하는 경우
			case "loginProc":
				return userCon.loginProc(request);
			// user/loginProc으로 접속하는 경우

			// user/join으로 접속하는 경우
			case "join":
				return userCon.join(request);
			// user/join으로 접속하는 경우

			// user/joinProc으로 접속하는 경우
			case "joinProc":
				return userCon.joinProc(request);
			// user/joinProc으로 접속하는 경우

			// user/ajaxIdChk으로 접속하는 경우
			case "ajaxIdChk":
				return userCon.ajaxIdChk(request);
			// user/ajaxIdChk으로 접속하는 경우

			// user/logout으로 접속하는 경우
			case "logout":
				return userCon.logout(request);
			// user/logout으로 접속하는 경우

			}
			// user/*으로 접속을 돕는 스위치문 <끝>
			// user로 시작하는 경우 스위치문 <끝>

			// restaurant로 시작하는 경우 스위치문 <시작>
		case ViewRef.URI_RESTAURANT:

			// restaurant/*로 접속을 돕는 스위치문 <시작>
			switch (uriArr[2]) {

			// restaurant/resMap으로 접속하는 경우
			case "resMap":
				return resCon.resMap(request);
			// restaurant/resMap으로 접속하는 경우

			// restaurant/resReg로 접속하는 경우
			case "resReg":
				return resCon.resReg(request);
			// restaurant/resReg로 접속하는 경우

			// restaurant/resProc로 접속하는 경우
			case "resRegProc":
				return resCon.resRegProc(request);
			// restaurant/resProc로 접속하는 경우

			// restaurant/ajaxResReg로 접속하는 경우
//			case "ajaxResReg":
//				return resCon.ajaxResReg(request);
//			}
			// restaurant/ajaxResReg로 접속하는 경우

			// restaurant/ajaxGetList로 접속하는 경우
			case "ajaxGetList":
				return resCon.ajaxGetList(request);
			// restaurant/ajaxGetList로 접속하는 경우
				
			}
			// restaurant/*로 접속을 돕는 스위치문 <끝>
			// restaurant로 시작하는 경우 스위치문 <끝>

		}
		// 페이지 접속기 <끝>
		
		// 이것도 저것도 아닌 경우 <시작>
		return "404"; // 에러 코드 반환
		// 이것도 저것도 아닌 경우 <끝>
	}
}
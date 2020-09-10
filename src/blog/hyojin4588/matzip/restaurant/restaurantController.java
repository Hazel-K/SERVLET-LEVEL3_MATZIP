package blog.hyojin4588.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;

public class restaurantController {
	
	private restaurantService service;
	
	public restaurantController() {
		service = new restaurantService();
	}
	
	// /restaurant/resMap 으로 접속 시 해당 메소드 실행
	public String resMap(HttpServletRequest request) {
		// 로그인 정보 없으면 로그인으로 돌려보는 메소드 <시작>
		if(!CommonUtils.isLogin(request)) {
			// DISPATCH 방식으로 표시할 경우 <시작>
//			request.setAttribute(Const.TITLE, "로그인");
//			request.setAttribute(Const.VIEW, "user/login");
//			return ViewRef.DEFAULT;
			// DISPATCH 방식으로 표시할 경우 <끝>
			
			// REDIRECT 방식으로 표시할 경우 <시작>
			return "redirect:/user/login";
			// REDIRECT 방식으로 표시할 경우 <끝>
		}
		// 로그인 정보 없으면 로그인으로 돌려보는 메소드 <끝>
		
		request.setAttribute(Const.TITLE, "식당 지도");
		request.setAttribute(Const.VIEW, "/restaurant/resMap");
		return ViewRef.TYPE_1;
	}

}
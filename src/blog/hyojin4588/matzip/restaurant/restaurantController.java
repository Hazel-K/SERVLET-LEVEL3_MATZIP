package blog.hyojin4588.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.CommonDAO;
import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;

public class restaurantController {
	
	private restaurantService service;
	
	public restaurantController() {
		service = new restaurantService();
	}
	
	// /restaurant/resMap 으로 접속 시 해당 메소드 실행
	public String resMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "식당 지도");
		request.setAttribute(Const.VIEW, "/restaurant/resMap");
		return ViewRef.TYPE_1;
	}
	
	// /restaurant/resReg 으로 접속 시 해당 메소드 실행
	public String resReg(HttpServletRequest request) {
		final int i_m = 1; // 카테고리 코드
		request.setAttribute("categoryList", CommonDAO.selCodeList(i_m));
		
		request.setAttribute(Const.TITLE, "가게 등록");
		request.setAttribute(Const.VIEW, "restaurant/resReg");
		
		return ViewRef.TYPE_1;
	}

}
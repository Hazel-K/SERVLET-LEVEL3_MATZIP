package blog.hyojin4588.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.CommonDAO;
import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.vo.RestaurantDomain;
import blog.hyojin4588.matzip.vo.RestaurantVO;

public class restaurantController {
	
	private restaurantService service;
	
	public restaurantController() {
		service = new restaurantService();
	}
	
	// /restaurant/resMap 으로 접속 시 해당 메소드 실행
	public String resMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "식당 지도");
		request.setAttribute(Const.VIEW, "restaurant/resMap");
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
	
	public String resRegProc(HttpServletRequest request) {
		// 속성 추출 및 vo 세팅 <시작>
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String strLat = request.getParameter("lat"); // double로
		double lat = CommonUtils.parseStringToDouble(strLat);
		String strLng = request.getParameter("lng"); // double로
		double lng = CommonUtils.parseStringToDouble(strLng);
		String strCd_category = request.getParameter("cd_category"); // int로
		int cd_category = CommonUtils.parseStringToInt(strCd_category);
		int i_user = SecurityUtils.getLoginUser(request);
		
		// 테스트 케이스
//		System.out.println("strLat : " + strLat + "\nlat" + lat);
//		System.out.println("strLng : " + strLng + "\nlng" + lng);
		// 테스트 케이스
		
		RestaurantVO param = new RestaurantVO();
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(i_user);
		// 속성 추출 및 VO 세팅 <끝>
		
		int result = service.resReg(param); // 성공적으로 수행 시 1 리턴
		
		if (result == 1) {
			return "redirect:/restaurant/resReg";
		}
		
		return "404";
	}
	
	// /restaurant/resDetail 으로 접속 시 해당 메소드 실행
	public String resDetail(HttpServletRequest request) {
		// 속성 추출 및 vo 세팅 <시작>
		String strI_rest = request.getParameter("i_rest");
		int i_rest = CommonUtils.parseStringToInt(strI_rest);
		// 속성 추출 및 vo 세팅 <끝>
		
		RestaurantDomain param = new RestaurantDomain();
		param.setI_rest(i_rest);
		
		request.setAttribute("data", service.getDetail(param));
		
		request.setAttribute(Const.TITLE, "식당 보기");
		request.setAttribute(Const.VIEW, "restaurant/resDetail");
		return ViewRef.TYPE_1;
	}
	
	public String addRecMenusProc(HttpServletRequest request) {
		// 속성 추출 및 vo 세팅 <시작>
		String uploads = request.getRealPath(path);
		String strI_rest = request.getParameter("i_rest");
		int i_rest = CommonUtils.parseStringToInt(strI_rest);
		
		String[] menu_nmArr = request.getParameterValues("menu_nm"); // 배열을 받을 경우 Values 추가
		String[] menu_priceArr = request.getParameterValues("menu_price"); // 배열을 받을 경우 Values 추가
		
		// type = file은 getParameter 계열로 받을 수 없기 때문에 다른 방식으로 가져와야 함
		
		// type = file은 getParameter 계열로 받을 수 없기 때문에 다른 방식으로 가져와야 함
		
		// 테스트 케이스
		for(int i = 0; i < menu_nmArr.length; i++) {
			System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);
		}
		// 테스트 케이스
		// 속성 추출 및 vo 세팅 <끝>
		return "redirect:/restaurant/resDetail?i_rest=" + i_rest;
	}
	
	// 사용하지 않는 메소드
//	public String ajaxResReg(HttpServletRequest request) {
//		// 속성 추출 및 vo 세팅 <시작>
//		String nm = request.getParameter("nm");
//		String addr = request.getParameter("addr");
//		String strLat = request.getParameter("lat"); // double로
//		double lat = CommonUtils.parseStringToDouble(strLat);
//		String strLng = request.getParameter("lng"); // double로
//		double lng = CommonUtils.parseStringToDouble(strLng);
//		String strCd_category = request.getParameter("cd_category"); // int로
//		int cd_category = CommonUtils.parseStringToInt(strCd_category);
//		
//		HttpSession hs = request.getSession();
//		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
//		int i_user = loginUser.getI_user();
//		
//		RestaurantVO param = new RestaurantVO();
//		param.setNm(nm);
//		param.setAddr(addr);
//		param.setLat(lat);
//		param.setLng(lng);
//		param.setCd_category(cd_category);
//		param.setI_user(i_user);
//		// 속성 추출 및 VO 세팅 <끝>
//		int result = service.resReg(param); // 성공적으로 수행 시 1 리턴
//		
//		return String.format("ajax:{\"result\": %s}", result);
//	}
	
	// /restaurant/ 으로 접속시 시작되는 메소드
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + service.getResList();
	}
}
package blog.hyojin4588.matzip.restaurant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.CommonDAO;
import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.vo.RestaurantDomain;
import blog.hyojin4588.matzip.vo.RestaurantRecommendMenuVO;
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
		int i_user = SecurityUtils.getLoginUserPk(request);
		
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
		
		// 가게 주소 및 이름 jsp로 내보내기 <시작>
		RestaurantDomain param = new RestaurantDomain();
		param.setI_rest(i_rest);
		request.setAttribute("data", service.getDetail(param));
		// 가게 주소 및 이름 jsp로 내보내기 <끝>
		
		// 추천 메뉴 정보 및 이미지 jsp로 내보내기 <시작>
		List<RestaurantRecommendMenuVO> paramList = service.selRecList(i_rest);
		request.setAttribute("recommendMenuList", paramList);
		// 추천 메뉴 정보 및 이미지 jsp로 내보내기 <끝>
		
		// 추천 메뉴 이미지 내보내기
		List<RestaurantRecommendMenuVO> paramList2 = service.selMenuList(i_rest);
		request.setAttribute("menuList", paramList2);
		// 추천 메뉴 이미지 내보내기
		
		// css 적용할 파일 이름 리스트 내보내기 <시작>
		String[] cssList = {"resDetail"};
		request.setAttribute("css", cssList);
		// css 적용할 파일 이름 리스트 내보내기 <끝>
		
		request.setAttribute(Const.TITLE, "식당 보기");
		request.setAttribute(Const.VIEW, "restaurant/resDetail");
		return ViewRef.TYPE_1;
	}
	
	// 가게 메뉴 삽입 프로시저
	public String addMenusProc(HttpServletRequest request) {
		int i_rest = service.addMenus(request);
		return "redirect:/restaurant/resDetail?i_rest=" + i_rest;
	}
	
	// /restaurant/addRecMenusProc 으로 접속 시 해당 메소드 실행
	public String addRecMenusProc(HttpServletRequest request) {
		int i_rest = service.addRecMenus(request);
		
		return "redirect:/restaurant/resDetail?i_rest=" + i_rest;
	}
	
	// /restaurant/ajaxDelRecMenu 으로 접속 시 해당 메소드 실행
	public String ajaxDelRecMenu(HttpServletRequest request) {
		int i_user = SecurityUtils.getLoginUserPk(request);
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		int seq = CommonUtils.getIntParameter("seq", request);
		
		RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		param.setI_user(i_user);
		param.setI_rest(i_rest);
		param.setSeq(seq);
		
		int result = service.delRecMenu(param);
		
		return String.format("ajax:{\"result\": %s}", result);
	}
	
	public String ajaxDelMenu(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
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
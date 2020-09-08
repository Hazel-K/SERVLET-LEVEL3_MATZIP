package blog.hyojin4588.matzip.user;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;
import blog.hyojin4588.matzip.vo.UserVO;

public class UserController {
	
	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	
	// /user/login 경로로 접속 시 해당 메소드 실행
	public String login(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "로그인"); // title : "로그인"
		request.setAttribute(Const.VIEW, "/user/login"); // view : "/user/login"
		return ViewRef.DEFAULT; // 결과값 : "/template/default"
	}
	
	// /user/join 경로로 접속 시 해당 메소드 실행
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입"); // title : "회원가입"
		request.setAttribute(Const.VIEW, "/user/join"); // view : "/user/join"
		return ViewRef.DEFAULT; // 결과값 : "/template/default"
	}
	
	// /user/joinProc 경로로 접속 시 해당 메소드 실행
	public String joinProc(HttpServletRequest request) {
		// Parameter 추출 후 VO에 세팅 <시작>
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		// Parameter 추출 후 VO에 세팅 <끝>
		
		int result = service.join(param);
		
		return "redirect:/user/login";
	}
}

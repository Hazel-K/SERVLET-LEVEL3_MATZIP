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
		
		// loginProc 중 오류 처리 구문 <시작>
		String error = request.getParameter("error");
		if (error != null) {
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인해주세요.");
			case "3":
				request.setAttribute("msg", "비밀번호를 확인해주세요.");
			}			
		}
		// loginProc 중 오류 처리 구문 <끝>
		
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
	
	// /user/loginProc 경로로 접속 시 해당 메소드 실행
	public String loginProc(HttpServletRequest request) {
		// Parameter 추출 후 VO에 세팅 <시작>
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		// Parameter 추출 후 VO에 세팅 <끝>
		
		int result = service.login(param);
		
		// 오류처리 스위치문 <시작>
		if (result == 1) {
			return "redirect:/restaurant/resMap"; // 로그인 성공
		} else {
			return "redirect:/user/login?error=" + result; //로그인 실패
		}
		// 오류처리 스위치문 <끝>
	}
}
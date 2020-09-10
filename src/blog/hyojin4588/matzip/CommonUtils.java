package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import blog.hyojin4588.matzip.vo.UserVO;

public class CommonUtils {
	
	// 세션을 가져오는 메소드
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
	
	// 로그인 정보가 있는지를 확인하는 메소드
	public static boolean isLogin(HttpServletRequest request) {
		HttpSession hs = getSession(request);
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		return loginUser != null;
	}

}

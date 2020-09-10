package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtils {
	
	// 세션을 가져오는 메소드
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

}

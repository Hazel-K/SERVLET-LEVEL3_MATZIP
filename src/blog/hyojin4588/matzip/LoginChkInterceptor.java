package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	public static String routerChk(HttpServletRequest request) {

		// 로그인 되어 있으면 login, join 접속하지 않음
		String[] chkUserUriArr = { "login", "loginProc", "join", "joinProc", "ajaxIdChk" };
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");

		if (targetUri.length < 3) {
			return null;
		}

		if (isLogout) {
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for (String uri : chkUserUriArr) {
					if (uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";
		} else {
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for (String uri : chkUserUriArr) {
					if (uri.equals(targetUri[2])) {
						return "/restaurant/resMap";
					}
				}
			}
		}
		// 로그인되지 않았을 때 접속할 수 있는 주소만 체크, 나머진 로그인 되어 있어야 함

		return null;
	}
}

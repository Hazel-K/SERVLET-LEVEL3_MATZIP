package blog.hyojin4588.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtils {
	
	// 세션을 가져오는 메소드
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
	// 세션을 가져오는 메소드
	
	// String을 int형으로 변환시키는 메소드
	public static int parseStringToInt(String arg0) {
		return parseStringToInt(arg0, 0);
	}
	
	public static int parseStringToInt(String arg0, int arg1) {
		try {
			return Integer.parseInt(arg0);
		} catch (Exception e) {
			return arg1;
		}
	}
	// String을 int형으로 변환시키는 메소드
	
	// String을 double형으로 변환시키는 메소드
	public static double parseStringToDouble(String arg0) {
		return parseStringToDouble(arg0, 0);
	}
	
	public static double parseStringToDouble(String arg0, int arg1) {
		try {
			return Double.parseDouble(arg0);
		} catch (Exception e) {
			return arg1;
		}
	}
	// String을 double형으로 변환시키는 메소드

}

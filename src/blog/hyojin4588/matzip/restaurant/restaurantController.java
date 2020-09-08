package blog.hyojin4588.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import blog.hyojin4588.matzip.Const;
import blog.hyojin4588.matzip.ViewRef;

public class restaurantController {
	
	public String resMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "식당 지도");
		request.setAttribute(Const.VIEW, "/restaurant/resMap");
		return ViewRef.TYPE_1;
	}

}

package blog.hyojin4588.matzip.restaurant;

import blog.hyojin4588.matzip.vo.RestaurantVO;

public class restaurantService {
	
	private restaurantDAO dao;
	
	public restaurantService() {
		dao = new restaurantDAO();
	}
	
	public int resReg(RestaurantVO param) {
		return dao.insRestaurant(param);
	}

}
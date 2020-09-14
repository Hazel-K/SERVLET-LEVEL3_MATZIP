package blog.hyojin4588.matzip.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import blog.hyojin4588.matzip.vo.RestaurantDomain;
import blog.hyojin4588.matzip.vo.RestaurantVO;

public class restaurantService {
	
	private restaurantDAO dao;
	
	public restaurantService() {
		dao = new restaurantDAO();
	}
	
	public int resReg(RestaurantVO param) {
		return dao.insRestaurant(param);
	}
	
	public String getResList() {
		List<RestaurantDomain> list = new ArrayList<RestaurantDomain>();
		list = dao.selResList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

}
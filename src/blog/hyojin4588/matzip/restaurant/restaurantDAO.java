package blog.hyojin4588.matzip.restaurant;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import blog.hyojin4588.matzip.db.JdbcTemplate;
import blog.hyojin4588.matzip.db.JdbcUpdateInterface;
import blog.hyojin4588.matzip.vo.RestaurantVO;

public class restaurantDAO {
	
	public int insRestaurant(RestaurantVO param) {
		// SQL문 작성 <시작>
		String sql = " INSERT INTO t_restaurant";
		sql += "(nm, addr, lat, lng, cd_category, i_user) ";
		sql += "VALUES ";
		sql += "(?, ?, ?, ?, ?, ?) ";
		// SQL문 작성 <끝>
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				int seq = 0;
				ps.setString(++seq, param.getNm());
				ps.setString(++seq, param.getAddr());
				ps.setDouble(++seq, param.getLat());
				ps.setDouble(++seq, param.getLng());
				ps.setInt(++seq, param.getCd_category());
				ps.setInt(++seq, param.getI_user());
			}
		});
	}

}

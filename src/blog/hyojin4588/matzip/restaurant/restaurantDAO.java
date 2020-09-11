package blog.hyojin4588.matzip.restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.hyojin4588.matzip.db.JdbcSelectInterface;
import blog.hyojin4588.matzip.db.JdbcTemplate;
import blog.hyojin4588.matzip.db.JdbcUpdateInterface;
import blog.hyojin4588.matzip.vo.RestaurantDomain;
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
	
	public List<RestaurantDomain> selResList() {
		List<RestaurantDomain> list = new ArrayList<RestaurantDomain>();
		
		String sql = " SELECT i_rest, nm, lat, lng FROM t_restaurant ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					RestaurantDomain vo = new RestaurantDomain();
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setNm(rs.getNString("nm"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					list.add(vo);
				}
			}
		});
		return list;
	}

}

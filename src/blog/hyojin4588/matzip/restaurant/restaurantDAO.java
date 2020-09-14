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
	
	public RestaurantDomain selRestaurant(RestaurantDomain param) {
		RestaurantDomain vo = new RestaurantDomain();
		String sql = " SELECT A.i_rest, A.nm, A.addr, A.i_user, A.hits, "
				+ "B.val AS cd_category_nm, IFNULL(C.cnt, 0) AS cnt "
				+ "FROM t_restaurant A "
				
				+ "LEFT JOIN c_code_d B "
				+ "ON A.cd_category = B.cd "
				+ "AND B.i_m = 1 "
				
				+ "LEFT JOIN ( "
				+ "	   SELECT i_rest, COUNT(i_rest) AS cnt "
				+ "	   FROM t_restaurant_recommend_menu "
				+ "    WHERE i_rest = ?"
				+ "    GROUP BY i_rest "
				+ ") C "
				+ "ON A.i_rest = C.i_rest "
				+ "WHERE A.i_rest = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				int i_rest = param.getI_rest();
				ps.setInt(1, i_rest);
				ps.setInt(2, i_rest);
			}
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					vo.setI_rest(param.getI_rest());
					vo.setNm(rs.getNString("nm"));
					vo.setAddr(rs.getNString("addr"));
					vo.setI_user(rs.getInt("i_user"));
					vo.setCntHits(rs.getInt("hits"));
					vo.setCd_category_nm(rs.getNString("cd_category_nm"));
					vo.setCntFavorite(rs.getInt("cntFavorite"));
				}
			}
		});
		
		return vo;
	}

}

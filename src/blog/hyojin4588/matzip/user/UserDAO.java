package blog.hyojin4588.matzip.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.hyojin4588.matzip.db.JdbcSelectInterface;
import blog.hyojin4588.matzip.db.JdbcTemplate;
import blog.hyojin4588.matzip.db.JdbcUpdateInterface;
import blog.hyojin4588.matzip.vo.UserVO;

public class UserDAO {
	
	public int join(UserVO param) {
		boolean isProfile_img = param.getProfile_img() != null; // 프로필 이미지 필요 조건
		
		// JOIN INSERT SQL문 생성 <시작>
		String sql = " INSERT INTO t_user(user_id, user_pw, salt, nm";
		if(isProfile_img) {
			sql += ", profile_img";
		}
		sql += ") VALUES (?, ?, ?, ?";
		if(isProfile_img) {
			sql += ", ?";
		}
		sql += ") ";
		// JOIN INSERT SQL문 생성 <끝>
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				int seq = 0;
				ps.setNString(++seq, param.getUser_id());
				ps.setNString(++seq, param.getUser_pw());
				ps.setNString(++seq, param.getSalt());
				ps.setNString(++seq, param.getNm());
				if (isProfile_img) {
					ps.setNString(++seq, param.getProfile_img());
				}
			}
		});
	}
	
	public UserVO selUser(UserVO param) {
		
		UserVO result = new UserVO();
		
		// User SELECT SQL문 생성 <시작>
		String sql = " SELECT i_user, user_id, user_pw, salt, nm, profile_img, r_dt, m_dt "
				+ "FROM t_user WHERE ";
		if(param.getI_user() > 0) {
			sql += " i_user = " + param.getI_user();
		} else if(param.getUser_id() != null && !"".equals(param.getUser_id())) {
			sql += " user_id = '" + param.getUser_id() + "' ";
		}
		// User SELECT SQL문 생성 <끝>
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}
			
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					int i_user = rs.getInt("i_user");
					String user_id = rs.getString("user_id");
					String user_pw = rs.getString("user_pw");
					String salt = rs.getString("salt");
					String nm = rs.getString("nm");
					String profile_img = rs.getString("profile_img");
					String r_dt = rs.getString("r_dt"); // date에 NString 달면 오류 생김
					String m_dt = rs.getString("m_dt"); // date에 NString 달면 오류 생김
					
					result.setI_user(i_user);
					result.setUser_id(user_id);
					result.setUser_pw(user_pw);
					result.setSalt(salt);
					result.setNm(nm);
					result.setProfile_img(profile_img);
					result.setR_dt(r_dt);
					result.setM_dt(m_dt);
				}
			}
		});
		
		return result;
	}

}
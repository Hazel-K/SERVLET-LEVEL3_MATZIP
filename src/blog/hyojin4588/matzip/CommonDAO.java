package blog.hyojin4588.matzip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.hyojin4588.matzip.db.JdbcSelectInterface;
import blog.hyojin4588.matzip.db.JdbcTemplate;
import blog.hyojin4588.matzip.vo.CodeDomain;

public class CommonDAO {
	
	// resReg.jsp에 select 태그의 value를 채우는 list
	public static List<CodeDomain> selCodeList(final int i_m) {
		// List는 interface임
		List<CodeDomain> list = new ArrayList<CodeDomain>(); // val을 담을 list 생성
		String sql = " SELECT i_m, cd, val FROM c_code_d WHERE i_m = ? "; // sql문 작성
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_m);
			}
			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					CodeDomain cd = new CodeDomain();
					cd.setI_m(rs.getInt("i_m"));
					cd.setCd(rs.getInt("cd"));
					cd.setVal(rs.getNString("val"));
					
					list.add(cd);
				}
			}
		});
		
		return list;
	}

}

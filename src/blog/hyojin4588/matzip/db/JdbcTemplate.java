package blog.hyojin4588.matzip.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
	
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.update(ps);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps);
		}
		
		return result;
	}
	
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.prepared(ps);
			rs = ps.executeQuery();
			jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps);
		}
	}
	
}

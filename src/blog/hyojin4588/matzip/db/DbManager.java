package blog.hyojin4588.matzip.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {
	
	// test용 메소드. java파일 우클릭 - run as - java application으로 실행 후 결과 확인
//	public static void main(String args[]) {
//		try {
//			getCon();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	// test용 메소드. java파일 우클릭 - run as - java application으로 실행 후 결과 확인
	
	public static Connection getCon() throws Exception {
		// mysql DB 접속
		String url = "jdbc:mysql://127.0.0.1:3306/matzip"; // ip부분을 localhost로 대체 가능
		String user = "root";
		String pw = "koreait2020";
		String className = "com.mysql.cj.jdbc.Driver"; // library path
		
		Class.forName(className);
		Connection con = DriverManager.getConnection(url, user, pw);
//		System.out.println("DB 연결 완료");
		
		return con;
		// mysql DB 접속
	}
}

package blog.hyojin4588.matzip.user;

import blog.hyojin4588.matzip.SecurityUtils;
import blog.hyojin4588.matzip.vo.UserVO;

public class UserService {
	
	private UserDAO dao;
	
	public UserService() {
		dao = new UserDAO();
	}
	
	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setUser_pw(encryptPw);
		param.setSalt(salt);
		
		return dao.join(param);
	}
	
	public int login(UserVO param) {
		int result = 0;
		
		UserVO dbResult = dao.selUser(param);
		
		// 오류처리 구문 <시작>
		if(dbResult.getI_user() == 0) {
			result = 2; // 없는 아이디
		} else {
			String salt = dbResult.getSalt();
			String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt);
			
			if(encryptPw.equals(dbResult.getUser_pw())) {
				result = 1; // 로그인 성공
			} else {
				result = 3; // 비밀번호 틀림
			}
		}
		
		// 오류처리 구문 <끝>
		return result;
	}

}
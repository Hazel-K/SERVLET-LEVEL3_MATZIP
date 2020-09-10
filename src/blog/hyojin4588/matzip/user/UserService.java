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
				// 세션에 넣을 로그인 정보 설정 <시작>
				param.setUser_pw(null); // 비밀번호 초기화
				
//				param = dbResult; // 이런 식으로 하면 안됨. reference변수이기 때문에 메소드가 끝난 이후 param에 박힌 dbResult에 해당하는 주소값을 불러올 수 없음
				param.setI_user(dbResult.getI_user());
				param.setUser_id(dbResult.getUser_id());
				param.setNm(dbResult.getNm());
				param.setProfile_img(dbResult.getProfile_img());
				// 세션에 넣을 로그인 정보 설정 <끝>
				
				result = 1; // 로그인 성공
			} else {
				result = 3; // 비밀번호 틀림
			}
		}
		
		// 오류처리 구문 <끝>
		return result;
	}

}
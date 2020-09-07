package blog.hyojin4588.matzip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") // "/"만 설정해줄 경우, WebContent 폴더에 있는 여타 다른 파일로의 접근이 불가능해짐
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 본 SERVLET URI와 URL값 확인 <시작>
		System.out.println("uri : " + request.getRequestURI()); // uri : /main
		System.out.println("url : " + request.getRequestURL()); // url : http://localhost:8089/main
		// 본 SERVLET URI와 URL값 확인 <끝>
		
		// 첫번째 값이 res인거 뽑아내는 작업 <시작>
		String[] uriArr = request.getRequestURI().split("/"); // "/"를 기준으로 각 문자열을 배열로 재구성
		
		// uriArr 안에 있는 값들을 확인하는 for문 <시작>
		for(int i = 0; i < uriArr.length; i++) {
			System.out.println(String.format("uriArr[%d] : %s", i, uriArr[i]));
		}
		// uriArr 안에 있는 값들을 확인하는 for문 <끝>
		// 첫번째 값이 res인거 뽑아내는 작업 <끝>#
		// test
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
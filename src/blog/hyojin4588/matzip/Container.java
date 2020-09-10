package blog.hyojin4588.matzip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Security;

@WebServlet("/") // "/"만 설정해줄 경우, .xml파일을 수정하기 전까지는 WebContent 폴더에 있는 여타 다른 파일로의 접근이 불가능해짐
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapper mapper;

	public Container() {
		mapper = new HandlerMapper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 본 SERVLET URI와 URL값 확인 <시작>
//		System.out.println("uri : " + request.getRequestURI());
//		System.out.println("url : " + request.getRequestURL());
		// 본 SERVLET URI와 URL값 확인 <끝>

		// 첫번째 값이 res인거 뽑아내는 작업 <시작>
//		String[] uriArr = request.getRequestURI().split("/"); // "/"를 기준으로 각 문자열을 배열로 재구성

		// uriArr 안에 있는 값들을 확인하는 for문 <시작>
//		for(int i = 0; i < uriArr.length; i++) {
//			System.out.println(String.format("uriArr[%d] : %s", i, uriArr[i])); 
		// uriArr[1] : favicon.ico (http://localhost:8089/res/js/test.js 찍었을 경우 결과값)
//		}
		// uriArr 안에 있는 값들을 확인하는 for문 <끝>
		// 첫번째 값이 res인거 뽑아내는 작업 <끝>

		proc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response);
	}

	private void proc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = mapper.nav(request); // URI 주소 받기
		
		// 로그인 정보 없으면 로그인으로 돌려보내는 메소드 <시작>
		String routerCheckResult = LoginChkInterceptor.routerChk(request);
		
		// 테스트 케이스
//		System.out.println("RCR value : " + routerCheckResult);
//		System.out.println("RCR location : " + String.format("/WEB-INF/view%s.jsp", routerCheckResult));
		// 테스트 케이스
		
		if(routerCheckResult != null) {
			response.sendRedirect(routerCheckResult);
			return;
		}
		// 로그인 정보 없으면 로그인으로 돌려보내는 메소드 <끝>
		
		// 테스트 케이스
//		System.out.println("temp : " + temp); // temp값 확인
//		System.out.println("redirect:/user/login".substring("redirect:/user/login".indexOf("/"))); // subString의 기능 확인
//		System.out.println("redirect:/user/login".substring(0, "redirect:/user/login".indexOf("/"))); // subString의 기능 확인
		// 테스트 케이스

		// temp안에 "/"가 존재할 때 조건문 <시작>
		if (temp.indexOf(":") >= 0) {
			String prefix = temp.substring(0, temp.indexOf(":"));
			String value = temp.substring(temp.indexOf(":") + 1);
			
			// 테스트 케이스
//			System.out.println("rediect 주소 : " + value);
			// 테스트 케이스
			
			if ("redirect".equals(prefix)) {
				// redirect:라는 글자가 있다면 redirect:라고 적힌 다음 경로를 추출해 그 페이지로 이동 <시작>
				response.sendRedirect(value);
				return;
				// redirect:라는 글자가 있다면 redirect:라고 적힌 다음 경로를 추출해 그 페이지로 이동 <끝>
			} else if("ajax".equals(prefix)) {
				// ajax통신 처리 구문 <시작>
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(value);
				return;
				// ajax통신 처리 구문 <끝>
			}
			
		}
		// temp안에 "/"가 존재할 때 조건문 <끝>

		// login 에러처리 스위치문 <시작>
		switch (temp) {
		case "404": // uriArr.length <= 3 && !(siwtch(uriArr[1]))
			temp = "/WEB-INF/view/error.jsp";
			break;
		case "405": // uriArr.length > 3
			temp = "/WEB-INF/view/notFound.jsp";
			break;
		}
		// login 에러처리 스위치문 <끝>

		// Mapper에서 요청받은 홈페이지 출력
		request.getRequestDispatcher(temp).forward(request, response);
		// Mapper에서 요청받은 홈페이지 출력
	}

}
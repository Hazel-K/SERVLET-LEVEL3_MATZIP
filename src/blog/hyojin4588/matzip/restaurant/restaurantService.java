package blog.hyojin4588.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import blog.hyojin4588.matzip.CommonUtils;
import blog.hyojin4588.matzip.FileUtils;
import blog.hyojin4588.matzip.vo.RestaurantDomain;
import blog.hyojin4588.matzip.vo.RestaurantRecommendMenuVO;
import blog.hyojin4588.matzip.vo.RestaurantVO;

public class restaurantService {

	private restaurantDAO dao;

	public restaurantService() {
		dao = new restaurantDAO();
	}

	public int resReg(RestaurantVO param) {
		return dao.insRestaurant(param);
	}

	public String getResList() {
		List<RestaurantDomain> list = new ArrayList<RestaurantDomain>();
		list = dao.selResList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public RestaurantDomain getDetail(RestaurantDomain param) {
		return dao.selRestaurant(param);
	}
	
	public int addMenus(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		System.out.println("i_rest : " + i_rest);
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant"); // 절대 경로
		String userPath = savePath + "/" + i_rest;
		String targetPath = userPath + "/menu";
		FileUtils.makeFolder(targetPath);
		
		RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
		vo.setI_rest(i_rest);
		
		try {
			System.out.println(request.getContentType());
			for(Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();
				System.out.println("fileName : " + fileName);
				if(fileName != null) {
					// 파일 저장
					String ext = FileUtils.getExt(fileName);
					String saveFileNm = UUID.randomUUID() + ext;
					part.write(targetPath + "/" + saveFileNm); // "/" 대신 File.seperator 도 가능
					// 파일 저장
					
					// db에 추가
					vo.setMenu_pic(saveFileNm);
					dao.insMenu(vo);
					// db에 추가
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return i_rest;
	}

	public int addRecMenus(HttpServletRequest request) {
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant"); // 절대 경로
		String tempPath = savePath + "/temp"; // 임시 주소값 / 쓰면 리눅스 windows 둘 다 돌아간다		
		FileUtils.makeFolder(tempPath);
		
		int maxFileSize = 10_485_760; //1024 * 1024 * 10 (10mb) //최대 파일 사이즈 크기
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendMenuVO> list = null;
		
		try {
			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			i_rest = CommonUtils.getIntParameter("i_rest", multi);
			
			System.out.println("i_rest : " + i_rest);
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
			
			// 값이 없으면 종료
			if(menu_nmArr == null || menu_priceArr == null) {
				return i_rest;
			}
			// 값이 없으면 종료
			
			list = new ArrayList();
			for(int i=0; i<menu_nmArr.length; i++) {
				RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
				vo.setI_rest(i_rest);
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
				list.add(vo);
			}
			
			String targetPath = savePath + "/" + i_rest;
			FileUtils.makeFolder(targetPath);
			
			String originFileNm = "";
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()) {		
				String key = (String)files.nextElement();
				System.out.println("key : " + key);
				originFileNm = multi.getFilesystemName(key);
				System.out.println("originFileNm : " + originFileNm);
				
				if(originFileNm != null) {
					String ext = FileUtils.getExt(originFileNm);
					String saveFileNm = UUID.randomUUID() + ext;
					
					System.out.println("saveFileNm : " + saveFileNm);				
					File oldFile = new File(tempPath + "/" + originFileNm);
				    File newFile = new File(targetPath + "/" + saveFileNm);
				    oldFile.renameTo(newFile);
				    
				    int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));				    
				    RestaurantRecommendMenuVO vo = list.get(idx);
				    vo.setMenu_pic(saveFileNm);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(list != null) {
			for(RestaurantRecommendMenuVO vo : list) {
				dao.insRecommendMenu(vo);
			}
		}
		
		return i_rest;
	}
	
	public List<RestaurantRecommendMenuVO> selMenuList(int i_rest) {
		return dao.selMenuList(i_rest);
	}
	
	public List<RestaurantRecommendMenuVO> selRecList(int i_rest) {
		return dao.selRecommendMenuList(i_rest);
	}	
	
	public int delRecMenu(RestaurantRecommendMenuVO param) {
		return dao.delRecommendMenuList(param);
	}

}
package action.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ListMangerService;
import svc.product.RacketListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ListManagerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ActionForward forward = null;
	    ArrayList<ProductBean> listAll= new ArrayList<ProductBean>();
	    
	    

		int page=1;//출력될 페이지의 기본값1로 설정
		int limit=12;//한페이지당 출력될 글의 갯수 
	    
	    
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
	    

	
	    
	    
	    ListMangerService listMangerService = new ListMangerService();
	   
	    int listCount=listMangerService.getListCount();
	   
	    listAll= listMangerService.getlistAll(page,limit);
	    
	    int maxPage=(int)((double)listCount/limit+0.95); //총 페이지 수 계산 =>총글의갯수/출력될글의 갯수에 0.95를 하면 정수올림할수있음
	   	
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   
   	     int endPage = startPage+5-1;//끝페이지 구함, 첫페이지1=>끝10 첫페이지 3=>12  +5부분을 조절하면 페이지수를 조절?
   	        
   		//총 페이지 수가 10개가 안될경우 
   	     //페이지 리스트가 1~8(maxPage)까지 밖에 없을경우 끝페이지를 maxPsage로 처리
   	     if (endPage> maxPage) {
   	    	 endPage= maxPage;
   	     }
   		PageInfo pageInfo = new PageInfo();//page객체 생성 vo.PageInfo 페이지정보를 자주쓸경우 클래스작성하여 사용
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		
		request.setAttribute("pageInfo", pageInfo);
	   
	   request.setAttribute("listAll", listAll);
	   forward = new ActionForward("/product/productList.jsp", false);
		return forward;
	}

}

package action.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.RacketListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class SearchPriceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ArrayList<ProductBean> listRacket = new ArrayList<ProductBean>();
		
		int page=1;//출력될 페이지의 기본값1로 설정
		int limit=6;//한페이지당 출력될 글의 갯수 
		String prod_kind=request.getParameter("prod_kind");

		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		RacketListService racketListService = new RacketListService();
		
		int listCount=racketListService.getListCount(prod_kind);//총 글의 개수를 반환하는 메서드 호출
		if(request.getParameter("line").equals("1")) {
		listRacket = racketListService.getlowRacketList(page, limit,prod_kind);	
		}else if(request.getParameter("line").equals("0")) {
		listRacket = racketListService.gethightRacketList(page, limit,prod_kind);	
		}else {
		listRacket = racketListService.getRacketList(page,limit,prod_kind);//지정한페에지에 출력될 글목록을 반환하는 메서드 호출
		}
		
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
		request.setAttribute("listRacket", listRacket);
		

		ActionForward forward = new ActionForward("/product/productView.jsp", false);
       
       
       
		return forward;
	}

}

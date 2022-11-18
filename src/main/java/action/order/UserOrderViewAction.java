package action.order;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.order.UserOrderViewService;
import vo.ActionForward;
import vo.OrderBean;
import vo.PageInfo;

public class UserOrderViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		
		HttpSession session = request.getSession();				
		
		String mem_id=(String)session.getAttribute("mem_id");
		System.out.println("주문내역조회 mem_id="+mem_id);
		
		
		if(mem_id==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 해주세요');");
			out.println("location.href='loginForm.jsp';");
			out.println("</script>");
		}else {

			int page=1;//출력될 페이지의 기본값1로 설정
			int limit=5;//한페이지당 출력될 글의 갯수 
			

			if(request.getParameter("page")!=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			
			
			UserOrderViewService userOrderViewService = new UserOrderViewService();
			
			int listCount=userOrderViewService.getUserOrderListCount(mem_id);
			ArrayList<OrderBean> userOrderList = userOrderViewService.getUserOrderList(mem_id,page,limit);

			int maxPage=(int)((double)listCount/limit+0.95); //총 페이지 수 계산 =>총글의갯수/출력될글의 갯수에 0.95를 하면 정수올림할수있음
		   	
		   	int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		   
		   	int endPage = startPage+5-1;//끝페이지 구함, 첫페이지1=>끝10 첫페이지 3=>12  +5부분을 조절하면 페이지수를 조절?
		
		    if (endPage> maxPage) {
	  	    	 endPage= maxPage;
	  	     }
	  		PageInfo pageInfo = new PageInfo();//page객체 생성 vo.PageInfo 페이지정보를 자주쓸경우 클래스작성하여 사용
	  		pageInfo.setEndPage(endPage);
	  		pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);	
			
			request.setAttribute("userOrderList", userOrderList);
			request.setAttribute("pageInfo", pageInfo);
			
			forward = new ActionForward("/order/myOrderPage.jsp", false);
		
		}
		return forward;
	}

		
		
		
		
		
		
	
}

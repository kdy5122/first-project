package action.order;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.order.OrderDeleteService;
import svc.order.OrderInsertService;
import svc.user.UserViewService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
	
		HttpSession session = request.getSession();
		String order_id=request.getParameter("order_id");
		String mem_id=request.getParameter("mem_id");
		int totalPrice =Integer.parseInt(request.getParameter("totalPrice"));
		System.out.println(totalPrice);
		OrderDeleteService orderDeleteService=new OrderDeleteService();
		
		
		
		//재고수 다시 증가
		orderDeleteService.updateProductQty(order_id);
		
		//사용포인트 구매자에게 돌려줌
		orderDeleteService.returnPoint(order_id,mem_id);
		
		//주문테이블+주문상세테이블 데이터 삭제
		boolean deleteSuccess=orderDeleteService.deleteOrder(order_id);
		
		//멤버등급조정
		orderDeleteService.updateMemberGrade(mem_id);
		
		//조정된 멤버등급 얻기
		UserViewService userViewService = new UserViewService();
		String mem_grade= userViewService.getUserInfo(mem_id).getMem_grade();
		
		
		//적립된 포인트 회수
		
		int usePoint=0;
		int point_Rate=0;
		OrderInsertService orderInsertService =new OrderInsertService();
		point_Rate=orderInsertService.getPointRate(mem_grade);
		System.out.println("포인트율:"+point_Rate);
		
		usePoint = (int)(totalPrice*point_Rate/100.0);
		
		
		 orderInsertService.usePoint(mem_id,usePoint);
		 
		 
		 
		 
		 if(!deleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
		
		
		forward=new ActionForward("manageOrderList.od",false);
			
			
			}
		
		return forward;
	}

}

package action.order;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.order.UpdateOrderStatusService;
import svc.order.UserCancleOrderService;
import vo.ActionForward;

public class UserCancleOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward= null;
			
		 String order_id=request.getParameter("order_id");

			HttpSession session = request.getSession();
		 String mem_id = (String)session.getAttribute("mem_id");
		
		
		 

		 //유저가 주문취소를 누르면 바로 취소되는게 아니라 주문상태를 '취소요청'으로 변경하여 판매자에게 전달
		 UserCancleOrderService userCancleOrderService = new UserCancleOrderService();
		 
		 
		 boolean isUpdateSuccess=userCancleOrderService.orderCancel(order_id);
		// updateOrderStatusService.updateMemberGradeService(order_id);

		
		 if(!isUpdateSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
		
				
				forward=new ActionForward("userOrderView.od?mem_id="+mem_id,false);
				
			
			}
		 

		 return forward;
	}

}

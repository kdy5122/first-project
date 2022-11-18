package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.UserOrderDetailService;
import vo.ActionForward;
import vo.OrderDetail;

public class UserOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		String order_id=request.getParameter("order_id");
		
		
		
		UserOrderDetailService userOrderDetailService =new UserOrderDetailService();
		
		ArrayList<OrderDetail> orderDetailList = userOrderDetailService.getOrderDetailList(order_id);		
		
		ArrayList<String> orderUserInfo=userOrderDetailService.getOrderUserList(order_id);
		
		request.setAttribute("orderDetailList", orderDetailList);
		request.setAttribute("orderUserInfo", orderUserInfo);
		
		forward = new ActionForward("/order/OrderDetailPage.jsp", false);
		
		
	
			return forward;
}

}
package action.order;

import java.io.PrintWriter;
import java.net.URI;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.UpdateOrderStatusService;
import vo.ActionForward;

public class UpdateOrderStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward= null;
			
		 String order_id=request.getParameter("order_id");
		 String o_status=request.getParameter("o_status");
		
		
		 

		 
		 UpdateOrderStatusService updateOrderStatusService = new UpdateOrderStatusService();
		 
		 
		 boolean isUpdateSuccess=updateOrderStatusService.updateOrderStatus(order_id,o_status);
		
		
		 
		 
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
		
				
				forward=new ActionForward("manageOrderList.od",false);
				
			
			}
		 

		
		 
		 
		return forward;
			
}
}
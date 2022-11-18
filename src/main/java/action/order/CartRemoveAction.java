package action.order;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.CartRemoveService;
import vo.ActionForward;

public class CartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//파라미터 값으로 전송된 utf-8로 인코딩된 m_id를 얻어와 
		int prod_id =Integer.parseInt(request.getParameter("prod_id"));
		
		if(prod_id<0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 상품을 선택해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			CartRemoveService cartRemoveService = new CartRemoveService();
			cartRemoveService.cartRemove(prod_id, request);
			
			forward = new ActionForward("cartList.od", true);
		}
		return forward;
	}

}

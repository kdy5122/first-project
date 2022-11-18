package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.CartRemoveAllService;
import vo.ActionForward;

public class CartRemoveAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		CartRemoveAllService cartRemoveAllService = new CartRemoveAllService();
		cartRemoveAllService.cartRemoveAll(request);
			
		forward = new ActionForward("cart.jsp", true);		
		return forward;
	}

}

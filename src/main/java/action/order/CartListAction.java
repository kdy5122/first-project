package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.CartListService;
import vo.ActionForward;
import vo.Cart;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		
		CartListService cartListService = new CartListService();
		ArrayList<Cart> cartList = CartListService.getCartList(request);
		
		int total=0;
		
		for (int i = 0; i < cartList.size(); i++) {
			//money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			//totalMoney += money;
			
			total+=cartList.get(i).getTotalMoney();
		}

		request.setAttribute("total", total);
		request.setAttribute("cartList", cartList);
		
		//기존요청
		forward = new ActionForward("/cart.jsp", false);//dispatch 방식
		
		return forward; 
		
		
		
		
		
		
	}

}

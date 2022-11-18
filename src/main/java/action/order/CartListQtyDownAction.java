package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.CartListQtyDownService;
import svc.order.CartListQtyUpService;
import vo.ActionForward;

public class CartListQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		//수량을 증가시킬 대상이 되는 장바구니 항목의 "한글 인코딩처리한 m_id값"을 파라미터 값으로 얻어와
		int prod_id = Integer.parseInt(request.getParameter("prod_id"));
		
		//장바구니 해당 메뉴의 수량을 1 감소
		CartListQtyDownService cartListQtyDownService = new CartListQtyDownService();
		CartListQtyDownService.downCartQty(prod_id, request);
		
		//장바구니 해당 메뉴의 수량을 1 증가시킨 후 '장바구니 목록보기'요청을 다시 하기 위해서
		forward = new ActionForward("cartList.od", true);
		return forward;
	}

}

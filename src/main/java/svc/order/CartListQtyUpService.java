package svc.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class CartListQtyUpService {

	public void upCartQty(int prod_id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		//장바구니 목록 객체에서 수량을 증가시킬 대상을 m_id값으로 비교하여 검색한 후 해당 메뉴의 수량을 1증가
		for(int i=0 ; i<cartList.size(); i++) {
			if(cartList.get(i).getProd_id()==prod_id) {
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
				cartList.get(i).setTotalMoney(cartList.get(i).getPrice(), cartList.get(i).getQty());;
				break;//1증가시킨 후 반복문 빠져나감
			}
		}
	}

}

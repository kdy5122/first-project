package svc.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class CartRemoveService {

	public void cartRemove(int prod_id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		/* 장바구니 목록 객체에서 삭제시킬 대상을 m_id값으로 비교하여 검색한 후 해당 메뉴 삭제 */
		for(int i=0 ; i<cartList.size(); i++) {
			if(cartList.get(i).getProd_id()==prod_id) {
				cartList.remove(cartList.get(i));//장바구니 목록에서 삭제 
				break;//삭제시킨 후 반복문 빠져나감
			}
		}
		
	}

}

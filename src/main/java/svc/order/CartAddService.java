package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import vo.Cart;
import vo.ProductBean;


public class CartAddService {

	public ProductBean getProduct(int prod_id) {
		// TODO 자동 생성된 메소드 스텁
		
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		ProductDAO productDAO = ProductDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		productDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		ProductBean product = productDAO.selectProduct(prod_id);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		
		return product;
	}

	public void addCart(HttpServletRequest request, ProductBean product, int o_amount) {
		/*--현재 session영역에 저장되어 있는 장바구니 목록을 얻어와--*/
		HttpSession session = request.getSession();
		//session영역에 설정된 이름 "cartList"의 속성값을 얻어와
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
	
		}
		//지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 저장할 변수
				boolean isNewCart = true;
				//기존에 장바구니 항목이 존재하면 같은 상품을 찾아서 수량을 1증가
				for(int i=0 ; i<cartList.size() ; i++) {
					if(product.getProd_id()==cartList.get(i).getProd_id()) {
						isNewCart = false;
						cartList.get(i).setQty(cartList.get(i).getQty()+o_amount);//수량 1증가
						break;
					}
				}
		

			if(isNewCart) {//지금 장바구니에 담는 항목이 새로 추가되는 항목이면
				Cart cart = new Cart();//기본값으로 채워진 Cart객체를
				cart.setProd_id(product.getProd_id());//매개값으로 전송된 menuInfo값으로 채운 후
				cart.setProd_name(product.getProd_name());
				cart.setPrice(product.getProd_price());
				cart.setImage(product.getProd_image());
				cart.setQty(o_amount);//수량은 처음이므로 1로 셋팅
				cart.setTotalMoney(product.getProd_price(), o_amount);
				cartList.add(cart);//cartList에 추가
			}
	}

}

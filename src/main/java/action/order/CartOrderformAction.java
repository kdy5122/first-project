package action.order;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.Member.MemberViewService;
import vo.ActionForward;
import vo.Cart;
import vo.MemberBean;
import vo.ProductBean;

public class CartOrderformAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		//장바구니의 주문 총 금액을 얻어옴
		int total=Integer.parseInt(request.getParameter("total"));
		
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		
		String mem_id = (String)session.getAttribute("mem_id");
		MemberViewService memberViewService = new MemberViewService();
		MemberBean member = memberViewService.getMember(mem_id);
		int mem_point=member.getMem_point();
		
		if(cartList == null) {//cartList(장바구니 목록)이 없으면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('주문할 제품을 선택해주세요.')");//알림창 띄우고
			out.println("history.back()");//이전상태로
			out.println("</script>");
		}else {//cartList(장바구니 목록)이 있으면
			
				//장바구니 총액 그대로 request에 담아 주기
					request.setAttribute("total", total);

					request.setAttribute("mem_point", mem_point); 
					forward = new ActionForward("/order/cartOrder_form.jsp", false);				
					
			
			
				
		}		
		
		
		
		
		
		
		
		
		return forward;
	}

}

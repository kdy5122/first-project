package action.order;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

import svc.order.CartAddService;
import vo.ActionForward;
import vo.ProductBean;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		

		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		System.out.println("----------------------------");
		
		//장바구니 담기위해 로그인상태 확인
		HttpSession session = request.getSession();
		String mem_id=(String)session.getAttribute("mem_id");
		int prod_id=Integer.parseInt(request.getParameter("prod_id"));
		int o_amount=Integer.parseInt(request.getParameter("o_amount"));
		int prod_amount=Integer.parseInt(request.getParameter("prod_amount"));

		if(mem_id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.');");
			out.println("location.href='loginForm.jsp';");
			out.println("</script>");
	
		}else if(o_amount > prod_amount){
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('재고가 부족합니다.판매자에게 문의해주세요');");
	            
	            out.println("history.back()");//상대경로로 (java코드이므로 EL이나 JSTL 사용못함)
	            //out.println("location.href='./userLogin.usr';");
	            
	            out.println("</script>");
	        }
		
	
		else {			
			CartAddService cartAddService = new CartAddService();
			
			//prod_id를 파라미터로 받아서 그에 해당하는 제품정보를 얻기위함
			ProductBean product = cartAddService.getProduct(prod_id);
			
			/* 장바구니에 담기 위해 매개값으로 전송
			 * (이 때, request객체도 전송: 이유?★★ 장바구니 항목을 유지할 수 있도록 session영역에 추가해야 하므로 )*/
			cartAddService.addCart(request, product,o_amount);
			
			
			
			//★반드시 리다이렉트로 포워딩 : 장바구니 항목에 새롭게 추가했으므로
			forward = new ActionForward("cartList.od", true);
		}
		
		
		
		
		
		
		
		
		
		return forward;
	}

}

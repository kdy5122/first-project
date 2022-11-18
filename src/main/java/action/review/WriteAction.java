package action.review;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.OrderItemNameService;
import vo.ActionForward;

public class WriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//먼저, 글작성하기 위해 로그인된 상태인지를 확인해야 함(세션 영역에서)
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		
		if(mem_id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.');");
			
			out.println("location.href='loginForm.jsp';");//상대경로로 (java코드이므로 EL이나 JSTL 사용못함)
			//out.println("location.href='./userLogin.usr';");
			
			out.println("</script>");
		}else {
			
			OrderItemNameService orderItemNameService=new OrderItemNameService();
			
			Map<Integer,String>  orderList=new LinkedHashMap<Integer,String>();
			orderList=orderItemNameService.getOrderList(mem_id);
			
			request.setAttribute("orderList", orderList);
			
			
			
			
			forward = new ActionForward("/review/review_board_form.jsp", false); //세션이기 때문에 
		}
		return forward;
	}

}

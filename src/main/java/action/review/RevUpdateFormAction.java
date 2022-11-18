package action.review;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.OrderItemNameService;
import svc.review.RevDetailViewService;
import vo.ActionForward;
import vo.ReviewBean;

public class RevUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


	
		
		ActionForward forward = null;
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		
		
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		
		
		

		if(mem_id == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			
			out.println("alert('잘못된 접근입니다.');");
			
			out.println("location.href='main.jsp';");//상대경로로 (java코드이므로 EL이나 JSTL 사용못함)
			//out.println("location.href='./userLogin.usr';");
			
			out.println("</script>");
		
		}
		
		
		

		else {
		
		//리뷰쓰는 form 을 불러오기전에 글내용 상세보기...의 정보들을 얻어와야하므로 new RevDetailViewService
	 	 forward = new ActionForward();
		
	 	response.setContentType("text/html;charset=utf-8");
	 	
		RevDetailViewService revDetailViewService =new RevDetailViewService();
		ReviewBean article= revDetailViewService.getArticle(board_no);
		
		OrderItemNameService orderItemNameService=new OrderItemNameService();
		
		Map<Integer,String>  orderList=new LinkedHashMap<Integer,String>();
		orderList=orderItemNameService.getOrderList(mem_id);
		
		request.setAttribute("orderList", orderList);
		request.setAttribute("article", article);//el 언어사용할ㄸ'ㅐ 속성명..
		//이 얻은 정보들을 form의 value값에 넣어주면 됨
		
		
		//위에서 얻은 정보들을 글쓰기입력란에 그대로 뿌려주면 됨.
		 forward = new ActionForward("/review/review_update_form.jsp", false);
		
			}
	
		return forward;
	}

}

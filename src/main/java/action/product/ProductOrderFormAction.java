package action.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.Member.MemberViewService;
import vo.ActionForward;
import vo.MemberBean;

public class ProductOrderFormAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;
      
      
      HttpSession session = request.getSession();
      String mem_id = (String)session.getAttribute("mem_id");
      int o_amount=Integer.parseInt(request.getParameter("o_amount"));
      int prod_amount=Integer.parseInt(request.getParameter("prod_amount"));
      
      if(mem_id == null) {
          response.setContentType("text/html;charset=utf-8");
          PrintWriter out = response.getWriter();
          out.println("<script>");
          out.println("alert('주문은 로그인후 가능합니다.');");
          
          out.println("location.href='loginForm.jsp';");//상대경로로 (java코드이므로 EL이나 JSTL 사용못함)
          //out.println("location.href='./userLogin.usr';");
          
          out.println("</script>");
    
    
      
      }else if(o_amount > prod_amount){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('재고가 부족합니다.');");
            
            out.println("history.back()");//상대경로로 (java코드이므로 EL이나 JSTL 사용못함)
            //out.println("location.href='./userLogin.usr';");
            
            out.println("</script>");
         }
         
         //prodcut detail 에서 넘어온 값들 을 form에 뿌려줄려함
         //request.getParameter("prod_id");
         //request.getParameter("prod_name");
         //request.getParameter("prod_price");
         //request.getParameter("o_amount");
         
      else {   
          
  		MemberViewService memberViewService = new MemberViewService();
  		MemberBean member = memberViewService.getMember(mem_id);
  		int mem_point=member.getMem_point();
        
    		request.setAttribute("mem_point", mem_point); 
         forward=new ActionForward("/order/order_form.jsp",false);
            }
      return forward;
     
}
}
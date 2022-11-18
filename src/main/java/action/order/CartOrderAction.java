package action.order;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.order.OrderInsertService;
import svc.user.UserViewService;
import vo.ActionForward;
import vo.Cart;
import vo.OrderBean;
import vo.OrderDetail;


public class CartOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
	

		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		int total=Integer.parseInt(request.getParameter("total")); //장바구니 총액
		int usePoint=Integer.parseInt(request.getParameter("point").trim());
		int addPoint=0;
		
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		/*주문번호 생성:  날짜 + 랜덤6자리수 조합*/
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		String ym=year+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String ymd=ym+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String subNum="";
		
		
		for(int i=1;i<=6;i++) {
			subNum+=(int)(Math.random()*10);
		}
		
		String order_id=ymd+"-"+subNum;
		
		
		

		if(cartList == null) {//cartList(장바구니 목록)이 없으면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('주문할 제품을 선택해주세요.')");//알림창 띄우고
			out.println("history.back()");//이전상태로
			out.println("</script>");
		}else {
			
			ArrayList<OrderDetail> orderdetailList=new ArrayList<OrderDetail>();
			
			//order_detail테이블
			for(int i=0;i<cartList.size();i++) {
				
				Cart cart=cartList.get(i);
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrder_id(order_id);
				orderDetail.setProd_id(cart.getProd_id());
				orderDetail.setProd_name(cart.getProd_name());
				orderDetail.setProd_price(cart.getPrice());
				orderDetail.setO_amount(cart.getQty());
				orderdetailList.add(orderDetail);
				
			}
				
				
		
			
			String o_name=request.getParameter("o_name");
			String o_phone =request.getParameter("o_phone");
			String o_email=request.getParameter("o_email");
			String o_address1=request.getParameter("o_address1");
			String o_address2=request.getParameter("o_address2");
			String o_address3=request.getParameter("o_address3");
			String o_require=request.getParameter("o_require");
			int totalPrice=Integer.parseInt(request.getParameter("total"));//장바구니 총액
			
			//order 테이블
			OrderBean orderBean=new OrderBean();
			orderBean.setMem_id(mem_id);
			orderBean.setOrder_id(order_id);
			orderBean.setO_name(o_name);
			orderBean.setO_email(o_email);
			orderBean.setO_phone(o_phone);
			orderBean.setO_address1(o_address1);
			orderBean.setO_address2(o_address2);
			orderBean.setO_address3(o_address3);
			orderBean.setO_require(o_require);
			orderBean.setTotalPrice(totalPrice);
			orderBean.setUsePoint(usePoint);
	
		
		
		OrderInsertService orderInsertService =new OrderInsertService();
		boolean isOrderSuccess = orderInsertService.registOrder(orderBean,orderdetailList);
		
		//주문하기가 이뤄지면 재고수량 감소
		boolean isUpdateSucccess=orderInsertService.updateQty(orderdetailList);
		
		/*사용포인트 차감*/
		if(usePoint!=0) {
		orderInsertService.usePoint(mem_id,usePoint);
		}
		
		/*구매에대한 적립*/
	   /*회원의 등급을 먼저 얻은 후 */
		UserViewService userViewService = new UserViewService();
		String mem_grade= userViewService.getUserInfo(mem_id).getMem_grade();
		
		int point_Rate=orderInsertService.getPointRate(mem_grade);
		System.out.println("포인트율:"+point_Rate);
		
		addPoint=(int)(totalPrice*point_Rate/100.0);
	
		orderInsertService.updatePoint(mem_id,addPoint);
		
		
		/*구매적립금에 대한 등급 조정*/
		orderInsertService.updateMemberGrade(mem_id);
		
		
		
		
			if(!isOrderSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주문실패')");
				out.println("history.back();");
				out.println("</script>");
				
				
				
			}

			if(!isUpdateSucccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('재고가 부족합니다. 판매자에게 문의해주세요')");
				out.println("history.back();");
				out.println("</script>");
				
				
				
			}
			
			
			
			else{
				//[구매하기]가 끝나면 session영역에 '장바구니 목록(cartList)'은 삭제해야 함
				session.removeAttribute("cartList");
				
			
					
				forward=new ActionForward("/order/orderComplete.jsp",false);//주문이 완료되었다는 뷰페이지
				
	
			}
		}	
		
		return forward;
		
		
		
		
		
		
		
		
	}

}

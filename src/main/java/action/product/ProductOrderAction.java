package action.product;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.order.OrderInsertService;
import svc.product.ProductOrderService;
import svc.user.UserViewService;
import vo.ActionForward;
import vo.OrderBean;
import vo.OrderDetail;

public class ProductOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice").trim());
	
		int usePoint=Integer.parseInt(request.getParameter("point").trim());
		int addPoint=0;
	    
	    Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		System.out.println("----------------------------");
		 
		
		//주문번호 생성:  날짜+ 랜덤6자리수
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		String ym=year+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String ymd=ym+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String subNum="";
		
		for(int i=1;i<=6;i++) {
			subNum+=(int)(Math.random()*10);
		}
		
		String order_id=ymd+"-"+subNum;
		
		String o_name=request.getParameter("o_name");
		String o_phone =request.getParameter("o_phone");
		String o_email=request.getParameter("o_email");
		String o_address1=request.getParameter("o_address1");
		String o_address2=request.getParameter("o_address2");
		String o_address3=request.getParameter("o_address3");
		String o_require=request.getParameter("o_require");
		
		
		int prod_id=Integer.parseInt(request.getParameter("prod_id").trim());
		int o_amount=Integer.parseInt(request.getParameter("o_amount").trim());	
		int prod_price=Integer.parseInt(request.getParameter("prod_price").trim());	
		String prod_name=request.getParameter("prod_name");
	
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
	
		//order detail 테이블
		ArrayList<OrderDetail> orderdetailList=new ArrayList<OrderDetail>();
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrder_id(order_id);
		orderDetail.setProd_id(prod_id);
		orderDetail.setProd_price(prod_price);
		orderDetail.setO_amount(o_amount);
		orderDetail.setProd_name(prod_name);
		orderdetailList.add(orderDetail);
		


		OrderInsertService orderInsertService =new OrderInsertService();
		boolean isOrderSuccess = orderInsertService.registOrder(orderBean,orderdetailList);
		
		//주문하기가 이뤄지면 재고수량 감소
				
		boolean isUpdateSucccess=orderInsertService.updateQty(orderdetailList);
	
		
		/*사용포인트 차감*/
		if(usePoint!=0) {
		orderInsertService.usePoint(mem_id,usePoint);
		}
		
		/*구매에대한 적립*/
		/*
		 * if(mem_grade.equals("0")) { addPoint=(int)(totalPrice*0.03); }else
		 * if(mem_grade.equals("1")) { addPoint=(int)(totalPrice*0.02); }
		 */
	   
		/*수정추가!!!!!!!!!!!!!!!
		 * 
		 */
		UserViewService userViewService = new UserViewService();
		String mem_grade= userViewService.getUserInfo(mem_id).getMem_grade();
		
		int point_Rate=orderInsertService.getPointRate(mem_grade);
		System.out.println("포인트율:"+point_Rate);
		
		addPoint=(int)(totalPrice*point_Rate/100.0);
		orderInsertService.updatePoint(mem_id,addPoint);
		
		/*구매금액에 대한 등급 조정*/
	
		orderInsertService.updateMemberGrade(mem_id);
		
		
		
		if(!isOrderSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문실패')");
			out.println("history.back();");
			out.println("</script>");
		
		
		

			if(!isUpdateSucccess){
				response.setContentType("text/html;charset=UTF-8");
				 out = response.getWriter();
				out.println("<script>");
				out.println("alert('재고가 부족합니다. 판매자에게 문의해주세요')");
				out.println("history.back();");
				out.println("</script>");
				
				
				
			}
			
		
		
		
		
		}
		
		
		
		
		else{
			
			
				forward=new ActionForward("/order/orderComplete.jsp",false);//주문이 완료되었다는 뷰페이지
			

		}
		
		
		return forward;
	}

}

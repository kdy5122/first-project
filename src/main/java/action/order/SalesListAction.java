package action.order;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.order.SalesListService;
import vo.ActionForward;

public class SalesListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;


		String month = request.getParameter("month");
		String year = request.getParameter("year");
		
		String ym=year+month;
	String ym2=year+"년"+month+"월";
		System.out.println(ym);
		/*
		 * int month=Integer.parseInt(request.getParameter("month")); int
		 * year=Integer.parseInt(request.getParameter("years"));
		 * 
		 * int day=1;
		 * 
		 * cal.set(year,month-1,1);
		 * 
		 * 
		 * int lastday = cal.getActualMaximum(Calendar.DATE);//해당월 마지막 날짜
		 * 
		 * 
		 * ArrayList<String> day_List= new ArrayList<String>(); for(int
		 * i=1;i<lastday;i++) { cal.add(Calendar.DAY_OF_MONTH, +1); format = new
		 * SimpleDateFormat(format.format(cal.getTime())); day_List.add(format);
		 * 
		 * } request.setAttribute("day_List", day_List);
		 */
		
		SalesListService salesListService =new SalesListService();
		 
		Map<String,String> daySlaesList=salesListService.getDaySaleList(ym);
		String monthSales=salesListService.getMonthSaleList(ym);
		

		request.setAttribute("daySlaesList", daySlaesList); //일별 매출조회
		request.setAttribute("monthSales", monthSales); //월별 매출'합계'
		request.setAttribute("ym2", ym2); //월별 매출'합계'
		
		
		

		forward = new ActionForward("/order/salesList.jsp", false);
		return forward;
	}

}

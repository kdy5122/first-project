package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import dao.OrderDAO;
import vo.ProductBean;

public class SalesListService {

	public  Map<String,String> getDaySaleList(String ym) {
		 Map<String,String> daySlaesList = null;
		
		/*공통부분*/
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		daySlaesList =orderDAO.selectDaySales(ym) ;
		close(con);
		return daySlaesList;
	}

	public String getMonthSaleList(String ym) {
		String monthSales=null;
		
		/*공통부분*/
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		monthSales=orderDAO.selectMonthSales(ym);
		close(con);
		
		return monthSales;
	}



	

}

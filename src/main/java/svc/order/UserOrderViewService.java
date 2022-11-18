package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;

import vo.OrderBean;

public class UserOrderViewService {

	public ArrayList<OrderBean> getUserOrderList(String mem_id, int page, int limit) {
		
	
		
			Connection con = getConnection();
	
			OrderDAO orderDAO = OrderDAO.getInstance();
	
			orderDAO.setConnection(con);
			
			
			ArrayList<OrderBean> userOrderList = orderDAO.selectUserOrderList(mem_id,page,limit);
			
		
			
			close(con);
			
		
		
		return userOrderList;
	}

	public int getUserOrderListCount(String mem_id) {
			int listCount = 0;
		
		/*공통부분*/
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		listCount = orderDAO.userorderListCount(mem_id);
		close(con);
		return listCount;
	}

}

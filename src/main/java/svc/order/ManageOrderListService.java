package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import dao.ProductDAO;
import vo.OrderBean;

public class ManageOrderListService {

	public ArrayList<OrderBean> getOrderList(int page,int limit) {

		
		Connection con = getConnection();

		OrderDAO orderDAO = OrderDAO.getInstance();

		orderDAO.setConnection(con);
		
		
		ArrayList<OrderBean> manageOrderList = orderDAO.selectManageOrderList(page,limit);
		
	
		
		close(con);
		
	
	
	return manageOrderList;
	}

	public int getOrderListCount() {
	int listCount = 0;
		
		/*공통부분*/
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		listCount = orderDAO.orderListCount();
		close(con);
		return listCount;
	}

	public int getDayTotal() {
		int dayTotal=0;
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		dayTotal = orderDAO.dayTotalPrice();
		close(con);
		return dayTotal;
	}

}

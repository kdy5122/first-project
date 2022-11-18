package svc.order;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrderDetail;

public class UserOrderDetailService {

	public ArrayList<OrderDetail> getOrderDetailList(String order_id) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<OrderDetail> orderDetailList = orderDAO.selectOrderDetailList(order_id);
		close(con);
		
		return orderDetailList;
	}

	public ArrayList<String> getOrderUserList(String order_id) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<String> orderUserInfo=orderDAO.getOrderUserInfo(order_id);
		close(con);
		return orderUserInfo;
	}

}

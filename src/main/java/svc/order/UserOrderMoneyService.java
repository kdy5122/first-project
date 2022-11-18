package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.OrderDAO;
import dao.ProductDAO;
import vo.OrderBean;

public class UserOrderMoneyService {

	public int getListCount() {

		int listCount = 0;

		/* 공통부분 */
		Connection con = getConnection();

		OrderDAO orderDAO = OrderDAO.getInstance();

		orderDAO.setConnection(con);

		listCount = orderDAO.userMoneyListCount();
		
		close(con);
		
		return listCount;
	}
	

	public List<Object>  getUserMoneyList(int page, int limit) {
		
		
		Connection con = getConnection();

		OrderDAO orderDAO = OrderDAO.getInstance();

		orderDAO.setConnection(con);

		List<Object>   userMoneyList=orderDAO.selectUserMoneyList(page,limit);
		
		close(con);
		
	
		return userMoneyList;
	}

}

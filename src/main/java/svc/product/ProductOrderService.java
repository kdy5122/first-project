package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.OrderBean;
import vo.OrderDetail;

public class ProductOrderService {


	public boolean registCartOrder(OrderBean orderBean, ArrayList<OrderDetail> orderdetailList) {

		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);
		
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int insertCartOrderCount = orderDAO.insertOrder(orderBean,orderdetailList);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isCartOrderSuccess = false;
		if(insertCartOrderCount > 0) {
			isCartOrderSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		

		
		
		// TODO 자동 생성된 메소드 스텁
		return isCartOrderSuccess;
	}

}

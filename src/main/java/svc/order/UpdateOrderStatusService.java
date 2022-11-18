package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.OrderDAO;

public class UpdateOrderStatusService {

	public boolean updateOrderStatus(String order_id, String o_status) {
		Connection con = getConnection();
		// 2.싱글톤 패턴:MenuDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int updateOrderStatusCount = orderDAO.updateOrderStatus(order_id, o_status);
	

		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		boolean isUpdateSuccess = false;
		if (updateOrderStatusCount > 0) {
			isUpdateSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제
		return isUpdateSuccess;

	}
	
}
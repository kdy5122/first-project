package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import dao.OrderDAO;
import dao.ProductDAO;


public class OrderDeleteService {

	public void updateProductQty(String order_id) {
		int insertOrderCount=0;
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		ProductDAO productDAO = ProductDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		productDAO.setConnection(con);
		
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		 insertOrderCount = productDAO.resetProductQty(order_id);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		if(insertOrderCount > 0) {
			
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		
	}

	//주문내역 삭제
	public boolean deleteOrder(String order_id) {
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		 int deleteOrderCount=orderDAO.deleteOrder(order_id); 
		boolean deleteSuccess=false;
		
		if( deleteOrderCount>0  ) {
			commit(con);
			deleteSuccess=true;
		}
		else {
			rollback(con);
		}
		close(con);
		return deleteSuccess;
	}

	public void updateMemberGrade(String mem_id) {
		Connection con =getConnection(); //2.싱글톤 패턴:MenuDAO객체 생성 
		  OrderDAO orderDAO =OrderDAO.getInstance(); //3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
	  orderDAO.setConnection(con);
	 // orderDAO.updateMemberGrade(mem_id2);
	  int isUpdateGradeSuccess=0;

	  
	  isUpdateGradeSuccess= orderDAO.updateMemberReGrade(mem_id);
	  if (isUpdateGradeSuccess > 0) {
			
			commit(con);
		} else {

			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제
	}

	

	public void returnPoint(String order_id, String mem_id) {
		Connection con =getConnection(); //2.싱글톤 패턴:MenuDAO객체 생성 
		  OrderDAO orderDAO =OrderDAO.getInstance(); //3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
	  orderDAO.setConnection(con);
	 // orderDAO.updateMemberGrade(mem_id2);
	  int isUpdateGradeSuccess=0;

	  
	  isUpdateGradeSuccess= orderDAO.ReturnMemberPoint(order_id,mem_id);
	  if (isUpdateGradeSuccess > 0) {
			
			commit(con);
		} else {

			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제
		
	}

}

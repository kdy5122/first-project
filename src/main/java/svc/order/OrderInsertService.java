package svc.order;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import vo.OrderBean;
import vo.OrderDetail;

public class OrderInsertService {


	public boolean registOrder(OrderBean orderBean, ArrayList<OrderDetail> orderdetailList) {

		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);
		
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int insertOrderCount = orderDAO.insertOrder(orderBean,orderdetailList);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isOrderSuccess = false;
		if(insertOrderCount > 0) {
			isOrderSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		
		return isOrderSuccess;
	}

	public boolean updateQty(ArrayList<OrderDetail> orderdetailList) {
		Connection con = getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int updateQtyCount=productDAO.updateProductQty(orderdetailList);
		
		boolean isUpdateSucccess = false;
		if(updateQtyCount > 0) {
			isUpdateSucccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제	
		
	
	
	return isUpdateSucccess;
	

}
	//사용포인트
	public void usePoint(String mem_id, int usePoint) {
		Connection con = getConnection();
		OrderDAO orderDAO=OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int updatePointCount=orderDAO.updateUsePoint(mem_id,usePoint);
		
		
		if(updatePointCount > 0) {
			
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제	
		
		
	}

	//포인트조정
	public void updatePoint(String mem_id, int addPoint) {
		Connection con = getConnection();
		OrderDAO orderDAO=OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int updatePointCount=orderDAO.updatePoint(mem_id,addPoint);
		
		
		if(updatePointCount > 0) {
			
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제	
		
		
	}

	//멤버조정
	public void updateMemberGrade(String mem_id) {
		 Connection con =getConnection(); //2.싱글톤 패턴:MenuDAO객체 생성 
		  OrderDAO orderDAO =OrderDAO.getInstance(); //3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
	  orderDAO.setConnection(con);
	 // orderDAO.updateMemberGrade(mem_id2);
	  int isUpdateGradeSuccess=0;

	  
	  isUpdateGradeSuccess= orderDAO.updateMemberGrade(mem_id);
	  if (isUpdateGradeSuccess > 0) {
			
			commit(con);
		} else {

			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제
		
	}
	//포인트율얻기
	public int getPointRate(String mem_grade) {
		int point_rate=0;
		 Connection con =getConnection(); //2.싱글톤 패턴:MenuDAO객체 생성 
		  OrderDAO orderDAO =OrderDAO.getInstance(); //3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
	  orderDAO.setConnection(con);
	 // orderDAO.updateMemberGrade(mem_id2);
	 

	  
	  point_rate= orderDAO.getPointRate(mem_grade);
	  if (point_rate > 0) {
			
			commit(con);
		} else {

			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제
		
		
		//포인트 율 얻기
		return point_rate;
		
	}


}
package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ProductDAO;
import vo.ProductBean;

public class ProductModifyProService {
	
	 public boolean updateProd(ProductBean porductBean) {
		 
			//1.커넥션 풀에서 Connection객체 얻어와
			Connection con = getConnection();
			//2.싱글톤 패턴:MenuDAO객체 생성
			ProductDAO productDAO = ProductDAO.getInstance();
			//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
			productDAO.setConnection(con);
			
			/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
			int updateProdCount = productDAO.updateproduct(porductBean);
			
			/*-(update,delete,insert)성공하면 commit 실패하면 rollback
			 * (select제외)----*/	
			boolean isUpdateProdSuccess = false;
			if(updateProdCount > 0) {
				isUpdateProdSuccess = true;
				commit(con);
			}else {
				rollback(con);
			}
			
			//4.해제
			close(con);//Connection객체 해제		
			
			return isUpdateProdSuccess;
		 
		 
		 
	 }

}

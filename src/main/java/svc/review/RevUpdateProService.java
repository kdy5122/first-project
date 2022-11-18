package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewBean;

public class RevUpdateProService {

	

	public  boolean updateReview(ReviewBean reviewBean) throws Exception {
		
		boolean isUpdateSuccess=false;
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int updateCount = reviewDAO.updateReview(reviewBean);
		if(updateCount > 0){
			commit(con);
			isUpdateSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
		
	
	}

}

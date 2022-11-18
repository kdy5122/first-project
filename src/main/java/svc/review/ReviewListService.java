package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.ReviewBean;

public class ReviewListService {

	public int getListCount() throws Exception {
		int listCount = 0;
		
		/*공통부분*/
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		listCount = reviewDAO.selectListCount();
		close(con);
		return listCount;
	}

	public ArrayList<ReviewBean> getListReview(int page, int limit) throws Exception{

		ArrayList<ReviewBean> listReview = null;
		
		/*공통부분*/
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		listReview = reviewDAO.selectList(page,limit);
		close(con);
		return listReview;
	}

	
}

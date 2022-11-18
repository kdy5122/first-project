package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;


import dao.ReviewDAO;

import vo.ReviewBean;

public class RevDetailViewService {

	public ReviewBean getArticle(int board_no) throws Exception { //게시글번호로 매개변수를 받아 해당 sql 문 조회하여 리뷰빈객체얻음

		ReviewBean article = null;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		/*
		 * int updateCount = reviewDAO.updateReadCount(board_no);
		 * 
		 * if(updateCount > 0){ commit(con); } else{ rollback(con); }
		 */
		
		article = reviewDAO.selectReview(board_no);
		close(con);

		

		return article;
	}

}

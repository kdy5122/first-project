package svc.tboard_svc;

import java.sql.Connection;

import dao.TBoardDAO;
import vo.TBoardBean;

import static db.JdbcUtil.*;

public class TBoardDetailService {

	public TBoardBean getArticle(int tboard_num) throws Exception{
		// TODO Auto-generated method stub
		
		TBoardBean article = null;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		int updateCount = tboardDAO.updateReadCount(tboard_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = tboardDAO.selectArticle(tboard_num);
		close(con);
		return article;
		
	}

}

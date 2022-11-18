package svc.tboard_svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.TBoardDAO;
import vo.TBoardBean;

public class TBoardReplyModfiyProService {
  
	
	public boolean modifyReplyArticle(TBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		int updateCount = tboardDAO.updateReplyArticle(article);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}
}

package svc.tboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.TBoardDAO;
import vo.TBoardBean;

public class TBoardReplyProService {

	public boolean replyArticle(TBoardBean article) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		insertCount = tboardDAO.insertReplyArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
		
	}

}

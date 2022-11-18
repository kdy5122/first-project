package svc.qboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.QBoardDAO;
import vo.QBoardBean;

public class QBoardReplyProService {

	public boolean replyArticle(QBoardBean article) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		boardDAO.setConnection(con);
		insertCount = boardDAO.insertReplyArticle(article);
		
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

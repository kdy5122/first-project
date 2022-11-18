package svc.qboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.QBoardDAO;
import vo.QBoardBean;

public class QBoardModifyProService {



	public boolean modifyArticle(QBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateArticle(article);
		
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

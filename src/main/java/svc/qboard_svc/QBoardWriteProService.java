package svc.qboard_svc;

import java.sql.Connection;

import dao.QBoardDAO;
import vo.QBoardBean;

import static db.JdbcUtil.*;
public class QBoardWriteProService {

	public boolean registArticle(QBoardBean boardBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertArticle(boardBean);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true; //1일시 ture
		}
		else{
			rollback(con);   //아닐시 rollback 
		}
		
		close(con);
		return isWriteSuccess;
		
	}

}

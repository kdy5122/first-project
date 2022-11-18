package svc.tboard_svc;

import java.sql.Connection;

import dao.TBoardDAO;
import vo.TBoardBean;

import static db.JdbcUtil.*;
public class TBoardWriteProService {

	public boolean registArticle(TBoardBean boardBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		int insertCount = tboardDAO.insertArticle(boardBean);
		
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

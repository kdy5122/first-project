package svc.tboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.TBoardDAO;
import vo.TBoardBean;

public class TBoardModifyProService {



	public boolean modifyArticle(TBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		int updateCount = tboardDAO.updateArticle(article);
		
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

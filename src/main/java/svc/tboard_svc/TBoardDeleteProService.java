package svc.tboard_svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;

import dao.TBoardDAO;

public class TBoardDeleteProService {



	public boolean removeArticle(int tboard_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		int deleteCount = tboardDAO.deleteArticle(tboard_num);
		
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}

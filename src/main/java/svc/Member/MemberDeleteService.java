package svc.Member;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class MemberDeleteService {

	public boolean deleteMember(String deleteId) {
		boolean deleteResult = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
	     int deleteCount=memberDAO.deleteMeber(deleteId);
	     int deleteOrderD=memberDAO.deleteOrderDetail(deleteId); 
		 int deleteOrderI=memberDAO.deleteOrderItem(deleteId); 
		
		if( deleteCount>0 ) {
			commit(con);
			deleteResult=true;
		}
		else {
			rollback(con);
		}
		close(con);
		return false;
	}
}

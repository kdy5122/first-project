package svc.user;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class UserDeleteService {
	//멤버변수
	//생성자
	//메서드
	
	//회원탈퇴 :파라미터로 전송된 id로 member에서 해당회원을 찾아서 삭제
	public boolean deleteUser(String deleteId){
		boolean deleteResult = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int deleteCount=memberDAO.deleteMeber(deleteId);
		if(deleteCount>0) {
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

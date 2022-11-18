package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LoginDAO;

import vo.MemberBean;
public class LoginService {

	public MemberBean getLoginMember(String mem_id, String mem_pwd) {
		// TODO Auto-generated method stub
		LoginDAO loginDAO = LoginDAO.getInstance();
		Connection con = getConnection();
		loginDAO.setConnection(con);
		MemberBean loginMember = loginDAO.selectLoginMember(mem_id,mem_pwd);
		
		System.out.println("디버그test2");
		close(con);
		return loginMember;
		
		
	}
	
	
	
	
	
}









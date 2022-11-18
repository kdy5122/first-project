package svc.user;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class UserViewService {
	//멤버변수
	//생성자
	//메서드
	
	public MemberBean getUserInfo(String viewId){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		memberDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		MemberBean userInfo = memberDAO.selectUserInfo(viewId);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return userInfo;
	}

	//회원 수정 후 이름이 안바뀌는 문제로 추가함---------------------------------------------
	public MemberBean getMemberName(String mem_id) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		memberDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		//String userName = userDAO.selectUserName(u_id);
		MemberBean userInfo = memberDAO.selectUserInfo(mem_id);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return userInfo;
	}
	
}

package svc.Member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberPwFindService {
	//멤버변수
	//기본생성자
	//메서드
	
	public MemberBean setHashPw(String mem_id, String mem_email){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		memberDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		MemberBean memberInfo = memberDAO.findHashPw(mem_id, mem_email);		
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return memberInfo;//'조회한 아이디와 이메일,이름'값을 셋팅한 UserBean객체를 리턴
	}
	
	public MemberBean findHashPw(String mem_id, String mem_email){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		memberDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/				
		//암호화 O - 임시비번을 메일로 전송하기 위해 아이디와 이메일로 해당 사용자 찾아서 '조회한 아이디와 이메일,이름'값을 셋팅한 UserBean객체를 리턴받아
		MemberBean memberInfo = memberDAO.findHashPw(mem_id, mem_email);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return memberInfo;//'조회한 아이디와 이메일,이름'값을 셋팅한 UserBean객체를 리턴
	}
	
	public boolean setHashPw(String mem_id, String mem_email, String ramdom_password) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		memberDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/				
		//암호화 O - 임시비번을 메일로 전송하기 위해 아이디와 이메일로 해당 사용자 찾아서 '조회한 아이디와 이메일,이름'값을 셋팅한 UserBean객체를 리턴받아
		int setHashPwCount = memberDAO.setHashPw(mem_id, mem_email, ramdom_password);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isSetHashPwResult = false;
		if(setHashPwCount>0 ) {
			isSetHashPwResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		//4.해제
		close(con);//Connection객체 해제		
		
		return isSetHashPwResult;
	}
}

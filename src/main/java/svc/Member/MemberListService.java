package svc.Member;

import java.sql.Connection;
import java.util.ArrayList;
import dao.MemberDAO;
import dao.ReviewDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class MemberListService {
	
	public int getListCount() throws Exception{
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectListCount();
		close(con);
		return listCount;
	}

	public static ArrayList<MemberBean> getMemberList(int page, int limit) throws Exception {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		ArrayList<MemberBean> memberList = memberDAO.selectMemberList(page,limit);
		close(con);
		return memberList;
	}

	

}

package svc.tboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.TBoardDAO;
import vo.TBoardBean;

public class TBoardListService {

	public int getListCount() throws Exception{ //총 글의 개수 구하는 메소드
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		listCount = tboardDAO.selectListCount();  //selectListCount()로 총글의 개수를 listCount에 담는다.
		close(con);
		return listCount;
		
	}

	public ArrayList<TBoardBean> getArticleList(int page, int limit) throws Exception{ //글의 목록 불러오는 메소드
		
		ArrayList<TBoardBean> articleList = null;
		Connection con = getConnection();
		TBoardDAO tboardDAO = TBoardDAO.getInstance();
		tboardDAO.setConnection(con);
		articleList = tboardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}

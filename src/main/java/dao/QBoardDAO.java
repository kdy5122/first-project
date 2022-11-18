package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import vo.QBoardBean;

public class QBoardDAO {

	DataSource ds;
	Connection con;
	private static QBoardDAO boardDAO;

	private QBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static QBoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new QBoardDAO(); //기본 생성자로 객체를 생성하여 boardDAO를 참조 
		}
		return boardDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	//글의 개수 구하기. ★★★★★★
	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from qboard");
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;

	}

	//글 목록 보기.	★★★★★★
	public ArrayList<QBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from qboard order by QBOARD_RE_REF desc,QBOARD_RE_SEQ asc limit ?,10";
		ArrayList<QBoardBean> articleList = new ArrayList<QBoardBean>();
		QBoardBean board = null;
		int startrow=(page-1)*10; //읽기 시작할 row 번호..	

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new QBoardBean();
				board.setMEM_ID(rs.getString("MEM_ID"));
				board.setQBOARD_NUM(rs.getInt("qboard_num"));
				board.setQBOARD_SUBJECT(rs.getString("QBOARD_SUBJECT"));
				board.setQBOARD_CONTENT(rs.getString("QBOARD_CONTENT"));
				board.setQBOARD_RE_REF(rs.getInt("QBOARD_RE_REF"));
				board.setQBOARD_RE_LEV(rs.getInt("QBOARD_RE_LEV"));
				board.setQBOARD_RE_SEQ(rs.getInt("QBOARD_RE_SEQ"));
				board.setQBOARD_READCOUNT(rs.getInt("QBOARD_READCOUNT"));
				board.setQBOARD_DATE(rs.getDate("QBOARD_DATE"));
				articleList.add(board);
			}

		}catch(Exception ex){
			System.out.println("getBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글 내용 보기.
	public QBoardBean selectArticle(int qboard_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QBoardBean boardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from qboard where qboard_num = ?");
			pstmt.setInt(1, qboard_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				boardBean = new QBoardBean();
				boardBean.setMEM_ID(rs.getString("MEM_ID"));
				boardBean.setQBOARD_NUM(rs.getInt("qboard_num"));
				boardBean.setQBOARD_SUBJECT(rs.getString("QBOARD_SUBJECT"));
				boardBean.setQBOARD_CONTENT(rs.getString("QBOARD_CONTENT"));
				boardBean.setQBOARD_RE_REF(rs.getInt("QBOARD_RE_REF"));
				boardBean.setQBOARD_RE_LEV(rs.getInt("QBOARD_RE_LEV"));
				boardBean.setQBOARD_RE_SEQ(rs.getInt("QBOARD_RE_SEQ"));
				boardBean.setQBOARD_READCOUNT(rs.getInt("QBOARD_READCOUNT"));
				boardBean.setQBOARD_DATE(rs.getDate("QBOARD_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return boardBean;

	}

	//글 등록.
	public int insertArticle(QBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(qboard_num) from qboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;  //인설트 할때마다 보드 넘버 1증가
			else
				num=1;

			sql="insert into qboard (qboard_num,MEM_ID,QBOARD_SUBJECT,";
			sql+= " QBOARD_CONTENT, QBOARD_RE_REF,"+
					"QBOARD_RE_LEV,QBOARD_RE_SEQ,QBOARD_READCOUNT,"+
					"QBOARD_DATE) values(?,?,?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMEM_ID());
			pstmt.setString(3, article.getQBOARD_SUBJECT());
			pstmt.setString(4, article.getQBOARD_CONTENT());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			

			
			insertCount=pstmt.executeUpdate(); //성공시 1 실패시 0

		}catch(Exception e){
			System.out.println("qboardInsert 에러 : "+e);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 답변.
	public int insertReplyArticle(QBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql="select max(qboard_num) from qboard";
		String sql="";
		int num=0;
		int insertCount=0;
		int re_ref=article.getQBOARD_RE_REF();
		int re_lev=article.getQBOARD_RE_LEV();
		int re_seq=article.getQBOARD_RE_SEQ();

		try{
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			sql="update qboard set QBOARD_RE_SEQ=QBOARD_RE_SEQ+1 where QBOARD_RE_REF=? ";
			sql+="and QBOARD_RE_SEQ>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			int updateCount=pstmt.executeUpdate();

			if(updateCount > 0){
				commit(con);
			}

			re_seq = re_seq + 1;
			re_lev = re_lev+1;
			sql="insert into qboard (qboard_num,MEM_ID,QBOARD_SUBJECT,";
			sql+="QBOARD_CONTENT,QBOARD_RE_REF,QBOARD_RE_LEV,QBOARD_RE_SEQ,";
			sql+="QBOARD_READCOUNT,QBOARD_DATE) values(?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMEM_ID());
			pstmt.setString(3, article.getQBOARD_SUBJECT());
			pstmt.setString(4, article.getQBOARD_CONTENT());
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_lev);
			pstmt.setInt(7, re_seq);
			pstmt.setInt(8, 0);
			insertCount = pstmt.executeUpdate();
			commit(con);
		}catch(SQLException ex){
			System.out.println("qboardReply 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 수정.
	public int updateArticle(QBoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update qboard set QBOARD_SUBJECT=?,QBOARD_CONTENT=? where qboard_num=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getQBOARD_SUBJECT());
			pstmt.setString(2, article.getQBOARD_CONTENT());
			pstmt.setInt(3, article.getQBOARD_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("qboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int qboard_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from qboard where qboard_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, qboard_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("qboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트.
	public int updateReadCount(int qboard_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update qboard set QBOARD_READCOUNT = "+
				"QBOARD_READCOUNT+1 where qboard_num = "+qboard_num;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("setReadCountUpdate 에러 : "+e);
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	


}

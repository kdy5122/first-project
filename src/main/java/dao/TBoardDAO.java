package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import vo.QBoardBean;
import vo.TBoardBean;

public class TBoardDAO {

	DataSource ds;
	Connection con;
	private static TBoardDAO tboardDAO;

	private TBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static TBoardDAO getInstance(){
		if(tboardDAO == null){
			tboardDAO = new TBoardDAO(); //기본 생성자로 객체를 생성하여 tboardDAO를 참조 
		}
		return tboardDAO;
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
			pstmt=con.prepareStatement("select count(*) from tboard");
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
	public ArrayList<TBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String tboard_list_sql="select * from tboard order by TBOARD_RE_REF desc,TBOARD_RE_SEQ asc limit ?,10";
		ArrayList<TBoardBean> articleList = new ArrayList<TBoardBean>();
		TBoardBean tboard = null;
		int startrow=(page-1)*10; //읽기 시작할 row 번호..	

		try{
			pstmt = con.prepareStatement(tboard_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				tboard = new TBoardBean();
				tboard.setMEM_ID(rs.getString("MEM_ID"));
				tboard.setTBOARD_NUM(rs.getInt("TBOARD_NUM"));
				tboard.setTBOARD_SUBJECT(rs.getString("TBOARD_SUBJECT"));
				tboard.setTBOARD_CONTENT(rs.getString("TBOARD_CONTENT"));
				tboard.setTBOARD_FILE(rs.getString("TBOARD_FILE"));
				tboard.setTBOARD_RE_REF(rs.getInt("TBOARD_RE_REF"));
				tboard.setTBOARD_RE_LEV(rs.getInt("TBOARD_RE_LEV"));
				tboard.setTBOARD_RE_SEQ(rs.getInt("TBOARD_RE_SEQ"));
				tboard.setTBOARD_READCOUNT(rs.getInt("TBOARD_READCOUNT"));
				tboard.setTBOARD_DATE(rs.getDate("TBOARD_DATE"));
				articleList.add(tboard);
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
	public TBoardBean selectArticle(int tboard_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TBoardBean tboardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from tboard where TBOARD_NUM = ?");
			pstmt.setInt(1, tboard_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				tboardBean = new TBoardBean();
				tboardBean.setMEM_ID(rs.getString("MEM_ID"));
				tboardBean.setTBOARD_NUM(rs.getInt("TBOARD_NUM"));
				tboardBean.setTBOARD_SUBJECT(rs.getString("TBOARD_SUBJECT"));
				tboardBean.setTBOARD_CONTENT(rs.getString("TBOARD_CONTENT"));
				tboardBean.setTBOARD_FILE(rs.getString("TBOARD_FILE"));
				tboardBean.setTBOARD_IMAGE(rs.getString("TBOARD_IMAGE"));
				tboardBean.setTBOARD_RE_REF(rs.getInt("TBOARD_RE_REF"));
				tboardBean.setTBOARD_RE_LEV(rs.getInt("TBOARD_RE_LEV"));
				tboardBean.setTBOARD_RE_SEQ(rs.getInt("TBOARD_RE_SEQ"));
				tboardBean.setTBOARD_READCOUNT(rs.getInt("TBOARD_READCOUNT"));
				tboardBean.setTBOARD_DATE(rs.getDate("TBOARD_DATE"));
				tboardBean.setTBOARD_PLACELA(rs.getString("TBOARD_PLACELA"));
				tboardBean.setTBOARD_PLACEMA(rs.getString("TBOARD_PLACEMA"));
				tboardBean.setTBOARD_ADDRESS(rs.getString("TBOARD_ADDRESS"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return tboardBean;

	}

	//글 등록.
	public int insertArticle(TBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(tboard_num) from tboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;  //인설트 할때마다 보드 넘버 1증가
			else
				num=1;

			sql="insert into tboard (TBOARD_NUM,MEM_ID,TBOARD_SUBJECT,";
			sql+= " TBOARD_CONTENT, TBOARD_FILE,TBOARD_IMAGE,TBOARD_RE_REF,"+
					"TBOARD_RE_LEV,TBOARD_RE_SEQ,TBOARD_READCOUNT,"+
					"TBOARD_DATE,TBOARD_PLACELA,TBOARD_PLACEMA,TBOARD_ADDRESS) values(?,?,?,?,?,?,?,?,?,?,now(),?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMEM_ID());
			pstmt.setString(3, article.getTBOARD_SUBJECT());
			pstmt.setString(4, article.getTBOARD_CONTENT());
			pstmt.setString(5, article.getTBOARD_FILE());
			pstmt.setString(6, article.getTBOARD_IMAGE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			//★★★★★★★★★★★★★★★★★★★★★★★★★ mySQL은 빈값을 NULL이아닌 공백으로 인식하기때문에 조건문을 걸어준다. ★★★★★★★★★★★★★★★★★★★★★★
			// 받아온 경도값이 있을경우 그값을 인설트시켜주고 
			if(!article.getTBOARD_PLACELA().equals("")) {
			pstmt.setString(11, article.getTBOARD_PLACELA());
			// 받아온 경도값이 없을 경우 mySQL에 null을 insert시켜준다. 
			}else if(article.getTBOARD_PLACELA().equals("")) { 
			pstmt.setString(11, "NULL");	
			}			
			
			if(!article.getTBOARD_PLACEMA().equals("")) {
			pstmt.setString(12, article.getTBOARD_PLACEMA());
			}else if(article.getTBOARD_PLACEMA().equals("")) {
			pstmt.setString(12, "NULL");	
			}
			
			pstmt.setString(13, article.getTBOARD_ADDRESS());
			insertCount=pstmt.executeUpdate(); //성공시 1 실패시 0

		}catch(Exception e){
			System.out.println("tboardInsert 에러 : "+e);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 답변.
	public int insertReplyArticle(TBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String tboard_max_sql="select max(tboard_num) from tboard";
		String sql="";
		int num=0;
		int insertCount=0;
		int re_ref=article.getTBOARD_RE_REF();
		int re_lev=article.getTBOARD_RE_LEV();
		int re_seq=article.getTBOARD_RE_SEQ();

		try{
			pstmt=con.prepareStatement(tboard_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			sql="update tboard set TBOARD_RE_SEQ=TBOARD_RE_SEQ+1 where TBOARD_RE_REF=? ";
			sql+="and TBOARD_RE_SEQ>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			int updateCount=pstmt.executeUpdate();

			if(updateCount > 0){
				commit(con);
			}

			re_seq = re_seq + 1;
			re_lev = re_lev+1;
			sql="insert into tboard (TBOARD_NUM,MEM_ID,TBOARD_SUBJECT,";
			sql+="TBOARD_CONTENT,TBOARD_RE_REF,TBOARD_RE_LEV,TBOARD_RE_SEQ,";
			sql+="TBOARD_READCOUNT,TBOARD_DATE,TBOARD_PLACELA,TBOARD_PLACEMA) values(?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMEM_ID());
			pstmt.setString(3, article.getTBOARD_SUBJECT());
			pstmt.setString(4, article.getTBOARD_CONTENT());
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_lev);
			pstmt.setInt(7, re_seq);
			pstmt.setInt(8, 0);
			pstmt.setString(9, "NULL");
			pstmt.setString(10, "NULL");
			insertCount = pstmt.executeUpdate();
			commit(con);
		}catch(SQLException ex){
			System.out.println("tboardReply 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 수정.
	public int updateArticle(TBoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update tboard set TBOARD_SUBJECT=?,TBOARD_CONTENT=?,TBOARD_FILE=?,TBOARD_IMAGE=?,TBOARD_PLACELA=?,TBOARD_PLACEMA=? where TBOARD_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTBOARD_SUBJECT());
			pstmt.setString(2, article.getTBOARD_CONTENT());
			pstmt.setString(3, article.getTBOARD_FILE());
			pstmt.setString(4, article.getTBOARD_IMAGE());
			
			if(!article.getTBOARD_PLACELA().equals("")) {
				pstmt.setString(5, article.getTBOARD_PLACELA());
				}else if(article.getTBOARD_PLACELA().equals("")) {
				pstmt.setString(5, "NULL");	
				}
				
				if(!article.getTBOARD_PLACEMA().equals("")) {
				pstmt.setString(6, article.getTBOARD_PLACEMA());
				}else if(article.getTBOARD_PLACEMA().equals("")) {
				pstmt.setString(6, "NULL");	
				}
			
			
			pstmt.setInt(7, article.getTBOARD_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("tboardModify 에러 : " + e);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}
    
	//답글 수정
	public int updateReplyArticle(TBoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update tboard set TBOARD_SUBJECT=?,TBOARD_CONTENT=? where tboard_num=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTBOARD_SUBJECT());
			pstmt.setString(2, article.getTBOARD_CONTENT());
			pstmt.setInt(3, article.getTBOARD_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("tboardReplyModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}
	//글 삭제.
	public int deleteArticle(int tboard_num){

		PreparedStatement pstmt = null;
		String tboard_delete_sql="delete from tboard where TBOARD_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(tboard_delete_sql);
			pstmt.setInt(1, tboard_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("tboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}
	
	

	//조회수 업데이트.
	public int updateReadCount(int tboard_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update tboard set TBOARD_READCOUNT = "+
				"TBOARD_READCOUNT+1 where TBOARD_NUM = "+tboard_num;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate 에러 : "+ex);
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	


}

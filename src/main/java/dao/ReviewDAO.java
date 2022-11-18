package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import vo.ReviewBean;


public class ReviewDAO {

	private Connection con;
	
	private  PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	private static ReviewDAO reviewDAO;
	
	private ReviewDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ReviewDAO getInstance(){
		if(reviewDAO == null){
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	
	
	//게시판 전체 글 개수
  public int selectListCount(){
	  int listCount = 0;
	  
	  try {
		  String sql="select count(*) from review";
		  pstmt=con.prepareStatement(sql);
		  rs=pstmt.executeQuery();
		  
		  if(rs.next()) {
			  listCount=rs.getInt(1);
		  }
	  }catch(Exception e) {
		  System.out.println("[ReviewDAO] getListCount 에러 :" +e);
	  }finally{
			close(rs);
			close(pstmt);
		}
	  return listCount;
  }
	
	
	//후기게시판 글목록가져오기 
  
	public ArrayList<ReviewBean> selectList(int page,int limit){
	
		ArrayList<ReviewBean>  listReview=new ArrayList<ReviewBean>();
		//try catch 문에 안들어가있음..
	 	String sql="select * from review order by board_no desc limit ?,8"; //1행부터 시작하고싶으면 0,6   3행부터싲가하고싶으면 4,6
	 			int startrow=(page-1)*8; //읽기시작할 row번호//mysql은 0부터 시작이라 page-1할 필요없음
	
	 			
	 	try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewBean reviewBean=new ReviewBean();
				
				reviewBean.setBoard_no(rs.getInt("board_no"));
				reviewBean.setProd_id(rs.getInt("prod_id"));
				reviewBean.setMem_id(rs.getString("mem_id"));
				reviewBean.setRev_content(rs.getString("rev_content"));
				reviewBean.setRev_score(rs.getString("rev_score"));
				reviewBean.setRev_date(rs.getString("rev_date"));
				reviewBean.setRev_fileName(rs.getString("rev_fileName"));
				reviewBean.setRev_origfileName(rs.getString("rev_origfileName"));
				
				
				listReview.add(reviewBean);
				
				}
			 }catch(Exception e) {
				  System.out.println("[ReviewDAO] selectList 에러 :" +e);
			  }finally{
					close(rs);
					close(pstmt);
				}
			 
			
		 return listReview;
	}

	//후기게시판 글 INSERT
	public  int insertReview(ReviewBean reviewBean) {
		String sql="";
		int insertCount=0;
		int idx=0;
		String prod_id="1004";//테스틀위한 고정 값
		//추후에 추가할예정
		/*String prod_id=0; (order테이블에서 가져올 )
		 * 
		 * 
		 * 
		 * */
		sql="insert into review(prod_id,mem_id,rev_content,rev_score,rev_date,rev_fileName,rev_origfileName)";
		sql+=" values(?,?,?,?,now(),?,?)";
		
		
		try {
	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,reviewBean.getProd_id());
			pstmt.setString(2, reviewBean.getMem_id());
			pstmt.setString(3, reviewBean.getRev_content());
			pstmt.setString(4, reviewBean.getRev_score());
			pstmt.setString(5, reviewBean.getRev_fileName());
			pstmt.setString(6, reviewBean.getRev_origfileName());
		
			
			insertCount = pstmt.executeUpdate();//성공시 1
			
			
		} catch (Exception e) {
			System.out.println("insertReview() 에러" + e); 
		}finally {
			close(pstmt);
		}
	
		
		
		return insertCount;
	}

	public ReviewBean selectReview(int board_no) {//게시글 내용조회
		ReviewBean reviewBean = null;
		String sql="select * from review where board_no = ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("selectReview 성공");
				reviewBean = new ReviewBean();
				reviewBean.setBoard_no(rs.getInt("board_no"));
				reviewBean.setProd_id(rs.getInt("prod_id"));
				reviewBean.setMem_id(rs.getString("mem_id"));
				reviewBean.setRev_content(rs.getString("rev_content"));
				reviewBean.setRev_score(rs.getString("rev_score"));
				reviewBean.setRev_date(rs.getString("rev_date"));
				reviewBean.setRev_fileName(rs.getString("rev_fileName"));
				reviewBean.setRev_origfileName(rs.getString("rev_origfileName"));
				
			}
		}catch(Exception e){
			System.out.println("selectReview() 에러 : " + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		
		
		
		return reviewBean;
	}


	public int updateReview(ReviewBean reviewBean) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		int idx=0;
		String sql="update review set rev_content=?,rev_score=?,rev_date=now()";
		
		if(reviewBean.getRev_fileName()!=null) { 
		
			sql+= ", rev_fileName=? ";}
		
		sql+=" where board_no=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(++idx, reviewBean.getRev_content());
			pstmt.setString(++idx, reviewBean.getRev_score());
			
			if(reviewBean.getRev_fileName()!=null) {
				pstmt.setString(++idx, reviewBean.getRev_fileName());
			}	
			
			
			pstmt.setInt(++idx, reviewBean.getBoard_no());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("updateReview 오류 : " + e);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}


	public boolean isArticleBoardWriter(int board_no, String mem_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from review where board_no=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			rs.next();

			if(mem_id.equals(rs.getString("mem_id"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter 오류 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;
	}

	public int deleteArticle(int board_no) {

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from review where board_no=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, board_no);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("boardDelete 오류 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	public int selectInsertboard() {
		int board_no=0;
		String sql="select * from review order by board_no desc limit 0,1"; //1행부터 시작하고싶으면 0,6   3행부터싲가하고싶으면 4,6
			
			
	try {
	
	pstmt = con.prepareStatement(sql);
	
	rs = pstmt.executeQuery();
	
	if(rs.next()) {
		board_no=rs.getInt("board_no");
		}
	 }catch(Exception e) {
		  System.out.println("[ReviewDAO] selectList 에러 :" +e);
	  }finally{
			close(rs);
			close(pstmt);
		}
	 
	
		return board_no;
	}
	
	
	
	
	
}

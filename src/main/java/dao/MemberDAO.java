package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

import util.SHA256;
import vo.MemberBean;
import static db.JdbcUtil.*;

public class MemberDAO {
	public static MemberDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	private MemberDAO() {}
	

	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
		
	}
	public int insertMember(MemberBean member) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,default,default)";
		int insertCount=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pwd());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_email());
			pstmt.setString(5, member.getMem_call());
			pstmt.setString(6, member.getMem_postcode());
			pstmt.setString(7, member.getMem_address1());
			pstmt.setString(8, member.getMem_address2());
			pstmt.setString(9, member.getMem_address3());
			
			
			insertCount=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember 에러:"+e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	
	public ArrayList<MemberBean> selectMemberList(int page, int limit) {
		String sql = "SELECT * FROM member order by mem_id asc limit ?,10";
		int startrow=(page-1)*10;
		ArrayList<MemberBean>memberList=new ArrayList<MemberBean>();
		MemberBean mb = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mb=new MemberBean();
					mb.setMem_id(rs.getString("mem_id"));
					mb.setMem_pwd(rs.getString("mem_pwd"));
					mb.setMem_name(rs.getString("mem_name"));
					mb.setMem_email(rs.getString("mem_email"));
					mb.setMem_call(rs.getString("mem_call"));
					mb.setMem_postcode(rs.getString("mem_postcode"));
					mb.setMem_address1(rs.getString("mem_address1"));
					mb.setMem_address2(rs.getString("mem_address2"));
					mb.setMem_address2(rs.getString("mem_address3"));
					mb.setMem_grade(rs.getString("mem_grade"));
					memberList.add(mb);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getDeatiMember 에러: "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}

	public MemberBean selectMemberList(String id) {
		String sql = "SELECT * FROM member WHERE mem_id=?";
		MemberBean mb =null;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mb=new MemberBean();
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_pwd(rs.getString("mem_pwd"));
				mb.setMem_name(rs.getString("mem_name"));
				mb.setMem_email(rs.getString("mem_email"));
				mb.setMem_call(rs.getString("mem_call"));
				mb.setMem_postcode(rs.getString("mem_postcode"));
				mb.setMem_address1(rs.getString("mem_address1"));
				mb.setMem_address2(rs.getString("mem_address2"));
				mb.setMem_address3(rs.getString("mem_address3"));
				mb.setMem_grade(rs.getString("mem_grade"));
				mb.setMem_point(rs.getInt("mem_point"));
			}
		} catch (Exception e) {
			System.out.println("getDeatilMember 에러 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return mb;
	}

	public int deleteMeber(String id) {
		String sql="DELETE FROM member WHERE mem_id =?";
		int deleteCount = 0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteMember 에러 : " + e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	public int deleteOrderItem(String id) {
		String sql="delete from order_item where mem_id =?";
  
		int deleteCount = 0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteOrderItem 에러 : " + e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	public int deleteOrderDetail(String id) {
		String sql="delete from order_detail";
		       sql+= " where order_id in(select order_id";
		       sql+= " from order_item where mem_id=?)";
  
		int deleteCount = 0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteOrderDetail 에러 : " + e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	public MemberBean findId(String mem_email) {
		MemberBean memberInfo = null;
		
		String sql="select * from member where mem_email=?";
		
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, mem_email);	
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				memberInfo = new MemberBean();

				memberInfo.setMem_id(rs.getString("mem_id"));
			}
			
		} catch (Exception e) {			
			System.out.println("[MemberDAO] findId 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		return memberInfo;
	}
	
	public MemberBean findHashPw(String mem_id, String mem_email) {
		MemberBean memberInfo = null;
		
		String sql="select * from member where mem_id=? AND mem_email=?";
		
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, mem_id);	
			pstmt.setString(2, mem_email);	
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				memberInfo = new MemberBean();//기본값으로 채워진 UserBean객체에
				//조회한 pw값을 셋팅
				memberInfo.setMem_pwd(rs.getString("mem_pwd"));
				
			}
			
		} catch (Exception e) {			
			System.out.println("[UserDAO] findPw 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		return memberInfo;
	}
	
	public int updateMember(MemberBean member) {
		int updateMemberCount = 0;
		
		String sql="update member SET  mem_name=?, mem_email=?, mem_call=?, mem_postcode=?, mem_address1=?, mem_address2=?, mem_address3=?, mem_grade=? where mem_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getMem_name());
			pstmt.setString(2, member.getMem_email());
			pstmt.setString(3, member.getMem_call());
			pstmt.setString(4, member.getMem_postcode());
			pstmt.setString(5, member.getMem_address1());
			pstmt.setString(6, member.getMem_address2());
			pstmt.setString(7, member.getMem_address3());
			pstmt.setString(8, member.getMem_grade());
			pstmt.setString(9, member.getMem_id());
	
			
			updateMemberCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[MemberDAO] updateMember 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}	
		
		return updateMemberCount;
	}

	public MemberBean selectUserInfo(String mem_id) {
		MemberBean userInfo = null;
		//사용자가 입력한 id 회원정보를 조회		
		String sql="select * from member where mem_id=?";		

		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, mem_id);		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//해당 id에 대한 정보가 있으면
				//기본값으로 채워진 UserBean객체에 조회한 회원정보값으로 셋팅
				userInfo = new MemberBean();
				
				userInfo.setMem_id(rs.getString("mem_id"));
				userInfo.setMem_pwd(rs.getString("mem_pwd"));
				userInfo.setMem_name(rs.getString("mem_name"));
				userInfo.setMem_email(rs.getString("mem_email"));
				userInfo.setMem_call(rs.getString("mem_call"));
				userInfo.setMem_postcode(rs.getString("mem_postcode"));
				userInfo.setMem_address1(rs.getString("mem_address1"));
				userInfo.setMem_address2(rs.getString("mem_address2"));
				userInfo.setMem_address3(rs.getString("mem_address3"));
				userInfo.setMem_grade(rs.getString("mem_grade"));
				userInfo.setMem_point(rs.getInt("mem_point"));
				
				
			}
		} catch (Exception e) {			
			System.out.println("[MemberDAO] selectUserInfo 에러:"+ e);//e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}			
		
		return userInfo;
	}
	
	public int deleteUser(String id) {
		int deleteUserCount = 0;
		
		String sql="delete from member WHERE mem_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, id);	
			
			deleteUserCount = pstmt.executeUpdate();//업데이트가 성공하면 1을 리턴	
			System.out.println("deleteUserCount:"+deleteUserCount);
		} catch (Exception e) {			
			System.out.println("[MemberDAO] deleteUser 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}	
		return deleteUserCount;
	}
	
	public int updateUser(MemberBean member) {
		int updateUserCount = 0;
		
		String sql="update member SET mem_pwd=?, mem_name=?, mem_email=?, mem_call=?, mem_postcode=?, mem_address1=?, mem_address2=?, mem_address3=? where mem_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, member.getMem_pwd());
			pstmt.setString(2, member.getMem_name());
			pstmt.setString(3, member.getMem_email());
			pstmt.setString(4, member.getMem_call());
			pstmt.setString(5, member.getMem_postcode());
			pstmt.setString(6, member.getMem_address1());
			pstmt.setString(7, member.getMem_address2());
			pstmt.setString(8, member.getMem_address3());
			pstmt.setString(9, member.getMem_id());	
			
			updateUserCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[MemberDAO] updateUser 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}	
		
		return updateUserCount;
	}

	public int selectListCount(){
		  int listCount = 0;
		  
		  try {
			  String sql="select count(*) from member";
			  pstmt=con.prepareStatement(sql);
			  rs=pstmt.executeQuery();
			  
			  if(rs.next()) {
				  listCount=rs.getInt(1);
			  }
		  }catch(Exception e) {
			  System.out.println("[MemberDAO] getListCount 에러 :" +e);
		  }finally{
				close(rs);
				close(pstmt);
			}
		  return listCount;
	  }

	public int setHashPw(String mem_id, String mem_email, String ramdom_password) {
int setHashPwCount = 0;
		
		String sql="update member set mem_pwd=? where mem_id=? and mem_email=?";
		
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, SHA256.encodeSHA256(ramdom_password));	
			//pstmt.setString(1, ramdom_password);//------------->발급받은 랜덤비번 입력하면 문제발생으로 수정해봄
			pstmt.setString(2, mem_id);
			pstmt.setString(3, mem_email);
		
			setHashPwCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[MemberDAO] setHashPw 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}	
		
		return setHashPwCount;
	}


	
	
}

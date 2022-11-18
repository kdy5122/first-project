package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vo.OrderBean;
import vo.OrderDetail;

public class OrderDAO {

	private Connection con;

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static OrderDAO orderDAO;

	private OrderDAO() {
		// TODO Auto-generated constructor stub
	}

	public static OrderDAO getInstance() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}


	// order_item 테이블 + order_detail테이블 동시에 insert(장바구니를 통한 여러상품구매
	public int insertOrder(OrderBean orderBean, ArrayList<OrderDetail> orderdetailList) {
		String sql = "";
		int insertOrderCount = 0;

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		sql = "insert into order_item values(?,?,?,?,?,?,?,?,?,?,?,now(),default)";

		String sql2 = "insert into order_detail (order_id,prod_id,prod_name,prod_price,o_amount)";
		sql2 += " values(?,?,?,?,?)";
		try {
			// 1. order table iinsert
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderBean.getOrder_id());
			pstmt.setString(2, orderBean.getMem_id());
			pstmt.setString(3, orderBean.getO_name());
			pstmt.setString(4, orderBean.getO_phone());
			pstmt.setString(5, orderBean.getO_email());
			pstmt.setString(6, orderBean.getO_address1());
			pstmt.setString(7, orderBean.getO_address2());
			pstmt.setString(8, orderBean.getO_address3());
			pstmt.setString(9, orderBean.getO_require());
			pstmt.setInt(10, orderBean.getTotalPrice());
			pstmt.setInt(11, orderBean.getUsePoint());

			pstmt.executeUpdate();

			// 2. order_detail

			for (int i = 0; i < orderdetailList.size(); i++) {
				pstmt2 = con.prepareStatement(sql2);

				OrderDetail orderDetail = orderdetailList.get(i);
				pstmt2.setString(1, orderDetail.getOrder_id());
				pstmt2.setInt(2, orderDetail.getProd_id());
				pstmt2.setString(3, orderDetail.getProd_name());
				pstmt2.setInt(4, orderDetail.getProd_price());
				pstmt2.setInt(5, orderDetail.getO_amount());

				insertOrderCount = pstmt2.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("[OrderDAO] insertCartOrder 에러:" + e);
		} finally {

			close(pstmt);
			close(pstmt2);
		}
		return insertOrderCount;
	}

	// 나의 주문내역 조회
	public ArrayList<OrderBean> selectUserOrderList(String mem_id, int page, int limit) {
		ArrayList<OrderBean> userOrderList = null;

		String sql = "select * from order_item where(mem_id=? and date(o_date)>date(subdate(now(),interval 6 month)))  order by o_date DESC limit ?,5";

		int startrow = (page - 1) * 5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setInt(2, startrow);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				userOrderList = new ArrayList<OrderBean>();// 기본값으로 채워진 myOrderList를

				do {
					// 조회한 각 주문내역 결과로 채운 Order객체를 추가함
					userOrderList.add(new OrderBean(rs.getString("order_id"), rs.getString("mem_id"),
							rs.getTimestamp("o_date"), rs.getString("o_status"), rs.getInt("totalPrice")));
				

				} while (rs.next()); // 주문내역이 없을 때까지 반복함
			}

		} catch (Exception e) {
			System.out.println(" [OrderDAO] selectUserOrderList 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return userOrderList;
	}
	
	public int userorderListCount(String mem_id) {
		int listCount = 0;

		try {
			String sql = "select count(*) from order_item where(mem_id=? and date(o_date)>date(subdate(now(),interval 6 month)))  order by o_date DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("[OrderDAO] userorderListCount 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	

	// 특정주문번호에 대한 주문상세 정보 얻기
	public ArrayList<OrderDetail> selectOrderDetailList(String order_id) {
		ArrayList<OrderDetail> orderDetailList = null;

		String sql = "select * from order_detail where order_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderDetailList = new ArrayList<OrderDetail>();// 기본값으로 채워진 myOrderList를

				do {
					// 조회한 각 주문내역 결과로 채운 Order객체를 추가함
					OrderDetail orderDetail = new OrderDetail();

					orderDetail.setProd_id(rs.getInt("prod_id"));
					orderDetail.setO_amount(rs.getInt("o_amount"));
					orderDetail.setProd_name(rs.getString("prod_name"));

					orderDetailList.add(orderDetail);

				} while (rs.next()); // 주문내역이 없을 때까지 반복함
			}

		} catch (Exception e) {
			System.out.println("[OrderDAO] selectOrderDetailList 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderDetailList;
	}

	// 특정주문번호에 대한 주문정보얻기 (배송지,배송정보..등)
	public ArrayList<String> getOrderUserInfo(String order_id) {
		ArrayList<String> orderUserInfo = null;

		String sql = "select * from order_item where order_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();

			orderUserInfo = new ArrayList<String>();

			while (rs.next()) {
		
				orderUserInfo.add(rs.getString("o_name"));
				orderUserInfo.add(rs.getString("o_phone"));
				orderUserInfo.add(rs.getString("o_email"));
				orderUserInfo.add(rs.getString("o_address1"));
				orderUserInfo.add(rs.getString("o_address2"));
				orderUserInfo.add(rs.getString("o_address3"));
				orderUserInfo.add(rs.getString("o_require"));
				orderUserInfo.add(rs.getString("totalPrice"));
				orderUserInfo.add(rs.getString("mem_id"));
				orderUserInfo.add(rs.getString("usePoint"));
		
				

			}

		} catch (Exception e) {
			System.out.println("[OrderDAO] getOrderUserInfo 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderUserInfo;
	}

	public ArrayList<OrderBean> selectManageOrderList(int page, int limit) {
		ArrayList<OrderBean> manageorderList = null;

		String sql = "select order_id,mem_id,o_date,totalPrice,o_status";
				sql+= " from order_item order by o_date desc limit ?,12";
	

		int startrow = (page - 1) * 12;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				manageorderList = new ArrayList<OrderBean>();// 기본값으로 채워진 myOrderList를

				do {

					OrderBean orderBean = new OrderBean();

					orderBean.setOrder_id(rs.getString("order_id"));
					orderBean.setMem_id(rs.getString("mem_id"));
					orderBean.setO_status(rs.getString("o_status"));
					orderBean.setO_date(rs.getDate("o_date"));
					orderBean.setTotalPrice(rs.getInt("totalPrice"));

					manageorderList.add(orderBean);

				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("[OrderDAO] selectManageOrderList 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return manageorderList;
	}
	

	//
	public  int orderListCount() {
		int listCount = 0;

		try {
			String sql = "select count(*) from order_item  order by o_date desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("[OrderDAO] orderListCount 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	
	//주문상태 업데이트
	public int updateOrderStatus(String order_id, String o_status) {
		int updateCount = 0;
		
		String sql = "update order_item set o_status=? where order_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, o_status);
			pstmt.setString(2, order_id);
		
			updateCount=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("[OrderDAO]  updateOrderStatus 오류 : " + e);
		} finally {
			close(pstmt);
		}

		return updateCount;

	}

	//당일 주문건 매출 조회
	public int dayTotalPrice() {
		int daytotal=0;
		
		String sql = "select ifnull(sum(totalPrice),0) from order_item where (o_date >curdate())";

		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				daytotal=rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(" [OrderDAO] dayTotalPrice 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		
		
		return daytotal;
	}

	//관리자모드 : 연,월 선택시 일별 매출 조회
	public  Map<String,String> selectDaySales(String ym) {
		 Map<String,String> daySlaesList=new LinkedHashMap<String,String>();//순서유지를 위해 LinkedHashMap 사용
		 
		
		 
		 String sql="select substr(order_id,1,8) as daytotal,sum(totalprice) as DtotalPrice";
		 sql+=" from order_item";
		 sql+=" group by o_status,substr(order_id,1,8)";
		sql+=" having o_status='처리완료' and daytotal like ?";
		/* sql+=" order by 2 asc"; */
		
		try {
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ym+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				daySlaesList.put(rs.getString("daytotal"), rs.getString("DtotalPrice"));
			}
		} catch (Exception e) {
			System.out.println("[OrderDAO] selectDaySales 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return daySlaesList;
	}

	//관리자모드:연 월 선택시 해당 월 매출 조회
	public String selectMonthSales(String ym) {
		String monthSales= null;
		
		String sql="select o_status,substr(order_id,1,6) as monthtotal,sum(totalprice) as MtotalPrice";
		 sql+=" from order_item";
		 sql+=" group by o_status,substr(order_id,1,6)";
		sql+=" having o_status='처리완료' and monthtotal like ?";
	
	
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ym+"%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				monthSales=rs.getString("MtotalPRice");
			}
		} catch (Exception e) {
			System.out.println("[OrderDAO] selectMonthSales 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return monthSales;
	}

	public int userMoneyListCount() { //페이징처리
		int listCount=0;
		
		try {
			String sql = "select count(*) from member where mem_grade != '2'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("[OrderDAO] userMoneyListCount 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		
		return listCount ;
	}
	
	
	
	
	
	//회원별 구매금액 

	public List<Object> selectUserMoneyList(int page, int limit) {
	
		 List<Object> userMoneyList =new ArrayList<Object>();
		 Map<String,String> hm=null;
		 
		 String sql="select mem_id,mem_grade,o_status,ifnull(sum(totalPrice),0) as totalPrice";
		 sql+=" from member left join order_item";
		 sql+=" using(mem_id)";
		sql+=" group by mem_id,mem_grade,o_status";
		sql+=" having mem_grade in('0','1')";
		sql+=" order by 4 desc";
		sql+=" limit ?,12";
		int startrow = (page - 1) * 12;
		try {
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				hm=new HashMap<>();
				hm.put("mem_id", rs.getString("mem_id"));
				hm.put("totalPrice",rs.getString("totalPrice"));
				hm.put("mem_grade", rs.getString("mem_grade"));
				userMoneyList.add(hm);
			
			}	
			
			
		} catch (Exception e) {
			System.out.println("[OrderDAO] selectUserMoneyList 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
	
		
		return userMoneyList;
	}



	 //사용자의 구매확정시 총 구매금액 조회후 일반회원 --> vip 등급조정 
	  public int updateMemberGrade(String mem_id) {
	  
	  int isUpdateGradeSuccess=0;
	  
	  
	  String sql="select mem_id,mem_grade,ifnull(sum(totalPrice),0) as totalPrice"; 
	  sql+=" from member left join order_item";
	  sql+=" using(mem_id)";
	  sql+=" group by mem_id,mem_grade";
	  sql+=" having totalPrice>=1000000";
	  sql+=" and mem_grade ='1'";
	  sql+=" and mem_id=?"; 

	  
	  
	  
	  try {
		  pstmt = con.prepareStatement(sql); 
		  pstmt.setString(1, mem_id);
	  
		  rs=pstmt.executeQuery();
		
		 
		  
		  if(rs.next()) { 
			  
				sql="update member set mem_grade ='0' where mem_id=?"; //vip
			 
		
			  
			  pstmt = con.prepareStatement(sql); 
		  pstmt.setString(1, rs.getString("mem_id"));
		  
		  isUpdateGradeSuccess=pstmt.executeUpdate();
	  
		  }	
	  
	  } catch (Exception e) {
	  System.out.println("[OrderDAO]  updateMemberGrade 오류 : " + e); }
	  finally {
	  close(pstmt); 
	  }
	  
	  return isUpdateGradeSuccess;
	  
	  }

	  //구매취소시 등급 재조정 vip=>일반
	  public int updateMemberReGrade(String mem_id) {
		  int isUpdateGradeSuccess=0;
		  
		  
		  String sql="select mem_id,mem_grade,ifnull(sum(totalPrice),0) as totalPrice"; 
		  sql+=" from member left join order_item";
		  sql+=" using(mem_id)";
		  sql+=" group by mem_id,mem_grade";
		  sql+=" having totalPrice<1000000";
		  sql+=" and mem_grade ='0'";
		  sql+=" and mem_id=?"; 
		   
		  
		  
		  
		  try {
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, mem_id);
			  
			  rs=pstmt.executeQuery();
			
			 
			  
			  if(rs.next()) { 
				  
					sql="update member set mem_grade ='1' where mem_id=?"; //vip
				 
			
				  
				  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, rs.getString("mem_id"));
			  
			  isUpdateGradeSuccess=pstmt.executeUpdate();
		  
			  }	
		  
		  } catch (Exception e) {
		  System.out.println("[OrderDAO]  updateMemberGrade 오류 : " + e); }
		  finally {
		  close(pstmt); 
		  }
		  
		  return isUpdateGradeSuccess;
		} 
		 
			
	  //사용자의 주문취소요청 -> 주문상태 취소요청으로 변경, 
	public int cancleOrder(String order_id) {
		int cancleOrderCount=0;
		
		String sql="update order_item set o_status='취소요청' where order_id=?";
		
		  
		  
		  try {
			  pstmt = con.prepareStatement(sql); 
				 pstmt.setString(1, order_id);
		  
				 cancleOrderCount=pstmt.executeUpdate();
			
			 
			 
		 
		  
		  } catch (Exception e) {
		  System.out.println("[OrderDAO]  cancleOrder 오류 : " + e); }
		  finally {
		  close(pstmt); 
		  }
		
		
		return cancleOrderCount;
	}

	

	public int deleteOrder(String order_id) {
		String sql = "";
		int deleteOrderCount = 0;

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		sql = "delete from order_item where order_id =?";

		String sql2 = "delete from order_detail where order_id=?";
	
		try {
			// 1. order table iinsert
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_id);
		
			pstmt.executeUpdate();

			// 2. order_detail

		
				pstmt2 = con.prepareStatement(sql2);

				pstmt2.setString(1, order_id);
				deleteOrderCount=pstmt2.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("[OrderDAO] deleteOrder 에러:" + e);
		} finally {

			close(pstmt);
			close(pstmt2);
		}
		return deleteOrderCount;
	
	  
}

/*
 * //주문취소시 포인트 회수 public int updateMemberRePoint(int totalPrice, String mem_id)
 * { int updatePointCount=0; int deletPoint=0; String
 * sql="select mem_grade from member where mem_id=?";
 * 
 * 
 * 
 * try {
 * 
 * pstmt = con.prepareStatement(sql); pstmt.setString(1, mem_id);
 * rs=pstmt.executeQuery();
 * 
 * if(rs.next()) {
 * 
 * if(rs.getString("mem_grade").equals("0")){ deletPoint=
 * (int)(totalPrice*0.03); }else if(rs.getString("mem_grade").equals("1")) {
 * deletPoint =(int)(totalPrice*0.02); }
 * 
 * sql="update member set mem_point =mem_point - ? where mem_id=?"; pstmt =
 * con.prepareStatement(sql); pstmt.setInt(1,deletPoint); pstmt.setString(2,
 * mem_id);
 * 
 * updatePointCount=pstmt.executeUpdate();
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 * } catch (Exception e) {
 * System.out.println("[OrderDAO] updateMemberReGrade 에러:" + e); } finally {
 * 
 * close(pstmt);
 * 
 * }
 * 
 * 
 * 
 * return updatePointCount; }
 */
	//포인트 사용시 포인트 차감
	public int updateUsePoint(String mem_id, int usePoint) {
		int updatePointCount=0;
		String sql="update member set mem_point =mem_point - ? where mem_id=?";
		
		
		try {
			
				 pstmt = con.prepareStatement(sql);
					pstmt.setInt(1,usePoint);
					pstmt.setString(2, mem_id);
				
					updatePointCount=pstmt.executeUpdate();

				  
			  
			  

		} catch (Exception e) {
			System.out.println("[OrderDAO] updateUsePoint 에러:" + e);
		} finally {

			close(pstmt);
			
		}
		
		
		
		
		return updatePointCount;
	}

	//구매후 포인트 적립
	public int updatePoint(String mem_id, int addPoint) {
		int updatePointCount=0;
		
		String sql="update member set mem_point =mem_point + ? where mem_id=?";
		
		
		try {
			
				 pstmt = con.prepareStatement(sql);
					pstmt.setInt(1,addPoint);
					pstmt.setString(2, mem_id);
				
					updatePointCount=pstmt.executeUpdate();

				  
			  
			  

		} catch (Exception e) {
			System.out.println("[OrderDAO] updateUsePoint 에러:" + e);
		} finally {

			close(pstmt);
			
		}
		
		
		
		
		
		
		return updatePointCount;
	}
	
	
	//구매취소시 사용한 포인트 돌려줌
	public int ReturnMemberPoint(String order_id, String mem_id) {
		int updatePointCount=0;
		

		int deletPoint=0;
		String sql="select usePoint from order_item where order_id =?";

		
		 
		  try {
			  
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, order_id);
			  rs=pstmt.executeQuery();
			  
			  if(rs.next()) {
				  
				 if(rs.getInt("usePoint")>0){
				
				 sql="update member set mem_point =mem_point + ? where mem_id=?";
				 pstmt = con.prepareStatement(sql);
					pstmt.setInt(1,rs.getInt("usePoint"));
					pstmt.setString(2, mem_id);
				
					updatePointCount=pstmt.executeUpdate();
			
				  
				 }
			  
			  
			  }
			  
			  
			
			} catch (Exception e) {
				System.out.println("[OrderDAO] ReturnMemberPoint 에러:" + e);
			} finally {
			
				close(pstmt);
				
			}
			
			
			
			return updatePointCount;
				
			
				
	}

	public int getPointRate(String mem_grade) {
		int point_rate=0;
		String sql = "select * from point where mem_grade=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_grade);

			rs = pstmt.executeQuery();

	
			if (rs.next()) {
		
				point_rate=rs.getInt("point_rate");

			}

		} catch (Exception e) {
			System.out.println("[OrderDAO] getOrderUserInfo 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return point_rate;
	}		
				
			
		
}



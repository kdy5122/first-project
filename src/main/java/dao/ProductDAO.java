package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import vo.OrderDetail;
import vo.ProductBean;

public class ProductDAO {

	private Connection con;

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static ProductDAO productDAO;

	private ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ProductDAO getInstance() {
		if (productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 사용자모드 상품 전체 글 개수
	public int racketListCount(String prod_kind) {
		int listCount = 0;

		try {

			String sql = "select count(*) from product where (prod_kind=? and prod_status='y')";
			// String sql="select count(*) from product where (prod_kind='acc' and
			// prod_status='y')";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prod_kind);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] RacketgetListCount 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	// 사용자모드 글목록가져오기

	public ArrayList<ProductBean> selectRacketList(int page, int limit, String prod_kind) {

		ArrayList<ProductBean> listRacket = new ArrayList<ProductBean>();

		String sql = "select * from product where (prod_kind=? and prod_status='y') order by prod_id desc limit ?,6"; // 1행부터
																														// 시작하고싶으면
																														// 0,6
																														// 3행부터싲가하고싶으면
																														// 4,6
		int startrow = (page - 1) * 6; // 읽기시작할 row번호//mysql은 0부터 시작이라 page-1할 필요없음

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prod_kind);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductBean productBean = new ProductBean();

				productBean.setProd_id(rs.getInt("prod_id"));
				productBean.setProd_name(rs.getString("prod_name"));
				productBean.setProd_kind(prod_kind);
				productBean.setProd_price(rs.getInt("prod_price"));
				productBean.setProd_amount(rs.getInt("prod_amount"));
				productBean.setProd_content(rs.getString("prod_content"));
				productBean.setProd_image(rs.getString("prod_image"));
				productBean.setProd_status(rs.getString("prod_status"));
				productBean.setProd_date(rs.getDate("prod_date"));

				listRacket.add(productBean);

			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] Racket selectList 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listRacket;
	}

	public ArrayList<ProductBean> lowPrice(int page, int limit, String prod_kind) {

		ArrayList<ProductBean> listRacket = new ArrayList<ProductBean>();

		String sql = "select * from product where (prod_kind=? and prod_status='y') order by prod_price limit ?,6"; // 1행부터
																													// 시작하고싶으면
																													// 0,6
																													// 3행부터싲가하고싶으면
																													// 4,6
		int startrow = (page - 1) * 6; // 읽기시작할 row번호//mysql은 0부터 시작이라 page-1할 필요없음

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prod_kind);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductBean productBean = new ProductBean();

				productBean.setProd_id(rs.getInt("prod_id"));
				productBean.setProd_name(rs.getString("prod_name"));
				productBean.setProd_kind(prod_kind);
				productBean.setProd_price(rs.getInt("prod_price"));
				productBean.setProd_amount(rs.getInt("prod_amount"));
				productBean.setProd_content(rs.getString("prod_content"));
				productBean.setProd_image(rs.getString("prod_image"));
				productBean.setProd_status(rs.getString("prod_status"));
				productBean.setProd_date(rs.getDate("prod_date"));

				listRacket.add(productBean);

			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] lowPrice 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listRacket;
	}

	//높은가격순
	public ArrayList<ProductBean> hightPrice(int page, int limit, String prod_kind) {

		ArrayList<ProductBean> listRacket = new ArrayList<ProductBean>();

		String sql = "select * from product where (prod_kind=? and prod_status='y') order by prod_price desc limit ?,6"; // 1행부터
																															// 시작하고싶으면
																															// 0,6
																															// 3행부터싲가하고싶으면
																															// 4,6
		int startrow = (page - 1) * 6; // 읽기시작할 row번호//mysql은 0부터 시작이라 page-1할 필요없음

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prod_kind);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductBean productBean = new ProductBean();

				productBean.setProd_id(rs.getInt("prod_id"));
				productBean.setProd_name(rs.getString("prod_name"));
				productBean.setProd_kind(prod_kind);
				productBean.setProd_price(rs.getInt("prod_price"));
				productBean.setProd_amount(rs.getInt("prod_amount"));
				productBean.setProd_content(rs.getString("prod_content"));
				productBean.setProd_image(rs.getString("prod_image"));
				productBean.setProd_status(rs.getString("prod_status"));
				productBean.setProd_date(rs.getDate("prod_date"));

				listRacket.add(productBean);

			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] hightPrice 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listRacket;
	}

	//관리자모드: 상품관리목록
	public ArrayList<ProductBean> selectlistAll(int page,int limit) {

		ArrayList<ProductBean> listAll = new ArrayList<ProductBean>();

		String sql = "select * from product order by prod_id desc limit ?,12";
		int startrow = (page - 1) * 12;
		
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBean productBean = new ProductBean();

				productBean.setProd_id(rs.getInt("prod_id"));
				productBean.setProd_name(rs.getString("prod_name"));
				productBean.setProd_kind(rs.getString("prod_kind"));
				productBean.setProd_price(rs.getInt("prod_price"));
				productBean.setProd_amount(rs.getInt("prod_amount"));
				productBean.setProd_content(rs.getString("prod_content"));
				productBean.setProd_image(rs.getString("prod_image"));
				productBean.setProd_status(rs.getString("prod_status"));
				productBean.setProd_date(rs.getDate("prod_date"));

				listAll.add(productBean);

			}
		} catch (Exception e) {
			System.out.println("[ProductDAO]  selecthightList 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listAll;
	}

	
	//관리자모드:상품관리목록 페이징
	public int ListManagerCount() {
		int listCount = 0;

		try {

			String sql = "select count(*) from product order by prod_id desc";
			// String sql="select count(*) from product where (prod_kind='acc' and
			// prod_status='y')";

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] ListManagerCount 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	
	
	
	
	
	
	
	// 상품등록
	public int insertProduct(ProductBean productBean) {
		String sql = "";
		int insertCount = 0;

		sql = "insert into product (prod_name,prod_kind,prod_price,prod_amount,prod_content,prod_image,prod_status,prod_date)";
		sql += " values (?,?,?,?,?,?,?,now())";

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productBean.getProd_name());
			pstmt.setString(2, productBean.getProd_kind());
			pstmt.setInt(3, productBean.getProd_price());
			pstmt.setInt(4, productBean.getProd_amount());
			pstmt.setString(5, productBean.getProd_content());
			pstmt.setString(6, productBean.getProd_image());
			pstmt.setString(7, productBean.getProd_status());

			insertCount = pstmt.executeUpdate();// 성공시 1

		} catch (Exception e) {
			System.out.println("insertProduct() 에러" + e);
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	public ProductBean selectProduct(int prod_id) {//상품 정보
		ProductBean productBean = null;
		String sql = "select * from product where prod_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prod_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("selectProduct 성공");
				productBean = new ProductBean();
				productBean.setProd_id(rs.getInt("prod_id"));
				productBean.setProd_name(rs.getString("prod_name"));
				productBean.setProd_kind(rs.getString("prod_kind"));
				productBean.setProd_price(rs.getInt("prod_price"));
				productBean.setProd_amount(rs.getInt("prod_amount"));
				productBean.setProd_content(rs.getString("prod_content"));
				productBean.setProd_image(rs.getString("prod_image"));
				productBean.setProd_status(rs.getString("prod_status"));
				productBean.setProd_date(rs.getDate("prod_date"));

			}
		} catch (Exception e) {
			System.out.println("selectProduct() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return productBean;
	}

	// 상품 수정
	public int updateproduct(ProductBean productBean) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update product set prod_name=?,prod_kind=?,prod_price=?,prod_amount=?, prod_content=?";
				
		if(productBean.getProd_image()!=null) {
		sql+= ",prod_image='"+productBean.getProd_image()+"'";
		}
		
		sql+=" ,prod_status=? where prod_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productBean.getProd_name());
			pstmt.setString(2, productBean.getProd_kind());
			pstmt.setInt(3, productBean.getProd_price());
			pstmt.setInt(4, productBean.getProd_amount());
			pstmt.setString(5, productBean.getProd_content());

			pstmt.setString(6, productBean.getProd_status());
			pstmt.setInt(7, productBean.getProd_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateProduct 오류 : " + e);
		} finally {
			close(pstmt);
		}

		return updateCount;

	}

	/*
	 * public boolean isArticleBoardWriter(int board_no, String mem_id) {
	 * PreparedStatement pstmt = null; ResultSet rs = null; String
	 * sql="select * from product where board_no=?"; boolean isWriter = false;
	 * 
	 * try{ pstmt=con.prepareStatement(sql); pstmt.setInt(1, board_no);
	 * rs=pstmt.executeQuery(); rs.next();
	 * 
	 * if(mem_id.equals(rs.getString("mem_id"))){ isWriter = true; }
	 * }catch(SQLException ex){ System.out.println("isBoardWriter 오류 : "+ex); }
	 * finally{ close(pstmt); }
	 * 
	 * return isWriter; }
	 */

	public int deleteProduct(int prod_id) {

		PreparedStatement pstmt = null;
		String product_delete_sql = "delete from product where prod_id=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(product_delete_sql);
			pstmt.setInt(1, prod_id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("productDelete 오류 : " + ex);
		} finally {
			close(pstmt);
		}

		return deleteCount;

	}

	//주문시 재고수 감소
	public int updateProductQty(ArrayList<OrderDetail> orderdetailList) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update product set prod_amount=prod_amount-? where prod_id=?";

		try {
			for (int i = 0; i < orderdetailList.size(); i++) {
			pstmt = con.prepareStatement(sql);
			
			OrderDetail orderDetail = orderdetailList.get(i);
			pstmt.setInt(1,orderDetail.getO_amount());
			pstmt.setInt(2, orderDetail.getProd_id());
			updateCount = pstmt.executeUpdate();
			
			}
		} catch (Exception e) {
			System.out.println("updateProductQty 오류 : " + e);
		} finally {
			close(pstmt);
		}

		return updateCount;

	}
	//주문취소 되면 재고수 다시 증가
	public int resetProductQty(String order_id) {
		int insertOrderCount=0;
		
		String sql="select prod_id,o_amount from order_detail where order_id=?";
		

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_id);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				 sql="update product set prod_amount=prod_amount+ ? where prod_id=?";
					pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("o_amount"));
				pstmt.setInt(2, rs.getInt("prod_id"));
				insertOrderCount=pstmt.executeUpdate();
				
				}
		} catch (Exception e) {			
			System.out.println("resetProductQty 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		
		}
		
		return insertOrderCount;
	}

	public  Map<Integer,String>   getProdName(String mem_id) {
		Map<Integer,String>  orderList=new LinkedHashMap<Integer,String>();
		
		try {

			String sql = "select prod_name,prod_id from order_detail join order_item using(order_id)";
			sql+=" where mem_id=?";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderList.put(rs.getInt("prod_id"), rs.getString("prod_name"));
			}
		} catch (Exception e) {
			System.out.println("[ProductDAO] getProdName 에러 :" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return orderList;
	}

	
	
	}	


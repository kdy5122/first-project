package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;

import vo.ProductBean;


public class RacketListService {

	public int getListCount(String prod_kind) {
		int listCount = 0;
		
		/*공통부분*/
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listCount = productDAO.racketListCount( prod_kind);
		close(con);
		return listCount;
	}

	public ArrayList<ProductBean> getRacketList(int page, int limit,String prod_kind) {
		ArrayList<ProductBean> listRacket = null;
		
		/*공통부분*/
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listRacket = productDAO.selectRacketList(page,limit,prod_kind);
		close(con);
		return listRacket;
	}
	public ArrayList<ProductBean> getlowRacketList(int page, int limit,String prod_kind) {
		ArrayList<ProductBean> listRacket = null;
		
		/*공통부분*/
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listRacket = productDAO.lowPrice(page,limit,prod_kind);
		close(con);
		return listRacket;
	}
	public ArrayList<ProductBean> gethightRacketList(int page, int limit,String prod_kind) {
		ArrayList<ProductBean> listRacket = null;
		
		/*공통부분*/
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listRacket = productDAO.hightPrice(page,limit,prod_kind);
		close(con);
		return listRacket;
	}

}

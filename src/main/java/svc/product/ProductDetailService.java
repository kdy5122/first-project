package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ProductDAO;

import vo.ProductBean;


public class ProductDetailService {

	public ProductBean getGoods(int prod_id) {
		ProductBean goods = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		/*
		 * int updateCount = reviewDAO.updateReadCount(board_no);
		 * 
		 * if(updateCount > 0){ commit(con); } else{ rollback(con); }
		 */
		
		goods = productDAO.selectProduct(prod_id);
		close(con);

		

		return goods;
	}

}

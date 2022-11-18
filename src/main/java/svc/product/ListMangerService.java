package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.ProductBean;

public class ListMangerService {
	
	public ArrayList<ProductBean> getlistAll(int page, int limit) {
		ArrayList<ProductBean> listAll = null;
		

		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);

			
			listAll = productDAO.selectlistAll(page,limit);
			close(con);
			return listAll;
		
		

	}

	public int getListCount() {
int listCount = 0;
		
		/*공통부분*/
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listCount = productDAO.ListManagerCount( );
		close(con);
		return listCount;
	}
}

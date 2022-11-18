package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ProductDAO;


public class OrderItemNameService {

	public   Map<Integer,String> getOrderList(String mem_id) {
		Map<Integer,String>  orderList=new LinkedHashMap<Integer,String>();
		
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		 orderList=productDAO.getProdName(mem_id);

		close(con);
		
		return orderList;
	}

}

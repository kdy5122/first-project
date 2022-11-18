package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductDetailService;
import vo.ActionForward;
import vo.ProductBean;


public class ProductModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int prod_id=0;
		try {
			prod_id = Integer.parseInt(request.getParameter("prod_id"));
		} catch (NumberFormatException e) {
		}
		
		System.out.println("prod_id 값 널 테스트"+prod_id);
		
		ProductDetailService productDetailService = new ProductDetailService();	
		
	   	ProductBean goods =productDetailService.getGoods(prod_id);
	   	
	   	request.setAttribute("goods", goods);
	   	
   		forward.setPath("/product/product_modify.jsp");
   		return forward;
	}

}

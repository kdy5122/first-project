package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductDetailService;
import svc.review.RevDetailViewService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReviewBean;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prod_id=Integer.parseInt(request.getParameter("prod_id"));
		
		ProductDetailService productDetailService=new ProductDetailService();
		ProductBean goods= productDetailService.getGoods(prod_id);
		
	
		request.setAttribute("goods", goods);//el 언어사용할ㄸ'ㅐ 속성명..
		

		ActionForward forward = new ActionForward("/product/product_detail.jsp", false);
		return forward;
	}

}

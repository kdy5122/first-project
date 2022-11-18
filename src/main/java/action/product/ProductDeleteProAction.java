package action.product;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductDeleteProService;
import vo.ActionForward;

public class ProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ActionForward forward= null;
	    String fileName = request.getParameter("prod_image");
		int prod_id=Integer.parseInt(request.getParameter("prod_id"));
		
		 Enumeration params = request.getParameterNames();
			System.out.println("----------------------------");
			while (params.hasMoreElements()){
			    String name = (String)params.nextElement();
			    System.out.println(name + " : " +request.getParameter(name));
			}
			System.out.println("----------------------------");
		 
		 
		ProductDeleteProService productDeleteProService = new ProductDeleteProService();
		//isArticleWriter(DAO의 isArticeBoraderWirter 참조[select borad num해서 그 넘버에대한 비밀번호가 pass와 일치하면 isWriter true ]) 
		//와 removeartice() 메서드 두개를 갖는 서비스 
		//들어올 때 이미 로그인ID와 해당하는 글의 ID를 비교해서 일치하지않으면 삭제버튼이 보이지않게 했기 때문에, 
		//isArticleWriter(DAO의 isArticeBoraderWirter 참조[select borad num해서 그 넘버에대한 비밀번호가 pass와 일치하면 isWriter true ])경로 전체를 삭제하여 확인누르면 바로삭제작동
 
		
			boolean isDeleteSuccess = productDeleteProService.removeProduct(prod_id);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				

				  ServletContext context = request.getServletContext();
					String uploadPath = context.getRealPath("/productUpload");
			        
			        File file = new File(uploadPath+File.separator +fileName);
			       
			        
			        if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다..바로 하면 삭제가안됨..?ㅎ
			        	//Thread.sleep(1000); 
			        	file.delete();
			           
			        }
			    
				
				
				
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("productListManager.po");
			}
			
		

	    
	    
	    
		return forward;
	}
	

}

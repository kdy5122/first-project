package action.review;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.review.RevDeleteProService;
import vo.ActionForward;

public class RevDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		 String fileName = request.getParameter("rev_fileName");
		
		 Enumeration params = request.getParameterNames();
			System.out.println("----------------------------");
			while (params.hasMoreElements()){
			    String name = (String)params.nextElement();
			    System.out.println(name + " : " +request.getParameter(name));
			}
			System.out.println("----------------------------");
		 
		 
		 
		//글작성자와 로그인작성자가 같으면 수정 삭제버튼이 보이겠지만 get방식으로 홈페이지에 바로접속할 수 있으므로 한번더 걸러주기
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
	
		
		RevDeleteProService revDeleteProService = new RevDeleteProService();
		boolean isArticleWriter =revDeleteProService.isArticleWriter(board_no, mem_id);
/*
		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제권한이 없습니다.);");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			*/
			//삭제작업
			
			boolean isDeleteSuccess = revDeleteProService.removeArticle(board_no);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{//삭제작업 성공시 저장된 파일도 지워야함
				 
			     if(fileName!=null) {
				  ServletContext context = request.getServletContext();
					String uploadPath = context.getRealPath("/reviewUpload");
			        
			        File file = new File(uploadPath+File.separator +fileName);

			    
			        
			        if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다..바로 하면 삭제가안됨..?ㅎ
			        	//Thread.sleep(1000); 
			        	file.delete();
			        	
			        }
			     }
			     
				
				
				
				forward = new ActionForward("rev_boardList.do", false); //전달값없으므로 redirect

			}
			
		/*}*/


		return forward;
	}

}

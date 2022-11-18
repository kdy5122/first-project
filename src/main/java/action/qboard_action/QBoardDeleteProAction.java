package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;


public class QBoardDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 

		ActionForward forward = null;
		int qboard_num=Integer.parseInt(request.getParameter("qboard_num"));
		String nowPage = request.getParameter("page");
		QBoardDeleteProService boardDeleteProService = new QBoardDeleteProService();
		//isArticleWriter(DAO의 isArticeBoraderWirter 참조[select borad num해서 그 넘버에대한 비밀번호가 pass와 일치하면 isWriter true ]) 
		//와 removeartice() 메서드 두개를 갖는 서비스 
		//들어올 때 이미 로그인ID와 해당하는 글의 ID를 비교해서 일치하지않으면 삭제버튼이 보이지않게 했기 때문에, 
		//isArticleWriter(DAO의 isArticeBoraderWirter 참조[select borad num해서 그 넘버에대한 비밀번호가 pass와 일치하면 isWriter true ])경로 전체를 삭제하여 확인누르면 바로삭제작동
 
		
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(qboard_num);

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
				forward = new ActionForward("qboardList.qna?page=" + nowPage,true);
			}
			
		


		return forward;
	}

}

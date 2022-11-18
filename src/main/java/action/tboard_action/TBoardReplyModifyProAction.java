package action.tboard_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.tboard_svc.TBoardModifyProService;
import svc.tboard_svc.TBoardReplyModfiyProService;
import vo.ActionForward;
import vo.TBoardBean;

public class TBoardReplyModifyProAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//본인이 아니면 수정버튼 나오지 않음 
				ActionForward forward = null;
				boolean isModifySuccess = false;
				int qboard_num=Integer.parseInt(request.getParameter("TBOARD_NUM"));
				TBoardBean article=new TBoardBean();
				TBoardReplyModfiyProService tboardReplyModifyProService = new TBoardReplyModfiyProService();


				    

					article.setTBOARD_NUM(qboard_num);
					article.setTBOARD_SUBJECT(request.getParameter("TBOARD_SUBJECT"));
					article.setTBOARD_CONTENT(request.getParameter("TBOARD_CONTENT")); 
					
					
					isModifySuccess = tboardReplyModifyProService.modifyReplyArticle(article);

					if(!isModifySuccess){
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out=response.getWriter();
						out.println("<script>");
						out.println("alert('수정실패');");
						out.println("history.back()");
						out.println("</script>");
					}
					else{
						forward = new ActionForward("tboardDetail.bo?tboard_num="+article.getTBOARD_NUM(),true);
					}

			

				return forward;
			
	}

}

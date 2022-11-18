package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		//본인이 아니면 수정버튼 나오지 않음 
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int qboard_num=Integer.parseInt(request.getParameter("QBOARD_NUM"));
		QBoardBean article=new QBoardBean();
		QBoardModifyProService boardModifyProService = new QBoardModifyProService();


		    

			article.setQBOARD_NUM(qboard_num);
			article.setQBOARD_SUBJECT(request.getParameter("QBOARD_SUBJECT"));
			article.setQBOARD_CONTENT(request.getParameter("QBOARD_CONTENT")); 
			
			
			isModifySuccess = boardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward("qboardDetail.qna?qboard_num="+article.getQBOARD_NUM(),true);
			}

	

		return forward;
	}

}

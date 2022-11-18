package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardReplyProAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 
		 	ActionForward forward = null;
		    String nowPage = request.getParameter("page");
		 	QBoardBean article = new QBoardBean();  		
		 	article.setMEM_ID(request.getParameter("loginID"));
		 	article.setQBOARD_NUM(Integer.parseInt(request.getParameter("qboard_num")));
		 	article.setQBOARD_SUBJECT(request.getParameter("QBOARD_SUBJECT"));
		 	article.setQBOARD_CONTENT(request.getParameter("QBOARD_CONTENT"));

		 	article.setQBOARD_RE_REF(Integer.parseInt(request.getParameter("QBOARD_RE_REF")));
		 	article.setQBOARD_RE_LEV(Integer.parseInt(request.getParameter("QBOARD_RE_LEV")));
		 	article.setQBOARD_RE_SEQ(Integer.parseInt(request.getParameter("QBOARD_RE_SEQ")));	   		
		 	QBoardReplyProService boardReplyProService = new QBoardReplyProService();
		 	boolean isReplySuccess = boardReplyProService.replyArticle(article);
		 	
		 	
	   		if(isReplySuccess){
	   			forward = new ActionForward("qboardList.qna?page=" + nowPage,true);
	   		}
	   		else{
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('답장실패')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}
	   		
	   		return forward;
	   		
	}  	
	 
}

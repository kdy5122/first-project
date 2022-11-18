package action.qboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;


public class QBoardReplyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 
		 	ActionForward forward = new ActionForward();
	   		String nowPage = request.getParameter("page");
	   		int qboard_num=Integer.parseInt(request.getParameter("qboard_num")); //해당하는 글의 borad_num값을 받아서 borad_num에 담음
	   		   		
	   		QBoardDetailService boardDetailService = new QBoardDetailService();
	   		QBoardBean article=boardDetailService.getArticle(qboard_num);	
	   		request.setAttribute("article", article);
	   		request.setAttribute("page", nowPage);
	   		forward.setPath("/qboard/qboard_reply.jsp");
	   		return forward;
	   		
	}
	 
}

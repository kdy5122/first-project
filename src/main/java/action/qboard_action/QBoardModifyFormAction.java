package action.qboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		 	ActionForward forward = new ActionForward();
			int qboard_num=Integer.parseInt(request.getParameter("qboard_num"));
			QBoardDetailService boardDetailService
			= new QBoardDetailService();	
		   	QBoardBean article =boardDetailService.getArticle(qboard_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/qboard/qboard_modify.jsp");
	   		return forward;
	   		
	 }
	 
}

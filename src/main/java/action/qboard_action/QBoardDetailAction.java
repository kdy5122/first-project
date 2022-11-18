package action.qboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

 public class QBoardDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int qboard_num=Integer.parseInt(request.getParameter("qboard_num"));
		String page = request.getParameter("page");
		QBoardDetailService boardDetailService = new QBoardDetailService();
		QBoardBean article = boardDetailService.getArticle(qboard_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/qboard/qboard_view.jsp");
   		return forward;

	 }
	 
}

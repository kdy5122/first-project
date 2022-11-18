package action.tboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;


public class TBoardReplyFormAction implements action.Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		    ActionForward forward = null;
		 	forward = new ActionForward();
	   		String nowPage = request.getParameter("page");
	   		       
	   		int tboard_num=Integer.parseInt(request.getParameter("tboard_num")); //해당하는 글의 borad_num값을 받아서 borad_num에 담음
	   		   		
	   		TBoardDetailService tboardDetailService = new TBoardDetailService();
	   		TBoardBean article=tboardDetailService.getArticle(tboard_num);	
	   		request.setAttribute("article", article);
	   		request.setAttribute("page", nowPage);
	   	
	   		forward.setPath("/tboard/tboard_reply.jsp");
	   		return forward;
	   		
	}
	 
}

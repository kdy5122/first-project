package action.tboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;

public class TBoardModifyFormAction implements action.Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		    ActionForward forward = null;
		    String page = request.getParameter("page");
		 	forward = new ActionForward();
			int tboard_num=Integer.parseInt(request.getParameter("tboard_num"));
			TBoardDetailService tboardDetailService
			= new TBoardDetailService();	
		   	TBoardBean article =tboardDetailService.getArticle(tboard_num);
		   	request.setAttribute("article", article);
		   	request.setAttribute("page", page);
		   	
		   	if(Integer.parseInt(request.getParameter("lev"))==0) {		   		       
	   		forward.setPath("/tboard/tboard_modify.jsp"); //여기서 page값 전달 끊김 
	   		return forward;
		   	}else {
		   	forward.setPath("/tboard/tboard_reply_modify.jsp"); //여기서 page값 전달 끊김 
		   	return forward;	
		   	}
		   	
		   	
	   		
	 }
	 
}

package action.tboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;

 public class TBoardDetailAction implements action.Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		ActionForward forward = null;
		int tboard_num=Integer.parseInt(request.getParameter("tboard_num"));
		String page = request.getParameter("page");
		System.out.println("출력테스트"+request.getParameter("page"));
				 
		TBoardDetailService tboardDetailService = new TBoardDetailService();
		TBoardBean article = tboardDetailService.getArticle(tboard_num);
		forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/tboard/tboard_view.jsp");
   		return forward;

	 }
	 
}

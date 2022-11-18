package action.tboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;

public class TBoardReplyProAction implements action.Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		   
		 	ActionForward forward = null;
		    String nowPage = request.getParameter("page"); 
		    System.out.println("디버그 확인 loginID" + request.getParameter("loginID"));
		    
		 	TBoardBean article = new TBoardBean();  		
		 	article.setMEM_ID(request.getParameter("loginID"));
		 	article.setTBOARD_NUM(Integer.parseInt(request.getParameter("TBOARD_NUM")));
		 	article.setTBOARD_SUBJECT(request.getParameter("TBOARD_SUBJECT"));
		 	article.setTBOARD_CONTENT(request.getParameter("TBOARD_CONTENT"));
		 	article.setTBOARD_PLACELA("NULL");  //답변글에는 위도 , 경도를 NULL로 set하여 작성할 때 지도가 뜨지않게한다.
		 	article.setTBOARD_PLACEMA("NULL");  //답변글에는 위도 , 경도를 NULL로 set하여 작성할 때 지도가 뜨지않게한다.
		 	article.setTBOARD_RE_REF(Integer.parseInt(request.getParameter("TBOARD_RE_REF")));
		 	article.setTBOARD_RE_LEV(Integer.parseInt(request.getParameter("TBOARD_RE_LEV")));
		 	article.setTBOARD_RE_SEQ(Integer.parseInt(request.getParameter("TBOARD_RE_SEQ")));	   		
		 	TBoardReplyProService tboardReplyProService = new TBoardReplyProService();
		 	boolean isReplySuccess = tboardReplyProService.replyArticle(article);
		 	
		 	
	   		if(isReplySuccess){
	   			forward = new ActionForward("tboardList.bo?page=" + nowPage,true);
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

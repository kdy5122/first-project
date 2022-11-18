package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward forward=null;
		QBoardBean boardBean = null;

		boardBean = new QBoardBean();
        boardBean.setMEM_ID(request.getParameter("loginID")); //세션에 있는 로그인한 아이디를 setMEM_ID에 set시켜준다.
		boardBean.setQBOARD_SUBJECT(request.getParameter("QBOARD_SUBJECT"));
		boardBean.setQBOARD_CONTENT(request.getParameter("QBOARD_CONTENT"));


		
   
		
		QBoardWriteProService boardWriteProService = new QBoardWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(boardBean);

		if(!isWriteSuccess){ //true가아니면 등록실패 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{ 
			forward = new ActionForward();
			forward.setRedirect(true); //리다이렉트
			forward.setPath("qboardList.qna");
		}

		return forward;
		
	}  	
	
}

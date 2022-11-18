package action.tboard_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class TBoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession(); //서블릿영역 getSession으로 서버에서 session영역을 요청한다.
		String loginID = (String)session.getAttribute("mem_id");
		
		
		if(loginID==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글쓰기 권한이 없습니다. 로그인해주세요.');");
			out.println("location.href='loginForm.jsp'");
			out.println("</script>");
			
		}else {
		 forward= new ActionForward("tboard/tboard_write.jsp",false);
		}
				
		return forward;
	}

}

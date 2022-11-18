package action.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.Member.MemberDeleteService;
import vo.ActionForward;

public class MemberDeleteAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mem_id");

		ActionForward forward = null;
		String deleteId = request.getParameter("id");
		MemberDeleteService memberDeleteService = new MemberDeleteService();
		
		boolean deleteResult = memberDeleteService.deleteMember(deleteId);

		if (deleteResult == true) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보삭제 실패.');");
			out.println("history.back();';");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보를  삭제 하였습니다.');");
			out.println("location.href='memberListAction.me';");
			out.println("</script>");
		}
		return forward;
	}
}

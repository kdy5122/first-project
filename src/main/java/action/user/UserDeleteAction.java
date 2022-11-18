package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserDeleteService;
import svc.Member.MemberDeleteService;
import svc.Member.MemberPwFindService;
import vo.ActionForward;
import vo.MemberBean;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mem_id");

		ActionForward forward = null;
		String deleteId = request.getParameter("id");
		MemberDeleteService memberDeleteService = new MemberDeleteService();
		boolean deleteResult = memberDeleteService.deleteMember(deleteId);

		if(deleteResult == true) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원탈퇴에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {//회원탈퇴에 성공하면
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");		
			out.println("alert('회원탈퇴에 성공했습니다. 그동안 감사했습니다.');");			
			out.println("location.href='logout';");
			out.println("</script>");			
		}
		return forward;
	}

}

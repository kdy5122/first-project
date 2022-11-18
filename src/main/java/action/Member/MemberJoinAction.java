package action.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.Member.MemberJoinService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinAction implements Action {

	public ActionForward execute(HttpServletRequest 
			request, HttpServletResponse response)
				throws Exception {
		
		MemberBean member = new MemberBean();
		boolean joinResult=false;
		
		member.setMem_id(request.getParameter("mem_id"));

		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_call(request.getParameter("mem_call"));
		member.setMem_postcode(request.getParameter("mem_postcode"));
		member.setMem_address1(request.getParameter("mem_address1"));
		member.setMem_address2(request.getParameter("mem_address2"));
		member.setMem_address3(request.getParameter("mem_address3"));
	    
		String mem_pwd = request.getParameter("mem_pwd");
		member.setMem_pwd(SHA256.encodeSHA256(mem_pwd));
		
		MemberJoinService memberJoinService=new MemberJoinService();
		joinResult=memberJoinService.joinMember(member);
		
		ActionForward forward = null;
		if(joinResult==false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패 하였습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 성공하였습니다.')");
			out.println("location.href='loginForm.jsp';");
			out.println("</script>");
		}
		return forward;
	}

}

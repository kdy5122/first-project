package action.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.Member.MemberPwFindService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberBean;

public class MemberPwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String mem_id = request.getParameter("mem_id");//파라미터로 전송된 아이디와
		String mem_email = request.getParameter("mem_email");//이메일로
		//사용자 정보를 조회하여 그 조회결과 중 pw값으로 UserBean객체에 셋팅한 후 리턴받아
		MemberPwFindService memberPwFindService = new MemberPwFindService();
		MemberBean memberInfo = memberPwFindService.findHashPw(mem_id, mem_email);//암호화X
				
		if(memberInfo == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 이메일정보가 일치하지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			String ramdom_password = SHA256.getRamdomPassword(8);//8자리 랜덤비번을 생성하여 
			System.out.println("ramdom_password : " + ramdom_password);
			
			boolean isSetHashPwSuccess = memberPwFindService.setHashPw(mem_id, mem_email,ramdom_password);
			
			if(isSetHashPwSuccess==false) {//if(!isSetHashPwSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디 또는 이메일정보가 일치하지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}else {	
			
			
			//조회한 UserBean객체에서 pw를 얻어와 request영역에 pre_password이름으로 얻어온 pw값을 속성값으로 공유
			String mem_pwd = memberInfo.getMem_pwd();
			request.setAttribute("mem_id", mem_id);
			request.setAttribute("ramdom_password", ramdom_password);
			//request영역에 showPage에 나타낼 user/findPwComplete.jsp뷰페이지를 설정하여
			request.setAttribute("showPage", "/findPwComplete.jsp");
			forward = new ActionForward("memberTemplate.jsp", false);//디스패치 방식으로 포워딩
			}
		}
		return forward;
	}

}

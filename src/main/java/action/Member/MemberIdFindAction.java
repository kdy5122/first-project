package action.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import svc.Member.MemberIdFindService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberIdFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String mem_email = request.getParameter("mem_email");//파라미터로 전송된 이메일로
		//사용자 정보를 조회하여 그 조회결과 중 id값으로 UserBean객체에 셋팅한 후 리턴받아
		MemberIdFindService memberIdFindService = new MemberIdFindService();
		MemberBean userInfo = memberIdFindService.findId(mem_email);
		
		
		if(userInfo == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 계정입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			//조회한 MemberBean객체에서 id를 얻어와 request영역에 u_id이름으로 얻어온 id값을 속성값으로 공유
			String mem_id = userInfo.getMem_id();
			request.setAttribute("mem_id", mem_id);
			//request영역에 showPage에 나타낼 user/findIdComplete.jsp뷰페이지를 설정하여
			request.setAttribute("showPage", "/findIdComplete.jsp");
			
			forward = new ActionForward("memberTemplate.jsp", false);//디스패치 방식으로 포워딩
		}
		return forward;
	}

}

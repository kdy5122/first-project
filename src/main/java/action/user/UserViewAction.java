package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserViewService;
import vo.ActionForward;
import vo.MemberBean;

public class UserViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/*현재 사용자가 로그인된 상태인지를 알아보기 위해서 session객체를 얻어옴
		 * session객체에 공유되어 있는 id속성값이 null이 아니면 로그인된 상태이다.		 *  
		 */
		HttpSession session = request.getSession();
		String viewId = (String)session.getAttribute("mem_id");
		
		if(viewId == null) {//현재 로그인된 상태가 아니면
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("location.href='loginForm.jsp';");
			out.println("</script>");
		}else {
			/*session영역에 공유된 id를 사용하여
			 * 해당 id의 사용자정보와 주소정보를 얻어온 후
			 * request객체에 속성값으로 공유시킴
			 */
			UserViewService userViewService = new UserViewService();
			MemberBean userInfo = userViewService.getUserInfo(viewId);
			
			request.setAttribute("user", userInfo);
			request.setAttribute("showPage", "/userView.jsp");
			
			forward = new ActionForward("userView.jsp", false);//디스패치 방식으로 포워딩
		}
		return forward;
	}

}






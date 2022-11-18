package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.Member.MemberDeleteAction;
import action.Member.MemberIdFindAction;
import action.Member.MemberJoinAction;
import action.Member.MemberListAction;
import action.Member.MemberPwFindAction;
import action.Member.MemberViewAction;
import action.user.UserDeleteAction;
import action.user.UserUpdateAction;
import action.user.UserViewAction;
import action.Member.MemberUpdateAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends javax.servlet.http.
HttpServlet
{
	static final long serialVersionUID = 1L;
	protected void doProcess(HttpServletRequest request,
	HttpServletResponse response)
	
	throws ServletException, IOException{
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward =null;
		Action action = null;
		
		if(command.equals("/memberJoin.me")) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/joinForm.jsp");
		
		
		}
		
		else if(command.equals("/memberJoinAction.me")) {//회원가입
			
			action=new MemberJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/memberListAction.me")) {//모든회원정보
			action = new MemberListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/memberViewAction.me")) {//회원1명 상세보기
			action = new MemberViewAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/memberUpdate.me")) {//'회원 정보 수정' 요청이면
			action  = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
			
		else if(command.equals("/memberDeleteAction.me")) {//회원삭제
			action = new MemberDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/memberIdFindForm.me")) {//'아이디 찾기 폼 보기' 요청이면
			request.setAttribute("showPage", "memberIdFind.jsp");//아이디 찾기 뷰페이지로
			forward = new ActionForward("memberTemplate.jsp",false);//포워딩함
		}

		else if(command.equals("/memberIdFindAction.me")) {//'아이디 찾기 처리' 요청이면
			action = new MemberIdFindAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/memberPwFindForm.me")) {//'비밀번호 찾기' 폼 보기 요청이면
			request.setAttribute("showPage", "memberPwFind.jsp");//비밀번호 찾기 뷰페이지로
			forward = new ActionForward("memberTemplate.jsp",false);//포워딩함
		}
		
		else if(command.equals("/memberPwFindAction.me")) {//'비밀번호 찾기 처리' 요청이면
			action = new MemberPwFindAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/userView.me")) {//'마이페이지' 요청이면
			action = new UserViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		}else if(command.equals("/userUpdate.me")) {//'마이페이지 정보 수정' 요청이면
			action  = new UserUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		/*---'회원탈퇴' 처리---------------------------------------------*/
		else if(command.equals("/userDelete.me")) {//'마이페이지 회원탈퇴' 요청이면
			action  = new UserDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		
		
		 if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				
			}
		}
	}
	
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
}

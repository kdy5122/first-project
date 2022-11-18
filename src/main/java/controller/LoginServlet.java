package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svc.LoginService;
import util.SHA256;
import vo.MemberBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = SHA256.encodeSHA256(request.getParameter("mem_pwd"));
		//String mem_pwd = request.getParameter("mem_pwd");
		
		
		LoginService loginService = new LoginService();
		MemberBean loginMember = loginService.getLoginMember(mem_id,mem_pwd);
		System.out.println("디버그test1");
		//로그인이 성공되면 Member객체가 넘어오고 실패하면 null이 넘어옴
		
		if(loginMember != null){
			HttpSession session = request.getSession();
			//session.setAttribute("loginMember", loginMember);
			session.setAttribute("mem_id", mem_id);
			session.setAttribute("mem_grade", loginMember.getMem_grade());
			response.sendRedirect("index.jsp");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}










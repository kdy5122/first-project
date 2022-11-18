package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.review.RevDeleteProAction;
import action.review.RevDetailViewAction;
import action.review.RevInsertProAction;
import action.review.RevListAction;
import action.review.RevUpdateFormAction;
import action.review.RevUpdateProAction;
import action.review.WriteAction;
import vo.ActionForward;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		String requestURI=request.getRequestURI();
		
		String contextPath=request.getContextPath();
		
		String command=request.getRequestURI().substring(contextPath.length());
		
		Action action = null;
		ActionForward forward =null;
		
		System.out.println("command:"+command);//어떤 요청인지 확인하기 위해 출력
		
		if(command.equals("/rev_boardList.do")) {//글목록과 페이지요청 함께처리
		
			
			action=new RevListAction();
			
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		
		
		}else if(command.equals("/boardWriteForm.do")) {//게시판목록에서 글작성하기 누르는 요청,
			action=new WriteAction();//글작성하기 위해 로그인 상태인지 확인
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		
		}else if(command.equals("/revInsertPro.do")) {//글 작성내용 입력후 글 등록요청
			action=new RevInsertProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}

		
			
		}else if(command.equals("/revDetailView.do")) {//글 등록후 작성한내용 상세보기 요청
			action=new RevDetailViewAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/revUpdateForm.do")) {//글 수정하기위해 해당글에 입력된정보들을 얻어오는 acton
			action=new RevUpdateFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/revUpdatePro.do")) {//글 수정 처리 요청-실질적
			action=new RevUpdateProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
		}else if(command.equals("/revDeletePro.do")) {//글 삭제처리요청
			action=new RevDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			

			
			
			
		}
		
		
		
		
		
		
		
		
		if(forward!=null) {
			if(forward.isRedirect()) { //true
				response.sendRedirect(forward.getPath()); //새요청으로 이동한다.
			}else { //false : 디스패치 - 기존요청 그대로 전달
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
		}
	
		}//if문 끝
		
		

		
		
		
		
		
		
		
	}
}

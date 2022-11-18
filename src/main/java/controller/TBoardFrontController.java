package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.tboard_action.TBoardDeleteProAction;
import action.tboard_action.TBoardDetailAction;
import action.tboard_action.TBoardListAction;
import action.tboard_action.TBoardModifyFormAction;
import action.tboard_action.TBoardModifyProAction;
import action.tboard_action.TBoardReplyFormAction;
import action.tboard_action.TBoardReplyModifyProAction;
import action.tboard_action.TBoardReplyProAction;
import action.tboard_action.TBoardWriteFormAction;
import action.tboard_action.TBoardWriteProAction;
import vo.ActionForward;

@WebServlet("*.bo")
public class TBoardFrontController extends javax.servlet.http.HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/tboardWriteForm.bo")){
			action=new TBoardWriteFormAction(); 
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
						
		}else if(command.equals("/tboardWritePro.bo")){
			action  = new TBoardWriteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/tboardList.bo")){
			action = new TBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		
		else if(command.equals("/tboardDetail.bo")){
			action = new TBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/tboardReplyForm.bo")){
			action = new TBoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/tboardReplyPro.bo")){
			action = new TBoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/tboardModifyForm.bo")){
			action = new TBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/tboardModifyPro.bo")){
			action = new TBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/tboardReplyModifyPro.bo")){
			action = new TBoardReplyModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}else if(command.equals("/tboardDeleteForm.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int tboard_num=Integer.parseInt(request.getParameter("tboard_num"));
			request.setAttribute("tboard_num",tboard_num);
			forward = new ActionForward("/tboard/tboard_delete.jsp",false);
		}
		else if(command.equals("/tboardDeletePro.bo")){
			action = new TBoardDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		/***************************포워딩****************************/
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath()); 
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}

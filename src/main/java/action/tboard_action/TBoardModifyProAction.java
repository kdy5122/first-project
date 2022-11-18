package action.tboard_action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;

public class TBoardModifyProAction implements action.Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		//본인이 아니면 수정버튼 나오지 않음 
		ActionForward forward = null;
		boolean isModifySuccess = false;
		
		
		TBoardBean article=null;
		TBoardModifyProService tboardModifyProService = new TBoardModifyProService();
		
		String realFolder="";
		String saveFolder="/tboardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		    article=new TBoardBean();
		    String nowPage = multi.getParameter("page");
		    int tboard_num=Integer.parseInt(multi.getParameter("TBOARD_NUM"));
	        article.setTBOARD_PLACELA(multi.getParameter("TBOARD_PLACELA"));
	        article.setTBOARD_PLACEMA(multi.getParameter("TBOARD_PLACEMA"));
			article.setTBOARD_NUM(tboard_num);
			
			article.setTBOARD_SUBJECT(multi.getParameter("TBOARD_SUBJECT"));
			article.setTBOARD_CONTENT(multi.getParameter("TBOARD_CONTENT")); 
		    
		 String tboard_file = multi.getFilesystemName("TBOARD_FILE");
		    article.setTBOARD_FILE(tboard_file);
		 String tboard_image=null;
		 
		 System.out.println("전달 확인 " + multi.getParameter("TBOARD_PIMAGE"));

		if(multi.getFilesystemName("TBOARD_IMAGE")!=null) {
		 tboard_image = multi.getFilesystemName("TBOARD_IMAGE");
	     article.setTBOARD_IMAGE(tboard_image);
		}else{
		 article.setTBOARD_IMAGE(multi.getParameter("TBOARD_PIMAGE"));
		}
		 


		 

		  
			isModifySuccess = tboardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{  
				forward = new ActionForward("tboardDetail.bo?tboard_num="+article.getTBOARD_NUM()+"&page="+nowPage,true);
			}

	

		return forward;
	}

}

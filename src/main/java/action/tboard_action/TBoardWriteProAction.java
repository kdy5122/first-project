package action.tboard_action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;

public class TBoardWriteProAction implements action.Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		
		ActionForward forward=null;
		TBoardBean tboardBean = null;
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
		tboardBean = new TBoardBean();
        tboardBean.setMEM_ID(multi.getParameter("loginID")); //세션에 있는 로그인한 아이디를 setMEM_ID에 set시켜준다.
		tboardBean.setTBOARD_SUBJECT(multi.getParameter("TBOARD_SUBJECT"));
		tboardBean.setTBOARD_CONTENT(multi.getParameter("TBOARD_CONTENT"));	
		
        
        String tboard_file = multi.getFilesystemName("TBOARD_FILE");
        
        String tboard_image = multi.getFilesystemName("TBOARD_IMAGE");
        
        tboardBean.setTBOARD_FILE(tboard_file);
        tboardBean.setTBOARD_IMAGE(tboard_image);
        
      

 
		
		/*
		 * tboardBean.setTBOARD_FILE(
		 * multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		 * 
		 * tboardBean.setTBOARD_IMAGE(
		 * multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		 */

		tboardBean.setTBOARD_PLACELA(multi.getParameter("TBOARD_PLACELA")); //set으로 위도값을 set시켜준다.
		tboardBean.setTBOARD_PLACEMA(multi.getParameter("TBOARD_PLACEMA")); //set으로 경도값을 set시켜준다.
		tboardBean.setTBOARD_ADDRESS(multi.getParameter("detailAddress"));
   
		
		TBoardWriteProService tboardWriteProService = new TBoardWriteProService();
		boolean isWriteSuccess = tboardWriteProService.registArticle(tboardBean);

		if(!isWriteSuccess){ //true가아니면 등록실패 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{ 
			forward = new ActionForward("tboardList.bo",true);
		}

		return forward;
		
	}  	
	
}

package action.review;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;

import svc.review.RevUpdateProService;
import vo.ActionForward;
import vo.ReviewBean;

public class RevUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=null;
		ReviewBean reviewBean=null;
		
		/* 그 전 단계에서 처리햇음
		 * //session 영역의 아이디를 얻어옴, 로그인 할 시에만 글 작성을 위해 HttpSession session =
		 * request.getSession();
		 * 
		 * String mem_id = (String)session.getAttribute("mem_id");
		 */
		
		
		

		String realFolder="";
		String saveFolder="/reviewUpload";
		int fileSize=10*1024*1024;
		

		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   

		 File file2 = new File(realFolder); if(!file2.exists()) { file2.mkdirs(); }
		
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		/*
		 * File file = new File(realFolder); if(!file.exists()) { file.mkdirs(); }
		 */
		
		

		String file=(String)multi.getFileNames().nextElement();//file타입 input태그의 name속성값들을 하나씩 가져옴
		
		 //서버에저장된이름 동일한이름등록시 ㄷ뒤에숫자..
		String origifileName =multi.getOriginalFileName(file);
		String fileName=multi.getFilesystemName(file);
		
		/*
		
		if(fileName!=null) {
			ParameterBlock pb = new ParameterBlock();	
			//★★ 운영체제 : 윈도우 / \ , 리눅스나 맥 /
			pb.add(realFolder + File.separator + fileName);//pb.add(imagePath+"/"+fileName);
			
			//"fileload"속성은 JAI가 제공하는 코덱을 사용
			RenderedOp rOp=JAI.create("fileload",pb);
			
			//BufferedImage : 이미지를 보관하는 장소
			//thumb : 이미지 버퍼를 생성하고 버퍼의 사이즈를 100x100로 설정
			BufferedImage bi = rOp.getAsBufferedImage();//원본
			BufferedImage thumb = new BufferedImage(350,350,BufferedImage.TYPE_INT_RGB);
			
			//thumb의 이미지 버퍼에 원본 이미지를 벙해진 버퍼 사이즈인 100X100로 맞추어 드로우한다.
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi,0,0,350,350,null);
			
			//출력할 위치와 파일이름을 설정하고 썸네일 이미지를 생성
			File thumbnailfile = new File(realFolder + File.separator + "sm_" + fileName);//File file=new File(imagePath+"/sm_"+filename);
			//저장하는 타입을 jpg로 설정.
			ImageIO.write(thumb,"jpg",thumbnailfile);
			
		}
		
		/*****************************************/
	
		
		
		
		
		
		//폼형식에서 받아올수있는 값들, 세션에저장된 id, 별점, 내용, 파일,
		
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		
		reviewBean=new ReviewBean();
		reviewBean.setBoard_no(board_no);//게시글번호가 그 해당 글번호를 찾아서 수정
		reviewBean.setMem_id(multi.getParameter("mem_id"));//수정할 항목아니니 굳이...?
		reviewBean.setRev_content(multi.getParameter("rev_content"));
		reviewBean.setRev_score(multi.getParameter("rev_score"));
		
		if(fileName!=null) {
		reviewBean.setRev_fileName(fileName);
		reviewBean.setRev_origfileName(origifileName);
		}
		

		
		RevUpdateProService revUpdateProService=new RevUpdateProService();
		boolean isUpdateSuccess = revUpdateProService.updateReview(reviewBean);
		
		if(!isUpdateSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward("revDetailView.do?board_no="+board_no, false);
		}
		
		
		
		return forward;
	}
}
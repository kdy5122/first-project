package action.review;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.review.RevDetailViewService;
import vo.ActionForward;
import vo.ReviewBean;

public class RevDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		
		RevDetailViewService revDetailViewService=new RevDetailViewService();
		ReviewBean article= revDetailViewService.getArticle(board_no);
		
		
		
		
		
		

		request.setAttribute("article", article);//el 언어사용할ㄸ'ㅐ 속성명..
		

		ActionForward forward = new ActionForward("/review/review_board_detail.jsp", false);
		return forward;
	}

}

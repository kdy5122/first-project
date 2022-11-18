package action.product;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.product.ProdInsertService;
import svc.product.ProductModifyProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;
  	ProductBean productBean=null;
	
	ServletContext context = request.getServletContext();
	String realFolder = context.getRealPath("/productUpload");
	int fileSize=10*1024*1024;
	
	/*리뷰등록과 RevInsertPro 유사 - 상품이미지는 썸네일 이용하지 않음*/
	
	/*관리자 아이디가 맞는지 세션에서 확인작업?*/
	
	
	 File file2 = new File(realFolder); if(!file2.exists()) { file2.mkdirs(); }
	 
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		
		
		
		  String file=(String)multi.getFileNames().nextElement();
		  String fileName=multi.getFilesystemName(file);
		 
		
			/* String fileName=multi.getOriginalFileName("prod_image"); */
		
		
		String prod_name=multi.getParameter("prod_name");
		
		int prod_id = Integer.parseInt(multi.getParameter("prod_id"));
		
		String prod_kind=multi.getParameter("prod_kind");
		int prod_price=Integer.parseInt(multi.getParameter("prod_price"));
		int prod_amount=Integer.parseInt(multi.getParameter("prod_amount"));
		String prod_content=multi.getParameter("prod_content");
		String prod_status=multi.getParameter("prod_status");
		
		/*
		if(fileName!=null) {
		  ParameterBlock pb = new ParameterBlock(); //★★ 운영체제 : 윈도우 / \ , 리눅스나 맥 /
		  pb.add(realFolder + File.separator + fileName);//pb.add(imagePath+"/"+fileName);
		  
		  //"fileload"속성은 JAI가 제공하는 코덱을 사용 RenderedOp rOp=JAI.create("fileload",pb);
		  RenderedOp rOp=JAI.create("fileload",pb);
		  
		  //BufferedImage : 이미지를 보관하는 장소 //thumb : 이미지 버퍼를 생성하고 버퍼의 사이즈를 100x100로 설정
		  BufferedImage bi = rOp.getAsBufferedImage();//원본 BufferedImage thumb = new
		 
		  BufferedImage thumb = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		  //thumb의 이미지 버퍼에 원본 이미지를 벙해진 버퍼 사이즈인 로 맞추어 드로우한다. Graphics2D g =
		  Graphics2D g = thumb.createGraphics();
			g.drawImage(bi,0,0,500,500,null);
		  
		  //출력할 위치와 파일이름을 설정하고 썸네일 이미지를 생성 
			File thumbnailfile = new File(realFolder + File.separator + "sm_" + fileName);//저장하는 타입을 jpg로 설정.
		  ImageIO.write(thumb,"jpg",thumbnailfile);
		}
		
		*/

		//자동증가되는 상품id, sysdate() 등록일은 제외하고 나머지는 폼에서 받아온값들
		 productBean=new ProductBean();
		 
		productBean.setProd_name(prod_name);
		productBean.setProd_kind(prod_kind);
		productBean.setProd_price(prod_price);
		productBean.setProd_amount(prod_amount);
		productBean.setProd_content(prod_content);
		
		productBean.setProd_image(fileName);
		productBean.setProd_status(prod_status);
		productBean.setProd_id(prod_id);	

		ProductModifyProService prodUpdateService = new ProductModifyProService();
		boolean isAddProdSuccess = prodUpdateService.updateProd(productBean);
	
		
	
			
			if(!isAddProdSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패')");
				out.println("history.back();");
				out.println("</script>");
			}
			else{

				forward=new ActionForward("/productListView.po?prod_kind="+prod_kind,false);// 상품리스트 보여주기 요청

			}
		
		
      
      
		
		return forward;
	}

}

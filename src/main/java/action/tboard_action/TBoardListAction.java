package action.tboard_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.tboard_svc.*;
import vo.ActionForward;
import vo.TBoardBean;
import vo.PageInfo;

 public class TBoardListAction implements action.Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionForward forward = null;
		ArrayList<TBoardBean> articleList=new ArrayList<TBoardBean>(); //각 페이지당 출력될 전체 글 목록을 저장할 ArrayList 객체를 생성하는 부분.(list.jsp에서 출력될 전체글 목록 저장 배열객체)
	  	int page=1; //목록보기 요청에서 출력될 페이지의 기본값으로 1페이지를 설정하는 부분 (veiw.jsp)
		int limit=10; //한 페이지당 출력될 글의 개수를 10개로 설정하는 부분이다. (list.jsp)
		
		//목록보기에 출력될 페이지가 파라미터로 전송된 경우 page 변수의 값을 변경하는 부분. 목록 보기 페이지에서 조회할 페이지
		//번호를 클릭하고 요청한 경우는 페이지 번호가 파리미터로 전송되어 온다.

		//list.jsp에서 [이전][다음]이나 숫자 클릭했을 경우 page값 저
		try {
			if(request.getParameter("page")!=null){ 
							page=Integer.parseInt(request.getParameter("page"));				
			}
		} catch (NumberFormatException e) {
          System.out.println(e.getMessage());
		}
		
		//받아온 page값이 없으면 기본값 1페이지 
		

		TBoardListService tboardListService = new TBoardListService();
		int listCount=tboardListService.getListCount(); //총 글의 개수를 반환하는 메소드를 호출하는 부분. 
		articleList = tboardListService.getArticleList(page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.  // 총 페이지를 수를 계산하는 부분.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		forward= new ActionForward();
		

   		forward.setPath("/tboard/tboard_list.jsp");
   		
   		return forward;
   		
	 }

 }

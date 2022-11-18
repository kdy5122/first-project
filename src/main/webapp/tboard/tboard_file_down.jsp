<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.TBoardBean" %>    
<%-- <%@ page import = "java.io.File"%> --%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.net.URLEncoder"%>

<%
//1. 파라미터로 전송된 다운로드할 파일이름(중복 방지 처리한 파일명:a1.txt)을 얻어와
	String fileName = request.getParameter("downFile"); //a1.txt

//2. 업로드한 폴더의 위치와 업로드 폴더의 이름을 알아야 한다.
	String savePath = "tboardUpload"; //서버에 파일이 업로드된 폴더명 지정
	//이 때, 절대경로 기준의 진짜 경로를 얻어와야 한다.
	ServletContext context = request.getServletContext();
	String sDownloadPath = context.getRealPath(savePath); //예)C:\\test\\upload

//3. 서버에 저장되어 있는 "폴더경로\저장된 파일명"으로 "풀path"를 만들어준다.	
	//주석된 코드의 문제 : 윈도우와 다르게 UNIX, Linux 같은 OS들은 경로 입력 방식에 차이가 있으므로 문제가 생길 수 있음.
	//String sFilePath = sDownloadPath + "\\" + fileName;	//예)C:\\test\\upload\\a1.txt
	
	//★★ 윈도우 \  / , 리눅스 /로 표시해줌
	String sFilePath = sDownloadPath + File.separator + fileName;

//4. 풀path에 대한 것을 파일객체로 인식시킨다.
	File file = new File(sFilePath);

//5. 한번에 읽고 출력할 바이트 크기로 배열 생성 -> 12-2에서 사용됨
//저장된 파일을 읽어와 저장할 버퍼를 임시로 만들고 버퍼의 용량은 이전에 한번에 업로드할 수 있는 파일크기로 지정한다.
	byte b[] = new byte[10*1024*1024];	//10M

//6. 풀path로 FileInputStream객체 생성 => 교재방식
	//FileInputStream in = new FileInputStream(sFilePath);

//6. file로 FileInputStream객체 생성 => 12-2에서 사용됨
	FileInputStream in = new FileInputStream(file);

//7. 유형 확인 : 읽어올 경로의 파일유형 -> 페이지 생성할 때 타입을 설정해야 한다.
//다운로드할 파일의 MIME타입을 얻어와(MIME타입 : image, html, txt...)
//※ Multipurpose Internet Mail Extensions
	String sMimeType = getServletContext().getMimeType(sFilePath);
	System.out.println("sMimeType(Mime타입 유형)>>>" + sMimeType);

//8. 지정되지 않은 MIME타입 유형은 예외 발생
	if (sMimeType == null) { //다운로드할 파일의 MIME타입이 제대로 반환되지 않으면
		sMimeType = "application/octet-stream";	//기본 MIME타입으로 이진파일을 위한 기본값으로 지정
	}

//9. 파일다운로드 시작-유형을 지정해준다.
	//응답할 데이터의 MIME타입을 다운로드할 파일의 MIME타입으로 지정
	response.setContentType(sMimeType); //원래는 "text/html"인데 -> 다운로드할 파일의 MIME타입으로 수정

//10. 브라우저에 따라 업로드 파일의 제목이 깨질 수 있으므로 인코딩을 해준다.
	//MS사의 IE이거나 Trident이면 다운로드 시
	String agent = request.getHeader("User-Agent");
	boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
	
	String filenameEncoding;
	//한글 파일명이 깨지지 않도록 "UTF-8"로 처리 후 공백부분이 "+"문자로 변경되므로 다시 공백문자(%20)으로 변경해 줘야 함"
	if (ieBrowser) {
		filenameEncoding = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	} else {	////MS사의 IE이거나 Trident이 아니면(예, 크롬이면) 한글 파일명이 깨지지 않도록 파일명을 UTF-8 인코딩 형태의 바이트로 받아서 "ISO-8859-1"로 문자열 생성해줘야 함
		filenameEncoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	}

	/*
	UTF-8 은 모든 유니코드 문자를 나타낼 수 있는 멀티 바이트 인코딩이다.(가변길이 인코딩)
	"ISO-8859-1"은 첫 256개의 유니코드 문자를 나타낼 수 있는 1바이트 인코딩이다.(1바이트 고정길이 인코딩)
	*/
	
//11. 웹 브라우저에서 해석되어 실행되는 파일들(jpg, html 등)도 다운로드 박스가 출력되도록 처리하는 방법
	//헤더 정보 설정 시 "Content-Disposition"값을 "attachment"로 설정하면 모든 파일에 대해서 다운로드 박스가 실행됨
	response.setHeader("Content-Disposition", "attachment; filename= " + filenameEncoding);

//12. 브라우저에 쓰기-------------------------------------------------------------------------
     out.clear();
     out=pageContext.pushBody();
     // getOutputStream()를 호출하는 과정에서 에러발생 해결 
     
     
	//12-1. 파일 다운로드 역할을 하는 "바이트 기반 출력 스트림 객체"를 생성
	ServletOutputStream out2 = response.getOutputStream(); //주의 : out은 이미 내장객체로 존재하므로
	
	/*
	12-2. 바이트 배열 b의 인덱스 0부터 한번에 최대 b.length만큼 읽어온다.
	이 때, 파일이 위치한 곳에 연결된 FileInputStream(=in)에서 읽되 끝(-1) 전까지 while문을 돈다.
	*/
	int numRead;

	while ((numRead = in.read(b, 0, b.length)) != -1) { //12-3. 읽어올 게 더 이상 없으면 -1을 리턴하면서 while문을 빠져나감
		//12-4. 브라우저에 출력
		out2.write(b, 0, numRead); //b배열에 있는 데이터의 0번째부터 최대 numRead만큼 출려한다.
	}

//13. 자원해제
	out2.flush(); //버퍼 비움
	out2.close();
	
	in.close();
%>

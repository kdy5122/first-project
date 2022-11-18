/**vo=DTO
 * 컨트롤러에서 클라이언트의 각 요청을 받아서 처리한 후 
 * 최종적으로 뷰 페이지(jsp)로 포워딩 처리시
 * 이동할 뷰 페이지의 URL과 포워딩방식(디스패치나 리다이렉트)이 필요하다.
 * 이 두 정보를 편리하게 다루기 위해 ActionForward 클래스를 설계
 * **/

package vo;

public class ActionForward {
	
	//이동할 뷰 페이지의 URL
	private String path; //기본값 null
	
	//포워딩방식(디스패치나 리다이렉트) : false 이면 dispatch(기존요청), true 이면 redirect(새요청) 방식으로 요청하겠다.
	private boolean redirect; //기본값 false를 인지하고있어야함 

	
	//매개변수가 없는 기본생성자- 밑의 매개변수가 있는생성자가 있으므로 기본생성자 수동으로 만들어야함.(생성자가 하나도 없을시에 컴파일러가 기본생성자를 추가해주기때문에)
	public ActionForward() {}
	
	
	
	//매개변수가 있는 생성자
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}
	
	
	public String getPath() {
		return path;
	}
	
	

	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	

	
}
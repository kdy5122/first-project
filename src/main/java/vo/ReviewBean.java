package vo;

public class ReviewBean {
	private int board_no;
	private int prod_id;
	private String mem_id;
	private String rev_content;
	private String rev_score;
	private String rev_date;
	private String rev_fileName;
	private String rev_origfileName;

	
	public int getBoard_no() {
		return board_no;
	}
	
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	
	
	
	
	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	public String getRev_score() {
		return rev_score;
	}
	public void setRev_score(String rev_score) {
		this.rev_score = rev_score;
	}
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}

	public String getRev_fileName() {
		return rev_fileName;
	}

	public void setRev_fileName(String rev_fileName) {
		this.rev_fileName = rev_fileName;
	}

	public String getRev_origfileName() {
		return rev_origfileName;
	}

	public void setRev_origfileName(String rev_origfileName) {
		this.rev_origfileName = rev_origfileName;
	}


	
	
	
}

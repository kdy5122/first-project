package vo;

import java.sql.Date;

public class QBoardBean {
	
	private int QBOARD_NUM;

	private String MEM_ID;
	private String QBOARD_SUBJECT;
	private String QBOARD_CONTENT;
	private int QBOARD_RE_REF;
	private int QBOARD_RE_LEV;
	private int QBOARD_RE_SEQ;
	private int QBOARD_READCOUNT;
	private Date QBOARD_DATE;


	public int getQBOARD_NUM() {
		return QBOARD_NUM;
	}
	public void setQBOARD_NUM(int qBOARD_NUM) {
		QBOARD_NUM = qBOARD_NUM;
	}
	public String getMEM_ID() {
		return MEM_ID;
	}
	public void setMEM_ID(String mEM_ID) {
		MEM_ID = mEM_ID;
	}
	public String getQBOARD_SUBJECT() {
		return QBOARD_SUBJECT;
	}
	public void setQBOARD_SUBJECT(String qBOARD_SUBJECT) {
		QBOARD_SUBJECT = qBOARD_SUBJECT;
	}
	public String getQBOARD_CONTENT() {
		return QBOARD_CONTENT;
	}
	public void setQBOARD_CONTENT(String qBOARD_CONTENT) {
		QBOARD_CONTENT = qBOARD_CONTENT;
	}
	public int getQBOARD_RE_REF() {
		return QBOARD_RE_REF;
	}
	public void setQBOARD_RE_REF(int qBOARD_RE_REF) {
		QBOARD_RE_REF = qBOARD_RE_REF;
	}
	public int getQBOARD_RE_LEV() {
		return QBOARD_RE_LEV;
	}
	public void setQBOARD_RE_LEV(int qBOARD_RE_LEV) {
		QBOARD_RE_LEV = qBOARD_RE_LEV;
	}
	public int getQBOARD_RE_SEQ() {
		return QBOARD_RE_SEQ;
	}
	public void setQBOARD_RE_SEQ(int qBOARD_RE_SEQ) {
		QBOARD_RE_SEQ = qBOARD_RE_SEQ;
	}
	public int getQBOARD_READCOUNT() {
		return QBOARD_READCOUNT;
	}
	public void setQBOARD_READCOUNT(int qBOARD_READCOUNT) {
		QBOARD_READCOUNT = qBOARD_READCOUNT;
	}
	public Date getQBOARD_DATE() {
		return QBOARD_DATE;
	}
	public void setQBOARD_DATE(Date qBOARD_DATE) {
		QBOARD_DATE = qBOARD_DATE;
	}
	
	

	
}

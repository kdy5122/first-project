package vo;

import java.util.Date;

public class OrderBean {
	private String order_id;
	private String mem_id;
	private String o_name;
	private String o_phone;
	private String o_email;
	private String o_address1;
	private String o_address2;
	private String o_address3;
	private Date o_date;
	private String o_status;
	private int totalPrice;
	private int usePoint;
	private String o_require;
	
	
	
	

	public OrderBean() {
		super();
	}
	
	
	public OrderBean(String order_id, String mem_id, Date o_date, String o_status, int totalPrice) {
		super();
		this.order_id = order_id;
		this.mem_id = mem_id;
		this.o_date = o_date;
		this.o_status = o_status;
		this.totalPrice = totalPrice;
	}
	
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public String getO_email() {
		return o_email;
	}
	public void setO_email(String o_email) {
		this.o_email = o_email;
	}
	public String getO_address1() {
		return o_address1;
	}
	public void setO_address1(String o_address1) {
		this.o_address1 = o_address1;
	}
	public String getO_address2() {
		return o_address2;
	}
	public void setO_address2(String o_address2) {
		this.o_address2 = o_address2;
	}
	public String getO_address3() {
		return o_address3;
	}
	public void setO_address3(String o_address3) {
		this.o_address3 = o_address3;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public String getO_status() {
		return o_status;
	}
	public void setO_status(String o_status) {
		this.o_status = o_status;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getO_require() {
		return o_require;
	}
	public void setO_require(String o_require) {
		this.o_require = o_require;
	}


	public int getUsePoint() {
		return usePoint;
	}


	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}


	

}

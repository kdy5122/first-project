package vo;

public class OrderDetail {

	
	private int order_detailNum;
	private String order_id;
	private int prod_id;
	private int prod_price;
	private int o_amount;
	private String prod_name;
	
	
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getOrder_detailNum() {
		return order_detailNum;
	}
	public void setOrder_detailNum(int order_detailNum) {
		this.order_detailNum = order_detailNum;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}

	
	
	
	
	
}

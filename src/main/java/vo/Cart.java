package vo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Cart implements Serializable {
	
	private int prod_id;
	private String prod_name; //상품명
	private int qty; //수량
	private int price; //가격
	private String image; //상품이미지
	private int totalMoney;
	private String encoding_prod_name;

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEncoding_prod_name() {
		try {
			encoding_prod_name = URLEncoder.encode(prod_name, "utf-8");
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return encoding_prod_name;
	}


	public int getTotalMoney() {
		return totalMoney;
	}

	
	public void setTotalMoney(int price,int qty) {
		
		this.totalMoney=price*qty;
		
			
	}
	
	

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	
	
	
	
}

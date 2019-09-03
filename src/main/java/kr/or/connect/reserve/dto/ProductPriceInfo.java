package kr.or.connect.reserve.dto;

public class ProductPriceInfo {
	private int id;							
	private char price_type_name;
	private String price_type_real_name;
	private int price;
	private int discount_rate;
	private String create_date;
	private String modify_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getPrice_type_name() {
		return price_type_name;
	}
	public void setPrice_type_name(char price_type_name) {
		this.price_type_name = price_type_name;
	}
	public String getPrice_type_real_name() {
		return price_type_real_name;
	}
	public void setPrice_type_real_name(String price_type_real_name) {
		this.price_type_real_name = price_type_real_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	@Override
	public String toString() {
		return "ProductPriceInfo [id=" + id + ", price_type_name=" + price_type_name + ", price_type_real_name="
				+ price_type_real_name + ", price=" + price + ", discount_rate=" + discount_rate + ", create_date="
				+ create_date + ", modify_date=" + modify_date + "]";
	}

	
}

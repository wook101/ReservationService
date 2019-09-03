package kr.or.connect.reserve.dto;

import java.util.Date;

public class ReservationListInfo {
	private int id;
	private int product_id;
	private int display_info_id;
	private String product_name;
	private String reservation_name;
	private String reservation_tel;
	private String reservation_email;
	private Date reservation_date;
	private int cancel_flag;
	private Date create_date;
	private Date modify_date;
	private int reservation_total_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getDisplay_info_id() {
		return display_info_id;
	}
	public void setDisplay_info_id(int display_info_id) {
		this.display_info_id = display_info_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getReservation_name() {
		return reservation_name;
	}
	public void setReservation_name(String reservation_name) {
		this.reservation_name = reservation_name;
	}
	public String getReservation_tel() {
		return reservation_tel;
	}
	public void setReservation_tel(String reservation_tel) {
		this.reservation_tel = reservation_tel;
	}
	public String getReservation_email() {
		return reservation_email;
	}
	public void setReservation_email(String reservation_email) {
		this.reservation_email = reservation_email;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getCancel_flag() {
		return cancel_flag;
	}
	public void setCancel_flag(int cancel_flag) {
		this.cancel_flag = cancel_flag;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public int getReservation_total_price() {
		return reservation_total_price;
	}
	public void setReservation_total_price(int reservation_total_price) {
		this.reservation_total_price = reservation_total_price;
	}
	
	@Override
	public String toString() {
		return "ReservationListInfo [id=" + id + ", product_id=" + product_id + ", display_info_id=" + display_info_id
				+ ", product_name=" + product_name + ", reservation_name=" + reservation_name + ", reservation_tel="
				+ reservation_tel + ", reservation_email=" + reservation_email + ", reservation_date="
				+ reservation_date + ", cancel_flag=" + cancel_flag + ", create_date=" + create_date + ", modify_date="
				+ modify_date + ", reservation_total_price=" + reservation_total_price + "]";
	}
	
	
	
}

package kr.or.connect.reserve.dto;

public class ReservationUserFormVo {
	private String reservation_email;
	private String reservation_user;
	private String reservation_tel;
	private int reservation_total_price;
	public String getReservation_email() {
		return reservation_email;
	}
	public void setReservation_email(String reservation_email) {
		this.reservation_email = reservation_email;
	}
	
	public String getReservation_user() {
		return reservation_user;
	}
	public void setReservation_user(String reservation_user) {
		this.reservation_user = reservation_user;
	}
	public String getReservation_tel() {
		return reservation_tel;
	}
	public void setReservation_tel(String reservation_tel) {
		this.reservation_tel = reservation_tel;
	}
	
	public int getReservation_total_price() {
		return reservation_total_price;
	}
	public void setReservation_total_price(int reservation_total_price) {
		this.reservation_total_price = reservation_total_price;
	}
	
	@Override
	public String toString() {
		return "ReservationUserFormVo [reservation_email=" + reservation_email + ", reservation_user="
				+ reservation_user + ", reservation_tel=" + reservation_tel + ", reservation_total_price="
				+ reservation_total_price + "]";
	}
	


	
}

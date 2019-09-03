package kr.or.connect.reserve.dto;

import java.util.Date;

public class ReservationUserCommentInfo {
	private int id;
	private int product_id;
	private int reservation_info_id; 
	private int score;  
	private String comment;                   
	private Date create_date;          
	private Date modify_date;
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
	public int getReservation_info_id() {
		return reservation_info_id;
	}
	public void setReservation_info_id(int reservation_info_id) {
		this.reservation_info_id = reservation_info_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	@Override
	public String toString() {
		return "ReservationUserCommentInfo [id=" + id + ", product_id=" + product_id + ", reservation_info_id="
				+ reservation_info_id + ", score=" + score + ", comment=" + comment + ", create_date=" + create_date
				+ ", modify_date=" + modify_date + "]";
	}
	
	
}

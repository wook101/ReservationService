package kr.or.connect.reserve.dto;

public class ReviewWriteFromVo {
	private int product_id;
	private int reservation_info_id;
	private int score;
	private String comment;
	
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
	
	@Override
	public String toString() {
		return "ReviewWriteFromVo [product_id=" + product_id + ", reservation_info_id=" + reservation_info_id
				+ ", score=" + score + ", comment=" + comment + "]";
	}
	
}

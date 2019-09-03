package kr.or.connect.reserve.dto;

public class DetailCommentList {
	private int reservation_info_id;
	private String description;
	private String score;
	private String comment;
	private String email;
	private String modify_date;
	
	public int getReservation_info_id() {
		return reservation_info_id;
	}
	public void setReservation_info_id(int reservation_info_id) {
		this.reservation_info_id = reservation_info_id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	@Override
	public String toString() {
		return "DetailCommentList [reservation_info_id=" + reservation_info_id + ", description=" + description
				+ ", score=" + score + ", comment=" + comment + ", email=" + email + ", modify_date=" + modify_date
				+ "]";
	}

	
	
	
}

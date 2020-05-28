package kr.or.connect.reserve.dto;

public class ReserveProduct {
	private int id;
	private String description;//상품 제목
	private String imgPath;  //이미지 파일 경로
	private String place_name; //장소
	private String opening_hours; // 행사 시간
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}
	
	@Override
	public String toString() {
		return "ReserveProductInfo [id=" + id + ", description=" + description + ", imgPath=" + imgPath
				+ ", place_name=" + place_name + ", opening_hours=" + opening_hours + "]";
	}
	
	
	
	

}

package kr.or.connect.reserve.dto;

public class PromotionImage {
	private Long id;
	private String imgPath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	@Override
	public String toString() {
		return "PromotionImages [id=" + id + ", imgPath=" + imgPath + "]";
	}
	
	
}

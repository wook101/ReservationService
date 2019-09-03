package kr.or.connect.reserve.dto;

public class DetailTitleInfo {
	private Long id;
	private String imgPath;
	private String description;
	private String content;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "DetailTitleInfo [id=" + id + ", imgPath=" + imgPath + ", description=" + description + ", content="
				+ content + "]";
	}
	
}

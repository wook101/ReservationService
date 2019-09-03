package kr.or.connect.reserve.dto;

import java.util.Date;

public class ProductInfo {
	private int id;   
	private String description; 
	private String content;
	private String event;     
	private int category_id ;
	private Date create_date ;
	private Date modify_date;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
		return "ProductInfo [id=" + id + ", description=" + description + ", content=" + content + ", event=" + event
				+ ", category_id=" + category_id + ", create_date=" + create_date + ", modify_date=" + modify_date
				+ "]";
	}
	
}

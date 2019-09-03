package kr.or.connect.reserve.dto;

public class ComeRouteInfo {
	private int id;
	private String description;
	private String place_lot;
	private String place_street;
	private String place_name;
	private String tel;
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
	public String getPlace_lot() {
		return place_lot;
	}
	public void setPlace_lot(String place_lot) {
		this.place_lot = place_lot;
	}
	public String getPlace_street() {
		return place_street;
	}
	public void setPlace_street(String place_street) {
		this.place_street = place_street;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "ComeRouteInfo [id=" + id + ", description=" + description + ", place_lot=" + place_lot
				+ ", place_street=" + place_street + ", place_name=" + place_name + ", tel=" + tel + "]";
	}
	
	
}

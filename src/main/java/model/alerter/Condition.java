package model.alerter;

public class Condition {
	private String id;
	private String title;
	private String type;
	private String user_id;
	private String start_date;
	private String hit_count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getHit_count() {
		return hit_count;
	}
	public void setHit_count(String hit_count) {
		this.hit_count = hit_count;
	}
	

}

package org.jackysoft.edu.formbean;

import org.jackysoft.edu.entity.UserEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEventBody {
	private String id;
	private String title;
	private String url;
	
	
	private String className = "event-important";
	private long start;
	private long end;
	
	
	
	public UserEventBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEventBody(UserEvent ue) {
		this.id = ue.getId();
		this.title = ue.getName();
		this.url = String.format("/userevent/contentof/%s",ue.getId());
		this.className = ue.getClassName();
		this.start = ue.getStarttime();
		this.end   = ue.getEndtime();
		
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonProperty("class")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	
	
	
}

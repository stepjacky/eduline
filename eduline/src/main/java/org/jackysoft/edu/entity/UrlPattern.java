package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;


@Table(label="资源")
public class UrlPattern {
	
	@Column(id=true,label="URL模式",idStrategy=IdStrategy.MANUAL)
	@NotNull
	private String urlPattern;
	@Column(label="名称",query=true)
	@NotNull
	private String name;

	
	public UrlPattern() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public UrlPattern(String urlPattern, String name) {
		super();
		this.urlPattern = urlPattern;
		this.name = name;
	}




	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

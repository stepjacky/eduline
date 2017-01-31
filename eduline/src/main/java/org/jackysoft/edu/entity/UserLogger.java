package org.jackysoft.edu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity
@Table(label = "用户日志")
public class UserLogger extends NoEntity{

	@Property
	private String username;
	@Property
	private String nickname;
	@Property
	private long firetime;
	@Property
	private String action;
	@Property
	private String ipaddr;	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getFiretime() {
		return firetime;
	}

	public void setFiretime(long firetime) {
		this.firetime = firetime;
	}	

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	
	
}

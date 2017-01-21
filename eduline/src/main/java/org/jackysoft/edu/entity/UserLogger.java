package org.jackysoft.edu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;


@Table(label = "用户日志")
public class UserLogger {
	
	@Column(id=true,idStrategy=IdStrategy.UUID)
	private String id;
	
	@Column
	private String username;
	@Column
	private String nickname;
	@Column
	private long firetime;
	@Column
	private String action;
	@Column
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

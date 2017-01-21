package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.OptionText;
import org.jackysoft.edu.annotation.OptionValue;
import org.jackysoft.edu.annotation.Table;
import org.springframework.security.core.GrantedAuthority;


@Table(label="权限")
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7185445239154389638L;

	@Column(id=true,label="编号",idStrategy=IdStrategy.SECURITY_ROLE,list=false,input=false)
	@OptionValue
	@NotNull
	private String authority;
	@Column(label="名称",query=true)
	@OptionText
	@NotNull
	private String name;
	
	
	
	
	
	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Authority(String authority, String name) {
		super();
		this.authority = authority;
		this.name = name;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setAuthority(String authority) {
		this.authority = authority;
	}




	@Override
	public String getAuthority() {
		return authority;
	}

}

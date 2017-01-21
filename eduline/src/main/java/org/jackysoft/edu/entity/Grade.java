package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.OptionText;
import org.jackysoft.edu.annotation.OptionValue;
import org.jackysoft.edu.annotation.Table;

@Table(label="年级")
public class Grade {
	
	
	@OptionValue
	@Column(id=true,label="编号",idStrategy=IdStrategy.MANUAL,list=false,input=false)
    private int id;
    
	@OptionText
	@Column(label="名称",query=true)
	@NotNull
	private String name;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
    
	
}

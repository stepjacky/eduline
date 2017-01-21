package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.OptionText;
import org.jackysoft.edu.annotation.OptionValue;
import org.jackysoft.edu.annotation.Table;


@Table(label="课程")
public class Course{
	
	public static final String COURSE_TYPE_MASTER="0";
    public static final String COURSE_TYPE_SLAVER="1";
    public static final String COURSE_TYPE_OTHER="2";
	
	@Column(id=true,label="编号",idStrategy=IdStrategy.AUTOINC,list=false,input=false)
	@OptionValue
	private int id;
	
	@Column(label="名称",query=true)
	@OptionText
	@NotNull
	private String name;	
	
	@Column(label="类别",defaultValue="0")
	private int ctype;
	
	
	
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
	
	
	/**0主课,1 副科  2次课*/
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;		
	}
	
	
}

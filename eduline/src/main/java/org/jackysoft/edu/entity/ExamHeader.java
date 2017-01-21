package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;

public class ExamHeader {
	
	@Column(idStrategy = IdStrategy.UUID)
	private String id;

	@Column(id=true)
	private int inyear;
	
	@Column(id=true)
	private int grade;

	@Column(id = true)
	private int semester;

	// 某次月考
	@Column(id = true)
	private int monthly;
	
	// 某课程
	@Column(id=true)
	private int course;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getInyear() {
		return inyear;
	}

	public void setInyear(int inyear) {
		this.inyear = inyear;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getMonthly() {
		return monthly;
	}

	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}
	
	
	
	
    		
}

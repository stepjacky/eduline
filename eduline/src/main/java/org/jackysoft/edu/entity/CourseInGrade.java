package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;

@Table(label="年级课程设置")
public class CourseInGrade {
	
	
	@Column(idStrategy=IdStrategy.UUID)
	private String id;
    
	@Column(id=true,idStrategy=IdStrategy.MANUAL)
	private int inyear;
	
	@Column(id=true,idStrategy=IdStrategy.MANUAL)
	private int grade;
	@Column
	private String gradeName;
	
	@Column(id=true,idStrategy=IdStrategy.MANUAL)
	private int course;
	
	@Column
	private String courseName;

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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	

}

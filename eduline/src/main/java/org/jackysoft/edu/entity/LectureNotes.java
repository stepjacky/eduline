package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.AutoValue;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;

@Table(label="教师讲义")
public class LectureNotes {
	
	
	@Column(id=true,idStrategy = IdStrategy.UUID, list = false, input = false)
	private String id;
	
	@Column(label="课程")
	private String course;
	@Column(label="课程名称")
	private String courseName;
	
	@Column(label="年级")
	private String grade;
	
	@Column(label="年级名称")
	private String gradeName;
	
	@Column(label="所有人",autoValue=AutoValue.CURRENT_USERNAME)
	private String owner;
	@Column(label="所有人名称",autoValue=AutoValue.CURRENT_USERNICK)
    private String ownerName;	
	
	@Column(label="名称")
	private String name;
	
			
	@Column(label="发布日期",autoValue=AutoValue.CURRENT_TIMEMILLIS)
	private long firetime;

	private int shared =  0;
	
	private String sharedFile;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getFiretime() {
		return firetime;
	}

	public void setFiretime(long firetime) {
		this.firetime = firetime;
	}

	public int getShared() {
		return shared;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}

	public String getSharedFile() {
		return sharedFile;
	}

	public void setSharedFile(String sharedFile) {
		this.sharedFile = sharedFile;
	}

	
	
	
	
	
	
	
}

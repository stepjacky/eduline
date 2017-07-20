package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.mongodb.morphia.annotations.Embedded;


@Table(label = "课程章节")
public class Chapter extends NoEntity {

	//名称
	private String name;

	//父章节
	private String parent;

	//课本
	private String textbook;

	//课程
	int course;

	//年级
	int grade;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}


	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}
}
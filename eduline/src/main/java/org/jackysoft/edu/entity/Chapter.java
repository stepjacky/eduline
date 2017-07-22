package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import java.util.ArrayList;
import java.util.List;


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

	long timemillis;

	@Transient
	List<Chapter> children = new ArrayList<>();


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

	public long getTimemillis() {
		return timemillis;
	}

	public void setTimemillis(long timemillis) {
		this.timemillis = timemillis;
	}

	public List<Chapter> getChildren() {
		return children;
	}

	public void setChildren(List<Chapter> children) {
		this.children = children;
	}


}
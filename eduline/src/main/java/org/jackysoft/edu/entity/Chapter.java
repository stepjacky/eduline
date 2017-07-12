package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.mongodb.morphia.annotations.Embedded;


@Table(label = "课程章节")
public class Chapter extends NoEntity {
	
	private String name;

	private String parent;

	private String isParent = "false";

	private String textBook;

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

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}


	public String getTextBook() {
		return textBook;
	}

	public void setTextBook(String textBook) {
		this.textBook = textBook;
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

	@Override
	public ZtreeNode toZtreeNode() {
		ZtreeNode node = new ZtreeNode();
		node.setId(getId());
		node.setName(getName());
		node.setParent(getParent());
		node.setSort(getSort());
		node.setIsParent(getIsParent());
		return node;
	}


	
	
	
	
}
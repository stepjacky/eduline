package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.mongodb.morphia.annotations.Embedded;


@Table(label = "课程章节")
public class Chapter extends NoEntity {
	
	private String name;

	private String parent;

	private String isParent = "false";

	@Embedded
	private NameValue textBook;

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

	public NameValue getTextBook() {
		return textBook;
	}

	public void setTextBook(NameValue textBook) {
		this.textBook = textBook;
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
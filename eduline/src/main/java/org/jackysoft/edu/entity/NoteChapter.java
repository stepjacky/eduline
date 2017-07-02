package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;


@Table(label = "课程章节")
public class NoteChapter extends NoEntity {
	
	private String name;

	private String parent;

	private String isParent = "false";

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
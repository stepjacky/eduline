package org.jackysoft.edu.formbean;

import java.util.HashMap;
import java.util.Map;

public class ZtreeNode {
	private String id;
	private String name;
	private int sort;
	private String parent;
	private String isParent;
	private Map<String,String> _attr;
	
	
	
	public ZtreeNode(){
		_attr = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public ZtreeNode attr(String key,String value){
		_attr.put(key, value);
	    return this;
	}
	
	public Map<String,String> getAttr(){
		return new HashMap<>(_attr);
	}
}

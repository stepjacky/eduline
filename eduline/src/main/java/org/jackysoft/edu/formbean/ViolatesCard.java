package org.jackysoft.edu.formbean;

import java.util.List;

public class ViolatesCard {
	
	private String title;
    private List<ViolateBean> beans;

	public List<ViolateBean> getBeans() {
		return beans;
	}

	public void setBeans(List<ViolateBean> beans) {
		this.beans = beans;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}

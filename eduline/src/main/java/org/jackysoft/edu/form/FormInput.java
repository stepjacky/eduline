package org.jackysoft.edu.form;

public class FormInput {
	private String name;
	private String html;
	private String label; 
	private boolean listed;
	private boolean inputed;
	private boolean queried;
	public FormInput(String name,  String label,String html) {
		super();
		this.name = name;		
		this.label = label;
		this.html = html;
		
	}

	/**name属性*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**html代码*/
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	/**汉字描述*/
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}

	public boolean isInputed() {
		return inputed;
	}

	public void setInputed(boolean inputed) {
		this.inputed = inputed;
	}

	public boolean isQueried() {
		return queried;
	}

	public void setQueried(boolean queried) {
		this.queried = queried;
	}

}

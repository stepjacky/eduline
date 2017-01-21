package org.jackysoft.utils;

import java.util.HashMap;
import java.util.Map;

public class FormOption {
	private String value;
	private String text;
	private Map<String,String> attr = new HashMap<>();
	
	public FormOption(String value,String text){
		this.text = text;
		this.value= value;
	}
	public  String getValue() {
    	return value;
    }
    public  String getText() {
    	return text;
    }
    public Map<String,String> getAttr(){return attr;}
    public void attr(String k,String v) {
    	this.attr.put(k, v);
    }
    public String getHtml() {
    	return String.format("<option value='%s'>%s</option>", getValue(),getText());
    }
}

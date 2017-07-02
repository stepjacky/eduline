package org.jackysoft.edu.service.base;

import java.util.HashMap;

public class PreResult extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420342153940092744L;
    public static final PreResult SUCCESS = new PreResult(true);
    public static final PreResult FAILURE = new PreResult(false);
	private boolean flag = false;
    
	
    
	public PreResult(boolean flag) {
		super();
		this.flag = flag;
	}
	public PreResult() {
		
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

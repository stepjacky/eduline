package org.jackysoft.edu.view;

import java.util.HashMap;

public class ActionResult extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420342153940092744L;
    public static final ActionResult SUCCESS = new ActionResult(true);
    public static final ActionResult FAILURE = new ActionResult(false);
	private boolean flag = false;
    private String message;
	
    
	public ActionResult(boolean flag) {
		super();
		this.flag = flag;
		if(this.flag){
			message="操作成功";
		}else{
			message="操作失败";
		}
	}
	public ActionResult() {
		this(true);
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

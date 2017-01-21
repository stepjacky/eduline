package org.jackysoft.edu.formbean;

import java.util.HashMap;
import java.util.Map;

public class Monthly {
    private int value;
    private static final Map<Integer,String> info = new HashMap<>();
    static {
    	info.put(0, "第一次月考");
    	info.put(1, "期中考试");
    	info.put(2, "第二次月考");
    	info.put(3, "期末考试");
    }
	public Monthly(int value) {
		super();
		this.value = value;
		
	}
	
	public int getMonthly() {
		return value;
	}
	
	public String getMonthlyName() {
		return info.get(value);
	}
	
	
    
}

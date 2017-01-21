package org.jackysoft.file;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.base.Strings;

public abstract class CMD {
	protected String base;
	
	protected  volatile String inner = new String();
	protected volatile String ext = "";
	private static final Map<String,CMD> map = new HashMap<>();
	static{
		map.put("doc", new DOCCMD());
		map.put(".doc", new DOCCMD());
		map.put("docx", new DOCCMD());
		map.put(".docx", new DOCCMD());
		map.put("ppt", new PPTCMD());
		map.put(".ppt", new PPTCMD());
		map.put("pptx", new PPTCMD());		
		map.put(".pptx", new PPTCMD());	
		map.put("jpg", new JPGCMD());
		map.put(".jpg", new JPGCMD());
		map.put("jpeg", new JPGCMD());
		map.put(".jpeg", new JPGCMD());
		map.put("png", new JPGCMD());
		map.put(".png", new JPGCMD());
	}
	protected CMD() {		
		this.base = System.getProperty("resbase");
	}
	public static CMD withType(String type){
		if(Strings.isNullOrEmpty(type)){
			throw new IllegalArgumentException("type is not allowed for null value");
		}
		CMD cmd = map.get(type.toLowerCase());
		if(cmd==null){
			throw new java.lang.UnsupportedOperationException("type "+type+" is not supported yet");
		}	
		cmd.ext = type.startsWith(".")?type.substring(1):type;
		return cmd;
	}
	
	
	
	public abstract CMD get(String fileName);
	
	public String toCommand() {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			String cmd = new String(this.inner.getBytes());
			this.inner = "";
			return cmd;
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public String toString() {
		return this.inner;
	}
	
	

}

class DOCCMD extends CMD{
	
	public DOCCMD() {
		super();			
	}

	@Override
	public CMD get(String fileName){
		inner = String.format("%s %s %s %s", "DOC", base, fileName, ext);
		return this;
	}
}

class PPTCMD extends CMD{		
	public PPTCMD() {
		super();
		
	}
	@Override
	public CMD get(String fileName){
		inner = String.format("%s %s %s %s", "PPT", base, fileName, ext);
		return this;
	}
}

class JPGCMD extends CMD{		
	public JPGCMD() {
		super();
		
	}
	@Override
	public CMD get(String fileName){
		inner = String.format("%s %s %s %s", "JPG", base, fileName, ext);
		return this;
	}
}
package org.jackysoft.file;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.base.Strings;

public abstract class CMD {
	public static final String PDF_EXTISION=".pdf";
	protected static String base;
	
	protected  volatile String inner = new String();
	protected  volatile String ext = "doc";
	protected  volatile String command = "DOC";
	private    static final Map<String,CMD> map = new HashMap<>();
	private static final Set<String> officPostfix = new HashSet<>();
	static{
		map.put("doc", new WORD());
		map.put(".doc", new WORD());
		map.put("docx", new WORD());
		map.put(".docx", new WORD());
		map.put("ppt", new POWERPOINT());
		map.put(".ppt", new POWERPOINT());
		map.put("pptx", new POWERPOINT());
		map.put(".pptx", new POWERPOINT());
		map.put("jpg", new JPEG());
		map.put(".jpg", new JPEG());
		map.put("jpeg", new JPEG());
		map.put(".jpeg", new JPEG());
		map.put("png", new JPEG());
		map.put(".png", new JPEG());
		officPostfix.add("doc");
		officPostfix.add("docx");
		officPostfix.add("ppt");
		officPostfix.add("pptx");

	}
	protected CMD(String command){
		CMD.base = System.getProperty("resbase");
		this.command = command;
	}
	public static CMD getCMD(String type,String fileName){
		if(Strings.isNullOrEmpty(type)){
			throw new IllegalArgumentException("type is not allowed for null value");
		}
		CMD cmd = map.get(type.toLowerCase());
		if(cmd==null){
			return null;
		}	
		cmd.ext = type.startsWith(".")?type.substring(1):type;
		cmd.inner = String.format("%s %s %s %s", cmd.command.toUpperCase(), CMD.base, fileName, cmd.ext);
		return cmd;
	}

	public static boolean isOffice(String extision){
		if(Strings.isNullOrEmpty(extision)) return false;
		extision = extision.startsWith(".")?extision.substring(1):extision;
		return officPostfix.contains(extision.toLowerCase());
	}

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


	private static class WORD extends CMD{

		public WORD() {
			super("DOC");
		}

	}

	private static class POWERPOINT extends CMD{
		public POWERPOINT() {
			super("PPT");

		}

	}

	private static class JPEG extends CMD{
		public JPEG() {
			super("JPG");
		}
	}

}
package org.jackysoft.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class URLMapper {
	 public static final Map<String,String> with(String query){
	    	final Map<String, String> map = new HashMap<>();
	    	if(Strings.isNullOrEmpty(query)) return map;
	    	List<String> list = Splitter.on('&').trimResults().splitToList(query);
	    	if(list.isEmpty()) return map;
	    	for(String param : list) {
	    		List<String> single = Splitter.on('=').splitToList(param);
	    		if(single.size()==2) {
	    			map.put(single.get(0), single.get(1));
	    		}    		
	    	}
			
			return map; 
	    }
	    
	    public static final String encode(String query) {
	    	String result = query;
	    	try {
				result = URLEncoder.encode(query,"UTF-8");
			} catch (UnsupportedEncodingException e) {}
	    	return result;
	    }
}

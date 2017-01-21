package org.jackysoft.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.poi.ss.usermodel.Cell;
import org.jackysoft.edu.annotation.OptionAttr;
import org.jackysoft.edu.annotation.OptionText;
import org.jackysoft.edu.annotation.OptionValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class StringUtils extends org.springframework.util.StringUtils {
	
	public static final String ALAPHA_PATTERN="^[A-Za-z]+$";
	
	
	public static String randomstring() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32).toUpperCase();
	}

	public static String addJsonSource(String html, String key) {
		Document doc = Jsoup.parse("");
		Element slt = doc.body().append(html).child(0).addClass("jsonData")
				.attr("key", key);
		return slt.outerHtml();
	}

	/**
	 * 
	 * 
	 * @return formoption列表
	 *
	 *
	 **/
	public static <E> FormOption convertFormOption(E e) {
		Field[] fds = e.getClass().getDeclaredFields();
		String text = null, value = null;
		Map<String,String> attrs = Maps.newHashMap();
		BeanWrapper bw = new BeanWrapperImpl(e);
		for (Field f : fds) {
			if (f.isAnnotationPresent(OptionValue.class)) {
				if (bw.isReadableProperty(f.getName())) {
					value = bw.getPropertyValue(f.getName()).toString();
				}
			}
			if (f.isAnnotationPresent(OptionText.class)) {
				if (bw.isReadableProperty(f.getName())) {
					text = bw.getPropertyValue(f.getName()).toString();
				}
			}
			if (f.isAnnotationPresent(OptionAttr.class)) {
				if (bw.isReadableProperty(f.getName())) {
					String k = f.getName();
					String v = bw.getPropertyValue(k).toString();
					attrs.put(k, v);
				}
			}
			
			if (!Strings.isNullOrEmpty(text) && !Strings.isNullOrEmpty(value))
				break;
		}
		if (!Strings.isNullOrEmpty(text) && !Strings.isNullOrEmpty(value)) {
			FormOption opt = new FormOption(value, text);
			opt.getAttr().putAll(attrs);
			return opt;
		}
		return null;
	}
	
	public static String toPinyin(String target) {

		if(Strings.isNullOrEmpty(target)) return "";
		
		StringBuilder sb = new StringBuilder();
	
		for(char c:target.toCharArray()) {
			String[] piny = PinyinHelper.toHanyuPinyinStringArray(c);
			if(piny==null||piny.length==0)continue;
			String s = piny[0];
			sb.append(s.subSequence(0, s.length()-1));
		}
	
		return sb.toString();
	}
	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");    
	    return pattern.matcher(str).matches();    
	 
	}  
	
	public static boolean isOnlyAlapha(String str){
		String s = StringUtils.trimAllWhitespace(str);
		Pattern pattern = Pattern.compile(ALAPHA_PATTERN);
		return pattern.matcher(s).matches();
	}
	
	public static double getCellNumberValue(Cell cell) {
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
		case Cell.CELL_TYPE_BOOLEAN:
		case Cell.CELL_TYPE_ERROR:
		case Cell.CELL_TYPE_FORMULA:
			return 0d;
		case Cell.CELL_TYPE_NUMERIC:return cell.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			double d = 0d;
			try {
				String val = trimAllWhitespace(cell.getStringCellValue());
				if(Strings.isNullOrEmpty(val))return 0d;
				d = Double.parseDouble(val);
				return d;
			}catch(Exception e) {
				return 0d;
			}
			
			default:return 0d;
		}
	}
	
	public static String toDownloadFileName(String s) {
		if (s == null)
			return s;
		try {
			s = new String(s.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
		return s;
	}
}

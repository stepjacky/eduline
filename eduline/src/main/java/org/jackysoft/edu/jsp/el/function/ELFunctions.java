package org.jackysoft.edu.jsp.el.function;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

public class ELFunctions {
	private static final Map<Integer,String> weeks = new HashMap<Integer,String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 9175907015368570227L;
		{
			int[]  alb = {0,1,2,3,4,5,6,7,8,9};
			char[] chs = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' }; 
		    for(int i=0;i<alb.length;i++){
		    	put(new Integer(alb[i]),chs[i]+"");
		    }
		}
	};
	
	private static final Map<Integer, String> months = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1216944875490944568L;

		{
			put(0, "第一次月考");
			put(1, "期中考试");
			put(2, "第二次月考");
			put(3, "期末考试");

		}
	};

	private static final Map<Integer, String> utype = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3674502873734949543L;

		{
			put(0, "管理员");
			put(1, "学生");
			put(2, "教师");
			put(3, "家长");
			put(4, "事件管理员");
		}
	};
	
	private static final Map<Integer, String> courseTypes = new HashMap<Integer, String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = -8876532777544451351L;
		{
			put(0, "主");
			put(1, "副");
			put(2, "次");
			
		}
		
	};
	static final Log logger = LogFactory.getLog(ELFunctions.class);
	static final ObjectMapper mapper = new ObjectMapper();

	public static String monthly(Integer m) {
		if (m == null || m < 0 || m > 3)
			m = 0;
		return months.get(m);

	}

	public static String semester(Integer s) {
		if (s == null || s < 0 || s > 1)
			s = 0;
		return s == 0 ? "上学期" : "下学期";
	}

	public static String violateType(Integer s) {
		if (s == null)
			s = 0;
		Document doc = Jsoup.parse("");
		Element span = doc.createElement("span").addClass("label");
		if (s > 0) {
			return span.addClass("label-success").html("奖").outerHtml();
		}
		return span.addClass("label-danger").html("罚").outerHtml();
	}

	public static String userType(Integer s) {
		if(s==null) return "无效权限";
		String val =  utype.get(s);
		return val==null?"无效权限":val;
	}

	public static String sex(Integer s) {
		return (s == null || s == 1) ? "男" : "女";
	}

	public static String grade(Integer s) {
		if (s == null || s == 0)
			return "-";
		return s + "年级";
	}

	public static String toJson(Object o) {
		if (o == null)
			return "{}";
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error(e);
			return "{err}";
		}
	}

	/**
	 * <pre>
	 * <select id='${id}' name='${id}' class='form-control'> <option value='0'>0
	 * </option> <option value='0.5'>0.5</option> <option value='1'>1</option>
	 * <option value='2'>2</option> <option value='3'>3</option>
	 * <option value='4'>4</option> <option value='5'>5</option>
	 * <option value='10'>10</option> </select>
	 * </pre>
	 */
	public static String vpoint(String id) {
		Document doc = Jsoup.parse("");
		Element select = doc.createElement("select").attr("id", id).attr("name", id).addClass("form-control");
		String[] p = new String[] { "0", "0.5", "1", "2", "3", "4", "5", "10" };
		for (String sp : p) {
			select.appendElement("option").val(sp).html(sp);
		}
		return select.outerHtml();

	}

	public static String formatFloat(Float f,Integer s) {
		if(f==null) return "";
		if(s==0||s==null)s=1;
		return String.format("%."+s+"f", f);
	}
	
	public static String dateFormat(Long mills) {
		if(mills==null || mills==0) return "0-0-0";
		Instant instant = Instant.ofEpochMilli(mills);
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
		return DateTimeFormatter.ofPattern("y-M-d").format(ldt);
	}
	
	public static String datetimeFormat(Long mills){
		if(mills==null || mills==0) return "0-0-0";
		Instant instant = Instant.ofEpochMilli(mills);
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
		return DateTimeFormatter.ofPattern("y-M-d H:m:s").format(ldt);
	}
	
	public static String datetimeFmt(Long mills,String pattern){
		if(mills==null || mills==0) return "0-0-0";
		String ptn = Strings.isNullOrEmpty(pattern)?"y-M-d H:m:s":pattern;
		Instant instant = Instant.ofEpochMilli(mills);
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
		return DateTimeFormatter.ofPattern(ptn).format(ldt);
	}
	
	public static String weekly(Integer week){
		if(week==null)return weeks.get(0);
		return "星期"+ weeks.get(week);
		
	}
	
	public static String courseType(Integer type){
		
		String s = courseTypes.get(type);
		return s!=null?s:"未知";
	}

	public static String datasize(Long size){
		return StringUtils.getDataSize(size);
	}
}

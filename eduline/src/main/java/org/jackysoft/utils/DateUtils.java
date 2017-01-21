package org.jackysoft.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import com.google.common.base.Strings;

public final class DateUtils {
	/**
	 * 一天所消耗的毫秒数 
	 **/
	public static final long MILLISSECONDS_OF_ONE_DAY=24*3600*1000;
	
	
    public static final long currentMillis() {
    	LocalDateTime ldt = LocalDateTime.now();
    	ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
    	return zdt.toInstant().toEpochMilli();
    	
    }
    
    public static String formatLocalDate(LocalDate date,String pattern) {
    	  LocalDate now  = date==null?LocalDate.now():date;
    	  String ptn = Strings.isNullOrEmpty(pattern)?"yyyy-MM-dd":pattern;
    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ptn);
    	  String text = now.format(formatter);
    	  return text;
    }
    public static String formatLocalDate(long date,String pattern) {
    	return formatLocalDate(fromMillis(date).toLocalDate(),pattern);
    }
    
    public static long withMillis(LocalDateTime ldt ) {
    
    	ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
    	return zdt.toInstant().toEpochMilli();
    	
    }
   
    public static long withMillis(LocalDate ld ) {
    	
    	LocalDateTime ldt = ld.atTime(0, 0, 0);
    	return withMillis(ldt);
    	
    }
    
    public static WeekRange weekRange(LocalDate now) {
    	if(now==null) now = LocalDate.now();
		DayOfWeek dow = now.getDayOfWeek();
		int offsetstart = dow.getValue()-1;
		int offsetend   = 7-dow.getValue();
		LocalDate start = now.minusDays(offsetstart);
		LocalDate end =   now.plusDays(offsetend);
		return new WeekRange(withMillis(start),withMillis(end),now.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
    }
    
    public static LocalDateTime fromMillis(long mills) {
    
    	Instant instant = Instant.ofEpochMilli(mills);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        return ldt;
    }
       
    public static  class WeekRange{
    	private long start;
    	private long end;  	
    	private int weekOfyear;
    	private int dayOfweek;
    	
		public WeekRange(long start, long end,int wn) {
			super();
			this.start = start;
			this.end = end;
		}
		public long getStart() {
			return start;
		}
		public void setStart(long start) {
			this.start = start;
		}
		public long getEnd() {
			return end;
		}
		public void setEnd(long end) {
			this.end = end;
		}
		public int getWeekOfyear() {
			return weekOfyear;
		}
		public void setWeekOfyear(int weekOfyear) {
			this.weekOfyear = weekOfyear;
		}
		public int getDayOfweek() {
			return dayOfweek;
		}
		public void setDayOfweek(int dayOfweek) {
			this.dayOfweek = dayOfweek;
		}
	
		
		
    	
    }
}

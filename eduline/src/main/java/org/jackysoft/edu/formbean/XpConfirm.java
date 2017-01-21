package org.jackysoft.edu.formbean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.utils.DateUtils;

public class XpConfirm {
	private String name;
	private String localName;
	private int dobYear;
	private int dobMonth;
	private int dobDayOfMonth;

	private int inputYear;
	private int outputYear;
	private String nowDate;

	private int grade = 7;
	
	public XpConfirm(SysUser user) {
		if(user==null) return;
		this.setName(String.format("%s %s", user.getFirstName(),user.getLastName()).toUpperCase());
		this.setLocalName(user.getNickname());
		LocalDateTime dob = DateUtils.fromMillis(user.getBirthday());
		this.setDobDayOfMonth(dob.getDayOfMonth());
		this.setDobMonth(dob.getMonthValue());
		this.setDobYear(dob.getYear());	
		this.grade = user.myGrade();
				
		this.setInputYear(grade<=9?user.beginYear():(user.beginYear()+3));
		this.setOutputYear(grade<=9?(user.beginYear()+3):(user.beginYear()+6));
	    
		DateTimeFormatter df =  DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ENGLISH);
	    this.setNowDate(df.format(LocalDateTime.now()));
	    
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public int getDobYear() {
		return dobYear;
	}

	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}

	public int getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(int dobMonth) {
		this.dobMonth = dobMonth;
	}

	public int getDobDayOfMonth() {
		return dobDayOfMonth;
	}

	public void setDobDayOfMonth(int dobDayOfMonth) {
		this.dobDayOfMonth = dobDayOfMonth;
	}

	public int getInputYear() {
		return inputYear;
	}

	public void setInputYear(int inputYear) {
		this.inputYear = inputYear;
	}

	public int getOutputYear() {
		return outputYear;
	}

	public void setOutputYear(int outputYear) {
		this.outputYear = outputYear;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
	public String getJuniorZh() {
		String s = "          谨此证明 %s (出生日期：%d 年 %d 月 %d 日)自  %d 年9月起就读于北京市新英才学校剑桥国际中心开设的国际初中课程。7年级和8年级的课程覆盖北京"
				+ "市教委规定的初中三年的课程和英国剑桥大学考试委员会提供的中级课程预备（Checkpoint） 的部分内容，9年级开始学习英国中级水平（IGCSE）课程。"
				+"\n          该学生在本校学习时间为 %d 年9月至 %d 年6月底，完成7-9年级课程，并参加北京市初中会考和英国中级水平5门课程的国际考试。";
        return String.format(s, this.getLocalName(),this.getDobYear(),this.getDobMonth(),this.getDobDayOfMonth()
        		,this.getInputYear(),this.getInputYear(),this.getOutputYear()
        		);      
	}
	
	public String getSeniorZh() {
		String s = "          谨此证明 %s (出生日期：%d 年 %d 月 %d 日)自 %d 年9月起就读于北京市新英才学校剑桥国际中心开设的1年制英"
				+ "国中级水平教育证书（IGCSE）和2年制高级水平教育证书（A-Level）课程。 IGCSE课程包括：数学、附加数学、化学、物理、中文、英语第二语言课程、音乐、"
				+ "体育和美术。A-Level课程包括：数学，高等数学，化学，物理，中文，英语的听、说、读、写课程，音乐，体育和美术。"
				+"\n          该学生在本校学习时间为 %d 年9月至 %d 年6月底，完成10-12年级（IGCSE，A Level）课程。";
		
		 return String.format(s, this.getLocalName(),this.getDobYear(),this.getDobMonth(),this.getDobDayOfMonth()
	        		,this.getInputYear(),this.getInputYear(),this.getOutputYear()
	        		);  	
	}
	
	public String getJuniorUs() {
		String s = "This is to certify that %s (DOB: %d / %d / %d ) was enrolled as a "
				+ "student of Cambridge International Centre at Beijing New Talent Academy. The fast-track programme "
				+ "of Year 7 and Year 8 includes completion of Junior High School curriculum prescribed by Beijing "
				+ "Education Commission and part of the syllabus of Checkpoint by Cambridge International Examinations. "
				+ "In Year 9 students begin to work on IGCSE (International General Certificate of Secondary Education) "
				+ "by Cambridge International Examinations." 
				+ "\n\nThe above-mentioned student will completed all subjects as required, obtained Leaving Certificate of Junior "
				+ "High School from Beijing Education Commission and sat for IGCSE exams in (5) subjects."
				+ "\n\nThe course started in September %d and will finish at the end of June %d .";

        return String.format(s, this.getName(),this.getDobYear(),this.getDobMonth(),this.getDobDayOfMonth()
        		,this.getInputYear(),this.getOutputYear()
        		);      
	}
	
	public String getSeniorUs() {
		String s = "This is to certify that %s (DOB: %d / %d / %d ) was enrolled as a "
				+ "student of Cambridge International Centre at Beijing New Talent Academy, doing a 1-year IGCSE "
				+ "(International General Certificate of Secondary Education) course followed by a 2-year A-Level "
				+ "(General Certificate of Education Advanced Level) course. The subjects of IGCSE Courses include: "
				+ "Mathematics, Additional Mathematics, Chemistry, Physics, Chinese, English as a Second Language,"
				+ "Music, Art and Physical Education. The AS and A-Level Courses include: Mathematics, Further Mathematics, "
				+ "Chemistry, Physics, Chinese, Music, Art, Physical Education and English for Academic Purposes. "
				+ "\n\nThe course started in September %d and will finish at the end of June %d .";
		
		 return String.format(s, this.getName(),this.getDobYear(),this.getDobMonth(),this.getDobDayOfMonth()
	        		,this.getInputYear(),this.getOutputYear()
	        		);  
	}
	
	public String getZhContent() {
		return grade<=9?this.getJuniorZh():this.getSeniorZh();
				
	}
	
	public String getUsContent() {
		return grade<=9?this.getJuniorUs():this.getSeniorUs();
	}
	
	
	
	
	
	
}

package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.InputType;
import org.jackysoft.edu.annotation.Table;
/**
 * 违纪/奖励记录
 * */
@Table(label="奖惩记录")
public class Violates {
     
	@Column(label="编号",id=true,idStrategy=IdStrategy.UUID,list=false,input=false)
	private String id;
	
	//>0正面 <0负面
	@Column(label="正/负面",formType=InputType.SELECT,data= {"0","惩罚","1","奖励"},query=true)
	private int affirmative;
	
	//当事人
	@Column(label="当事人ID",list=false)
	@NotNull
	private String student;
	@Column(label="当事人姓名",input=false,query=true)
	private String studentName;
	
	@Column(label="当事人年级")
	private int grade;
	
	@Column(label="年级名称")
	private String gradeName;
	
	//记录老师
	@Column(label="记录老师ID",list=false)
	@NotNull
	private String teacher;
	@Column(label="记录老师姓名",input=false,query=true)
	private String teacherName;
	
	@Column(label="情况说明",formType=InputType.TEXTAREA)
	
	private String content;
	
	@Column(label="得分")
    @NotNull
	private float scoreValue;
	
	@Column(label="发生时间",formType=InputType.INPUT_DATE,query=true)
	private long fireTime;

	@Column(label="截止有效时间",formType=InputType.INPUT_DATE,query=true)
	private long validity;
	
	@Column(label="发生年份",formType=InputType.INPUT_DATE,query=true)
	private int year;
	
	@Column(label="发生所在周年范围")
	private int weekOfyear;
	
	@Column(label="发生星期数")
	private int dayOfweek;
	
	@Column(label="所在周起始日期")
	private long weekstart;
	
	@Column(label="所在周结束日期")
	private long weekend;
	
	@Column(label="记录时间")
	private long recordtime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAffirmative() {
		return affirmative;
	}

	public void setAffirmative(int affirmative) {
		this.affirmative = affirmative;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(float scoreValue) {
		this.scoreValue = scoreValue;
	}

	public long getFireTime() {
		return fireTime;
	}

	public void setFireTime(long fireTime) {
		this.fireTime = fireTime;
	}

	public long getValidity() {
		return validity;
	}

	public void setValidity(long validity) {
		this.validity = validity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	

	
	


	public int getDayOfweek() {
		return dayOfweek;
	}

	public void setDayOfweek(int dayOfweek) {
		this.dayOfweek = dayOfweek;
	}

	public long getWeekstart() {
		return weekstart;
	}

	public void setWeekstart(long weekstart) {
		this.weekstart = weekstart;
	}

	public long getWeekend() {
		return weekend;
	}

	public void setWeekend(long weekend) {
		this.weekend = weekend;
	}

	public long getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}

	public int getWeekOfyear() {
		return weekOfyear;
	}

	public void setWeekOfyear(int weekOfyear) {
		this.weekOfyear = weekOfyear;
	}


	
	
	
	
	
}

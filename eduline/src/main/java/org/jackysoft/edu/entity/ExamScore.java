package org.jackysoft.edu.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.InputType;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.annotation.Transient;

@Table(label = "成绩")
public class ExamScore {

	@Column(idStrategy = IdStrategy.UUID, list = false, input = false)
	private String id;

	@Column(id=true,list = false, input = false)
	private String groupId;
	// 某学生
	@Column(id = true, label = "学生ID", list = false)
	private String student;

	@Column(label = "学生姓名", query = true)
	private String studentName;
	

	@Column(label = "年级")
	private int grade;
	
	//0-初中[7,8,9],1-高中[10,11,12]
	@Column(label="年级级别")
	private int  jors;
	
	@Column(label="入学年份")
	private int inyear;
	
	@Column(label = "年级名称")
	private String gradeName;
	
	// 某学期
	// 0 上学期 1 上学期
	@Column(id = true, label = "学期[上/下]", formType = InputType.SELECT, data = {
			"0", "上学期", "1", "下学期" }, query = true)
	private int semester;

	// 某课程
	@Column(id = true, label = "课程编号", formType = InputType.SELECT, data = "/course/options")
	private int course;

	@Column(label = "课程名称", formType = InputType.INPUT_HIDDEN, input = false, query = true)
	private String courseName;
	
	
	@Column(label="课程类别")
	private String courseType;
    
	// 某次月考
	@Column(id = true, label = "月考序号", formType = InputType.SELECT, data = {
			"0", "第一次月考", "1", "期中考试", "2", "第三次月考", "3", "期末考试" }, query = true)
	private int monthly;
	@Column(label = "月考名称", formType = InputType.INPUT_HIDDEN, input = false)
	private String monthlyName;

	// 成绩
	@Column(label = "分数", query = true)
	@Min(0)
	@Max(100)
	private float scoreValue;

	
	@Column 
	private float avgValue;
	
	@Column
	private int rankValue;
	
	//是否冻结>0 未冻结,其他冻结
	@Column(label = "是否冻结",defaultValue="1",list=false,input=false)
	private int freeze;
	
	
	@Column
	private String remark;
	
	@Transient
	private int enabled ;
	
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getMonthly() {
		return monthly;
	}

	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public String getMonthlyName() {
		return monthlyName;
	}

	public void setMonthlyName(String monthlyName) {
		this.monthlyName = monthlyName;
	}

	public float getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(float scoreValue) {
		this.scoreValue = scoreValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getFreeze() {
		return freeze;
	}

	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}

	public int getInyear() {
		return inyear;
	}

	public void setInyear(int inyear) {
		this.inyear = inyear;
	}

	public int getJors() {
		return jors;
	}

	public void setJors(int jors) {
		this.jors = jors;
	}

	public float getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(float avgValue) {
		this.avgValue = avgValue;
	}

	public int getRankValue() {
		return rankValue;
	}

	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
	}
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	
	
	
}

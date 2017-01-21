package org.jackysoft.edu.entity;

import javax.validation.constraints.NotNull;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.InputType;
import org.jackysoft.edu.annotation.Table;


@Table(label="班级成员")
public class GroupMember {
	
	@Column(idStrategy=IdStrategy.UUID,list=false,input=false)
    private String id;
	
	@Column(label="名称")
	private String name;
	
	@Column(label="教师ID",list=false)
	@NotNull
	private String teacher;
	@Column(label="教师姓名",query=true)
	private String teacherName;
	
	
	@Column(id=true,label="群组ID",query=true,list=false)
	private String groupId;
	
	// 某学年
	@Column(label = "年份", query = true)
	private int inyear;
	
	@Column(id=true,label="学生ID",list=false)
	@NotNull
	private String student;
	@Column(label="学生姓名",query=true)
	private String studentName;
	
	@Column(label="年级ID",formType=InputType.SELECT,data="/grade/options",list=false)
	@NotNull
	private int    grade;
	
	@Column(label="年级名称",formType=InputType.INPUT_HIDDEN,query=true)
	private String gradeName;
	
	@Column(label="课程编号",data="/course/options",list=false)
	private int course;
	
	@Column(label="课程名称",formType=InputType.INPUT_HIDDEN,query=true)
	private String courseName;
	
	@Column(label="课程分类")
	private int courseType;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public int getInyear() {
		return inyear;
	}
	public void setInyear(int inyear) {
		this.inyear = inyear;
	}
	public int getCourseType() {
		return courseType;
	}
	public void setCourseType(int courseType) {
		this.courseType = courseType;
	}
	
	
	
}

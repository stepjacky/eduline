package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.InputType;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.MediaFile;

@Table(label="试题")
public class ExamPaper implements MediaFile {

	@Column(label="试题ID",id=true,idStrategy=IdStrategy.UUID)
	private String id;
	
	@Column(label="分类",formType=InputType.SELECT,data= {"IGCSE","IGCSE","AS","AS","A2","A2"})
	private String category;
	
	@Column(label="所属课程",formType=InputType.SELECT,data="/course/options")
	private int course;
	
	@Column(label="课程名称",input=false,list=true,query=false)
	private String courseName;
	
	@Column(label="年份")
	private int year;

	@Column(label="名称",query=true)
	private String name;
	
	@Column(label="内容类型",input=false,query=false,list=false)
	private String fileType;
	
	
	@Column(label="真实路径",input=false,query=false,list=false)
	private String realPath;
	
	@Column(label="文件大小",list=false,input=false)
	private long size;
	
	@Column(label="上传日期",input=false)
	private long fireTime;
	
	
	
	public ExamPaper() {
		super();
	
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public long getContentLength() {
		return this.getSize();
	}

	@Override
	public String getContentType() {
		return this.getFileType();
	}

	@Override
	public String getFilename() {
		return this.getName();
	}

	public long getFireTime() {
		return fireTime;
	}

	public void setFireTime(long fireTime) {
		this.fireTime = fireTime;
	}

}

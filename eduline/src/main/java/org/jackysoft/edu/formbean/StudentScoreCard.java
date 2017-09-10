package org.jackysoft.edu.formbean;

import java.util.List;

import org.jackysoft.edu.entity.ExamScore;

import com.google.common.collect.Lists;

/**
 * 学生某次考试详情
 * */
public class StudentScoreCard {
	private String name = null;

	private int inyear;
	private String student;
	private int grade;
	private String gradeName;

	private int semester;
	private String semesterName;

	private int monthly;
	private String monthlyName;

	private String studentName;
	
	private int current;	

	private float totalScore;
	private float avgTotalScore;
	private int totalSorted;
	
	
	private List<ExamScore> details = Lists.newArrayList();
	
	
	public StudentScoreCard() {
		super();
	}
	
	public StudentScoreCard(ExamScore info,int page) {
		super();
		this.current = page;
		if(info==null) {
			this.name = "没有了";
		    return;
		}
		setInyear(info.getInyear());
		setStudent(info.getStudent());
		setStudentName(info.getStudentName());
		setGrade(info.getGrade());
		setGradeName(info.getGradeName());
		Semester s = new Semester(info.getSemester());
		setSemester(s.getSemester());
		setSemesterName(s.getSemesterName());
		setMonthly(info.getMonthly());
		setMonthlyName(info.getMonthlyName());	
		
		int sy = inyear+(grade-7);
		String yvar = "";
		if(semester==0) {
			yvar = String.format("%d-%d", sy,sy+1);
		}else {
			yvar = String.format("%d-%d", sy-1,sy);
		}
		this.name =  String.format("%d届(%s学年)%s%s %s",inyear,yvar,gradeName,semesterName,monthlyName);
	    
	}

	
	
	public String getName() {
		return this.name;
	}


	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public float getAvgTotalScore() {
		return avgTotalScore;
	}

	public void setAvgTotalScore(float avgTotalScore) {
		this.avgTotalScore = avgTotalScore;
	}

	public int getTotalSorted() {
		return totalSorted;
	}

	public void setTotalSorted(int totalSorted) {
		this.totalSorted = totalSorted;
	}

	public List<ExamScore> getDetails() {
		return details;
	}

	public void setDetails(List<ExamScore> details) {
		this.details = details;
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

	public int getInyear() {
		return inyear;
	}

	public void setInyear(int inyear) {
		this.inyear = inyear;
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
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

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
}

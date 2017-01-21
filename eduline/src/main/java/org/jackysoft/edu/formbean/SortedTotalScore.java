package org.jackysoft.edu.formbean;

import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.Table;

/**
 * 总分成绩总揽
 * 
 * @author jacky
 *
 */
@Table
public class SortedTotalScore {

	@Column
	private String student;

	@Column
	private String studentName;
	@Column
	private int inyear;
	@Column
	private int grade;
	@Column
	private String gradeName;
	@Column
	private int semester;
	@Column
	private String semesterName;
	@Column
	private int monthly;
	@Column	
	private String monthlyName;
	@Column
	private float totalScore;
	@Column
	private float avgScore;
	@Column
	private int totalSorted;

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

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}

	public int getTotalSorted() {
		return totalSorted;
	}

	public void setTotalSorted(int totalSorted) {
		this.totalSorted = totalSorted;
	}

}

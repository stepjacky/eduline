package org.jackysoft.edu.formbean;

public class ViolateResult {
	private float upPoint;
	private float downPoint;
	private String student;
	private String studentName;
	private int weekOfyear;
	private int inyear;
	private int grade;
	private String gradeName;

	private String weekStart;
	private String weekEnd;
	
	
	
	
	public String getAffirmative() {
		return String.format("%.1f", getUpPoint());
	}

	

	

	public String getNegative() {
		return String.format("%.1f", getDownPoint());
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

	public float getUpPoint() {
		return upPoint;
	}





	public void setUpPoint(float upPoint) {
		this.upPoint = upPoint;
	}





	public float getDownPoint() {
		return downPoint;
	}





	public void setDownPoint(float downPoint) {
		this.downPoint = downPoint;
	}





	public String getWeekStart() {
		return weekStart;
	}





	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}





	public String getWeekEnd() {
		return weekEnd;
	}





	public void setWeekEnd(String weekEnd) {
		this.weekEnd = weekEnd;
	}





	public int getWeekOfyear() {
		return weekOfyear;
	}





	public void setWeekOfyear(int weekOfyear) {
		this.weekOfyear = weekOfyear;
	}

	
	
	
	
	
}

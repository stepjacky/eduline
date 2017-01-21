package org.jackysoft.edu.formbean;

public class Semester {
	private int semester;
	private String semesterName;

	public Semester(int semester) {
		super();
		this.semester = semester;	
		this.semesterName=semester==0?"上学期":"下学期";
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

}

package org.jackysoft.edu.formbean;

import com.google.common.base.Strings;

public class ScoreInfo {
	private String course;
	private String teacher;
	private String scoreValue;
	private String remark;

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTeacher() {
		return Strings.isNullOrEmpty(teacher)?"":teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(String scoreValue) {
		this.scoreValue = scoreValue;
	}

	public String getRemark() {
		return Strings.isNullOrEmpty(remark)?"":remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

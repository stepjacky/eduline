package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity
@Table(label = "家庭作业")
public class HomeWork extends NoEntity {

	public static final int SUBMITED=1;
	public static final int UNSUBMITED=0;
	
	public static final int SCORED=1;
	public static final int UNSCORED=0;
	@Property
	private String workId;
	private String student;
	private String studentName;
	@Property
	private String teacher;
	private String teacherName;	
	private long firetime;
	private long finishtime;
	private int  offset;
	private int course;
	private String courseName;
	private String lecture;
	private String chapter;
	private String chapterName;
	private String groupId;
	private String groupName;
	private String answerResult;
	
	
    //提交状态 0 未提交,1提交
	private int submitStatus;
	
	//已打分 0 未打分 ,1打分
	private int scoreStatus;
	
	//得分 A B C
	private String score = "N";	
	//老师点评
	private String note;
	
	//单选学生答案
	private String answer;
	//单选标准答案
    private String modelAnswer;
	
	//学生综合题答案
	private String answerDoc;

	//习题
	private String exerciseFile;
	
	
	
	
	
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

	public long getFiretime() {
		return firetime;
	}

	public void setFiretime(long firetime) {
		this.firetime = firetime;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	

	public String getNote() {
		return note;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(int submitStatus) {
		this.submitStatus = submitStatus;
	}


	public String getAnswer() {
		return answer==null?"":answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getAnswerDoc() {
		return answerDoc;
	}

	public void setAnswerDoc(String answerDoc) {
		this.answerDoc = answerDoc;
	}

	

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getModelAnswer() {
		return modelAnswer==null?"":modelAnswer;
	}

	public void setModelAnswer(String modelAnswer) {
		this.modelAnswer = modelAnswer;
	}

	public long getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(long finishtime) {
		this.finishtime = finishtime;
	}

	public int getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(int scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getExerciseFile() {
		return exerciseFile;
	}

	public void setExerciseFile(String exerciseFile) {
		this.exerciseFile = exerciseFile;
	}

	public String getAnswerResult() {
		return answerResult;
	}

	public void setAnswerResult(String answerResult) {
		this.answerResult = answerResult;
	}

	

}

package org.jackysoft.edu.formbean;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

public class InYearScoreDetail {
	private String student;
	private String studentName;
	private String scoreHtml;
	private String courseHtml;
	private String idHtml;
	private String inyear;
	private String grade;
	private String semester;
	private String monthly;
	private float totalScore;
	private float avgScore;
	private int totalSorted;
    private Map<String,String> _inner;
    private static final Comparator<String> compor = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
		
	};
    
    
    
	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}

	public int getTotalSorted() {
		return totalSorted;
	}

	public void setTotalSorted(int totalSorted) {
		this.totalSorted = totalSorted;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}
	

	public String getScoreHtml() {
		return Joiner.on("</td><td>").join(organize().values());
	}

	public void setScoreHtml(String scoreHtml) {
		this.scoreHtml = scoreHtml;
	}

	public String getCourseHtml() {
		return Joiner.on("</th><th>").join(organize().keySet());
	}
	
	public Set<String> getCourseSet(){
		return organize().keySet();
	}
	
	public Collection<String> getScoreSet(){
		return organize().values();
	}

	public void setCourseHtml(String courseHtml) {
		this.courseHtml = courseHtml;
	}

	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getIdHtml() {
		return idHtml;
	}

	public void setIdHtml(String idHtml) {
		this.idHtml = idHtml;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getInyear() {
		return inyear;
	}

	public void setInyear(String inyear) {
		this.inyear = inyear;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Map<String,String> organize() {
		if(_inner!=null) return _inner;
		if(courseHtml==null || scoreHtml==null) return _inner=Maps.newTreeMap(compor);
		List<String> ths  = Splitter.on(',').splitToList(courseHtml);
	    List<String> tds  = Splitter.on(',').splitToList(scoreHtml);
	    _inner = Maps.newTreeMap(compor);
	    for(int i=0;i<ths.size();i++) {
	    	_inner.put(ths.get(i), tds.get(i));
	    }	    
	    return _inner;    
	    
	}
	@Override
	public String toString() {
		
		return "\n"+studentName+"\n"+idHtml+"\n"+courseHtml+"\n"+scoreHtml;
	}
	
}

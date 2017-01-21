package org.jackysoft.edu.formbean;

import java.util.ArrayList;
import java.util.List;

public class ScoreCard {
	private String name;
	private String localName;
	private String gender;
	private String localGender;
	private String bob;
	private String localBob;
	private String level;	
	private List<CourseScore> scores;
    private String grandSystem;
	private String gpaInfo;
	
	
	public ScoreCard() {
		super();
		scores = new ArrayList<>();
				
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocalGender() {
		return localGender;
	}

	public void setLocalGender(String localGender) {
		this.localGender = localGender;
	}

	public String getBob() {
		return bob;
	}

	public void setBob(String bob) {
		this.bob = bob;
	}

	public String getLocalBob() {
		return localBob;
	}

	public void setLocalBob(String localBob) {
		this.localBob = localBob;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	public List<CourseScore> getScores() {
		return scores;
	}

	public void setScores(List<CourseScore> scores) {
		this.scores = scores;
	}

	public String getGrandSystem() {
		return grandSystem;
	}

	public void setGrandSystem(String grandSystem) {
		this.grandSystem = grandSystem;
	}

	public String getGpaInfo() {
		return gpaInfo;
	}

	public void setGpaInfo(String gpaInfo) {
		this.gpaInfo = gpaInfo;
	}

	
}

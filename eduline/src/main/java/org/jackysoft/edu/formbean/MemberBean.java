package org.jackysoft.edu.formbean;

import org.jackysoft.edu.entity.GroupMember;

public class MemberBean extends GroupMember{
	
	private  String  totalSorted;
	private  String  upviolate;
	private  String  dwviolate; 
	private  String  father;
	private  long  firetimef;
	private  String  mother;
	private  long  firetimem;
	
	
	
	
	public String getTotalSorted() {
		return totalSorted;
	}
	public void setTotalSorted(String totalSorted) {
		this.totalSorted = totalSorted;
	}
	public String getUpviolate() {
		return upviolate;
	}
	public void setUpviolate(String upviolate) {
		this.upviolate = upviolate;
	}
	public String getDwviolate() {
		return dwviolate;
	}
	public void setDwviolate(String dwviolate) {
		this.dwviolate = dwviolate;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public long getFiretimef() {
		return firetimef;
	}
	public void setFiretimef(long firetimef) {
		this.firetimef = firetimef;
	}
	public long getFiretimem() {
		return firetimem;
	}
	public void setFiretimem(long firetimem) {
		this.firetimem = firetimem;
	}
	
	
	
	
	
	
	
	
}

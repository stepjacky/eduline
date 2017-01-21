package org.jackysoft.edu.formbean;

import java.time.format.DateTimeFormatter;

import org.jackysoft.edu.entity.Violates;
import org.jackysoft.utils.DateUtils;

public class ViolateBean {
     private String type;
     private String value;
     private String gradeName;
     private String remark;
     private String date;
     
     
     
     
	public ViolateBean(Violates bean) {
		if(bean==null) return;
		this.type = bean.getAffirmative()>0?"奖":"罚";
		this.value = bean.getScoreValue()+"";
		this.gradeName = bean.getGradeName();
		this.remark = bean.getContent();
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d");
		this.date = DateUtils.fromMillis(bean.getFireTime()).format(format);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
     
     
     
}

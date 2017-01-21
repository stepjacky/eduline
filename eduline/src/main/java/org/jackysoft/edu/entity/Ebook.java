package org.jackysoft.edu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.MediaFile;

@Table(label = "电子书")
public class Ebook  implements MediaFile{
	
	@Column(id=true,idStrategy=IdStrategy.UUID,list=false,input=false)
	private String id;
	
	@Column(label="名称")
	private String name;
	
	@Column(label="上传时间")	
	private long firetime;
	
	@Column(label="名称",list=false,input=false)	
	private String realPath;
	
	@Column(label="文件类型",input=false)
	private String fileType;
	
	@Column(label="封面")
	private String coverPath;
	
	@Column(label="豆瓣链接")
	private String douban;
	
	@Column(label="简介")
	private String remark;
	
	@Column(label="标签")
	private String tags;
	
	@Column(label="点击次数")
	private int clicknum;
	
	@Column(label="大小",input=false)	
	private long size;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getFiretime() {
		return firetime;
	}

	public void setFiretime(long firetime) {
		this.firetime = firetime;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public long getContentLength() {
		 return this.getSize();
	}

	@Override
	public String getContentType() {
		return this.getFileType();
	}

	@Override
	public String getFilename() {
		return this.getName();
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getDouban() {
		return douban;
	}

	public void setDouban(String douban) {
		this.douban = douban;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getClicknum() {
		return clicknum;
	}

	public void setClicknum(int clicknum) {
		this.clicknum = clicknum;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	

}

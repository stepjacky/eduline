package org.jackysoft.edu.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.formbean.Ztreeable;

@Table(label = "讲义章节")
public class NoteChapter implements Ztreeable {
	
	public final static Map<String,String> TYPED_FILE = new HashMap<>();
	static{
		TYPED_FILE.put("pptFile","演示文档");		
		TYPED_FILE.put("mp3File","音频资料");		
		TYPED_FILE.put("keysFile","重点讲解");		
		TYPED_FILE.put("exerciseFile","本章习题");		
		TYPED_FILE.put("anwserFile","本章习题答案");
	}
	
	@Column(id=true,idStrategy = IdStrategy.UUID,list=false,input=false)
	private String id;
	
	@Column(label="名称")
	private String name;
	
	@Column(label="所属讲义")
	private String noteId;
	
	@Column(label="父章节编号")
	private String parent;
	
	@Column(label="父名称")
	private String parentName;

	@Column()
	private String isParent;
	
	
	
	@Column(list = false, input = false)
	private String pptFile;
	@Column(list = false, input = false)
	private String mp3File;
	@Column(list = false, input = false)
	private String keysFile;
	@Column(list = false, input = false)
	private String exerciseFile;
	@Column(list = false, input = false)
	private String anwserFile;
	
	@Column()
	private String anwserHead;
	
	@Column
	private int anwserNum;
	
	@Column()
	private int sort;
	
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

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getPptFile() {
		return pptFile;
	}

	public void setPptFile(String pptFile) {
		this.pptFile = pptFile;
	}

	public String getMp3File() {
		return mp3File;
	}

	public void setMp3File(String mp3File) {
		this.mp3File = mp3File;
	}

	public String getKeysFile() {
		return keysFile;
	}

	public void setKeysFile(String keysFile) {
		this.keysFile = keysFile;
	}

	public String getExerciseFile() {
		return exerciseFile;
	}

	public void setExerciseFile(String exerciseFile) {
		this.exerciseFile = exerciseFile;
	}

	public String getAnwserFile() {
		return anwserFile;
	}

	public void setAnwserFile(String anwserFile) {
		this.anwserFile = anwserFile;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getAnwserHead() {
		return anwserHead;
	}

	public void setAnwserHead(String anwserHead) {
		this.anwserHead = anwserHead;
	}

	@Override
	public ZtreeNode toZtreeNode() {
		ZtreeNode node = new ZtreeNode();
		node.setId(getId());
		node.setName(getName());
		node.setParent(getParent());
		node.setSort(getSort());
		node.setIsParent(getIsParent());
		node.attr("heads", this.getAnwserHead());
		node.attr("tails",this.getAnwserNum()+"");
		node.attr("keysFile", keysFile);
		node.attr("exerciseFile", exerciseFile);
		node.attr("mp3File", mp3File);
		node.attr("pptFile", pptFile);
		node.attr("anwserFile", anwserFile);
		return node;
	}

	public int getAnwserNum() {
		return anwserNum;
	}

	public void setAnwserNum(int anwserNum) {
		this.anwserNum = anwserNum;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	
	
}
package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.MediaFile;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.annotations.Embedded;

@Table(label = "教学资源")
public class Resource extends NoEntity implements MediaFile {

    /**
     * 公共或者个人
     * */
    String commontype;

    /**
     * 类别
     * */
    String styletype;

    /**
     * 内容类别
     * */
    String filetype;

    String name;

    String realpath;

    //所属课本
    String textbook;

    //文件后缀
    String suffix;

    //所属章节
    String chapter;

    //课程
    int course;

    //年级
    int grade;

    //拥有者
    @Embedded
    NameValue owner;

    long modifyDate;

    int size;


    public String getCommontype() {
        return commontype;
    }

    public void setCommontype(String commontype) {
        this.commontype = commontype;
    }

    public String getStyletype() {
        return styletype;
    }

    public void setStyletype(String styletype) {
        this.styletype = styletype;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public NameValue getOwner() {
        return owner;
    }

    public void setOwner(NameValue owner) {
        this.owner = owner;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //method for download
    @Override
    public long getContentLength() {
        return size;
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }

    @Override
    public String getFilename() {
        return name+(suffix.startsWith(".")?suffix:suffix.substring(1));
    }

}



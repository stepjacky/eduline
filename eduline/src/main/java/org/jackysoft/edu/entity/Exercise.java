package org.jackysoft.edu.entity;


import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.formbean.MediaFile;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.annotations.Embedded;

@Table(label = "习题")
public class Exercise extends NoEntity implements MediaFile {

    String commontype;

    String name;

    String textbook;

    String chapter;

    //拥有者
    @Embedded
    NameValue owner;
    //课程
    int course;

    //年级
    int grade;

    //习题路径
    String realpath;

    //选择题答案
    String choice;

    //解答题路径
    String explain;

    //选择题数目
    int csize;

    //解答题数目
    int esize;

    int size;

    long modifyDate;
    public String getCommontype() {
        return commontype;
    }

    public void setCommontype(String commontype) {
        this.commontype = commontype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public NameValue getOwner() {
        return owner;
    }

    public void setOwner(NameValue owner) {
        this.owner = owner;
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



    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getCsize() {
        return csize;
    }

    public void setCsize(int csize) {
        this.csize = csize;
    }

    public int getEsize() {
        return esize;
    }

    public void setEsize(int esize) {
        this.esize = esize;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

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
        return name;
    }
}

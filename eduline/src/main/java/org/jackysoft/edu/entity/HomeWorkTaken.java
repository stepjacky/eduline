package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.HomeworkConstant;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Table(label = "做作业")
public class HomeWorkTaken {

    //做作业学生
    @Embedded
    NameValue student;

    //批阅老师
    @Embedded
    NameValue teacher;

    //作业编号
    @Reference
    HomeWork homework;

    //学生选择题答案
    String choice;

    //学生解答题答案
    String explain;

    //作业状态
    String status = HomeworkConstant.HoweworkStatus.unsubmit.name();
    //得分
    float score = 0f;


    //批阅时间
    long readDate;

    //交作业时间
    long submitDate;

    //逾期天数
    int exceeds;

    //老师点评
    String yelp;


    public NameValue getStudent() {
        return student;
    }

    public void setStudent(NameValue student) {
        this.student = student;
    }

    public NameValue getTeacher() {
        return teacher;
    }

    public void setTeacher(NameValue teacher) {
        this.teacher = teacher;
    }

    public HomeWork getHomework() {
        return homework;
    }

    public void setHomework(HomeWork homework) {
        this.homework = homework;
    }

    public String getChoice() {
        return choice==null?"":choice;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getReadDate() {
        return readDate;
    }

    public void setReadDate(long readDate) {
        this.readDate = readDate;
    }

    public long getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(long submitDate) {
        this.submitDate = submitDate;
    }

    public String getYelp() {
        return yelp;
    }

    public void setYelp(String yelp) {
        this.yelp = yelp;
    }

    public int getExceeds() {
        return exceeds;
    }

    public void setExceeds(int exceeds) {
        this.exceeds = exceeds;
    }
}

package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Table(label = "学生持有作业")
public class HomeWorkTaken {

    //做作业学生
    @Embedded
    NameValue student;

    //批阅老师
    @Embedded
    NameValue teacher;


    //作业编号
    String homework;

    //学生选择题答案
    String choice;

    //学生解答题答案
    List<String> explains;

    //作业状态
    String status ;

    //总得分
    float score = 0f;

    List<Integer> explainscores;

    //批阅时间
    long readdate;

    //交作业时间
    long submitdate;

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

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
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

    public long getReaddate() {
        return readdate;
    }

    public void setReaddate(long readdate) {
        this.readdate = readdate;
    }

    public long getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(long submitdate) {
        this.submitdate = submitdate;
    }

    public String getYelp() {
        return yelp;
    }

    public void setYelp(String yelp) {
        this.yelp = yelp;
    }

    public List<Integer> getExplainscores() {
        return explainscores;
    }

    public void setExplainscores(List<Integer> explainscores) {
        this.explainscores = explainscores;
    }
}

package org.jackysoft.edu.entity;

import org.apache.poi.xssf.usermodel.ListAutoNumber;
import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Embedded;

import java.util.List;
import java.util.Set;

@Table(label = "作业")
public class HomeWork extends NoEntity {

    //名称
    String name;

    //作业内容
    String content;

    //所涉及的习题
    String exercise;

    //班级
    List<String> groups;

    int course;

    //选择题答案
    String choice;

    //解答题答案
    String explain;


    //发布人
    @Embedded
    NameValue teacher;

     //起始日期
    long startdate;

    //截至日期
    long deaddate;

    //总共接受作业人数
    int amount;

    //已交作业人数
    int amountsubmited;

    String status;

    //选择题数目
    int choiceAmount;

    //解答题数目
    int explainAmount;

    String exercisePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
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

    public NameValue getTeacher() {
        return teacher;
    }

    public void setTeacher(NameValue teacher) {
        this.teacher = teacher;
    }


    public long getStartdate() {
        return startdate;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public long getDeaddate() {
        return deaddate;
    }

    public void setDeaddate(long deaddate) {
        this.deaddate = deaddate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountsubmited() {
        return amountsubmited;
    }

    public void setAmountsubmited(int amountsubmited) {
        this.amountsubmited = amountsubmited;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getChoiceAmount() {
        return choiceAmount;
    }

    public void setChoiceAmount(int choiceAmount) {
        this.choiceAmount = choiceAmount;
    }

    public int getExplainAmount() {
        return explainAmount;
    }

    public void setExplainAmount(int explainAmount) {
        this.explainAmount = explainAmount;
    }

    public String getExercisePath() {
        return exercisePath;
    }

    public void setExercisePath(String exercisePath) {
        this.exercisePath = exercisePath;
    }
}

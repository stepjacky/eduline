package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Embedded;

import java.util.Set;

@Table(label = "作业")
public class HomeWork extends NoEntity {

    //名称
    String name;

    //作业内容
    String content;

    //所涉及的习题
    Set<String> exercises;

    //班级
    Set<String> groups;


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

    public Set<String> getExercises() {
        return exercises;
    }

    public void setExercises(Set<String> exercises) {
        this.exercises = exercises;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
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
}

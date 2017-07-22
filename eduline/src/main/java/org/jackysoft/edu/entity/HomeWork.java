package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Embedded;

import java.util.Set;

@Table(label = "作业")
public class HomeWork extends NoEntity {

    //名称
    String name;

    //作业内容
    String contentText;

    //所涉及的习题
    Set<NameValue> exercises;

    //班级
    Set<String> groups;


    //选择题答案
    String choice;

    //解答题答案
    String explain;


    //发布人
    @Embedded
    NameValue teacher;

    //发布日期
    long publishDate;

     //起始日期
    long startDate;

    //截至日期
    long deadDate;

    //总共接受作业人数
    int amount;

    //已交作业人数
    int amountSubmited;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public Set<NameValue> getExercises() {
        return exercises;
    }

    public void setExercises(Set<NameValue> exercises) {
        this.exercises = exercises;
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

    public NameValue getTeacher() {
        return teacher;
    }

    public void setTeacher(NameValue teacher) {
        this.teacher = teacher;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(long deadDate) {
        this.deadDate = deadDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountSubmited() {
        return amountSubmited;
    }

    public void setAmountSubmited(int amountSubmited) {
        this.amountSubmited = amountSubmited;
    }


    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}

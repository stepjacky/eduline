package org.jackysoft.edu.entity;


import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.HomeworkConstant;

@Table(label = "习题")
public class Exercise extends NoEntity{

    String commonType = HomeworkConstant.CommonType.personal.name();
    String name;

    String chapter;

    //拥有者
    String owner;
    //课程
    int course;

    //年级
    int grade;

    String realpath;

    //选择题答案
    String choice;

    //解答题答案
    String explain;

    //选择题数目
    int choiceNumber;
    //解答题数目
    int explainNumber;

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

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public String getCommonType() {
        return commonType;
    }

    public void setCommonType(String commonType) {
        this.commonType = commonType;
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

    public int getChoiceNumber() {
        return choiceNumber;
    }

    public void setChoiceNumber(int choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    public int getExplainNumber() {
        return explainNumber;
    }

    public void setExplainNumber(int explainNumber) {
        this.explainNumber = explainNumber;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

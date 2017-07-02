package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;

@Table(label = "课本")
public class Textbook  extends NoEntity{
    String name;
    int grade = 7;
    int course;

    /**
     * 章节根ID
     * */
    String chapter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}

package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.mongodb.morphia.annotations.Embedded;

@Table(label = "课本")
public class Textbook  extends NoEntity{


    String name;

    //年级
    @Embedded
    NameValue grade = new NameValue("7");

    //课程
    @Embedded
    NameValue course;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public NameValue getGrade() {
        return grade;
    }

    public void setGrade(NameValue grade) {
        this.grade = grade;
    }

    public NameValue getCourse() {
        return course;
    }

    public void setCourse(NameValue course) {
        this.course = course;
    }

}

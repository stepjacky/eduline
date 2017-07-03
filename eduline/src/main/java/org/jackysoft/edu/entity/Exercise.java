package org.jackysoft.edu.entity;


import org.jackysoft.edu.annotation.Table;

@Table(label = "习题")
public class Exercise extends NoEntity{

    String name;
    String chapter;
    String realpath;


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
}

package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.ResourceConstant;
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
    Set<String> classId;

    //作业状态
    String status = ResourceConstant.HoweworkStatus.unsubmit.getName();

    //发布人
    @Embedded
    NameValue teacher;

    //发布日期
    long publishDate;

     //起始日期
    long startDate;

    //截至日期
    long deadDate;


}

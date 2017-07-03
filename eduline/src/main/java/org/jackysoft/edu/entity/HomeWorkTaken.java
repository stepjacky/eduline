package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.ResourceConstant;
import org.mongodb.morphia.annotations.Embedded;

@Table(label = "做作业")
public class HomeWorkTaken {

    //状态
    String status = ResourceConstant.HoweworkStatus.unsubmit.getName();

    //做作业者
    @Embedded
    NameValue student;

    //批阅老师
    @Embedded
    NameValue teacher;

    //作业
    String homework;

    //选择题答案
    String choice;

    //解答题答案
    String explain;

    //得分
    float score = 0f;


    //批阅时间
    long readDate;

    //交作业时间
    long submitDate;

    //逾期天数
    int exceedDays;

    //老师点评
    String yelp;




}

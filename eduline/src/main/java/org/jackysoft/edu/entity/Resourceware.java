package org.jackysoft.edu.entity;

import org.jackysoft.edu.annotation.Table;
import org.jackysoft.utils.HomeworkConstant;
import org.mongodb.morphia.annotations.Embedded;

@Table(label = "教学资源")
public class Resourceware extends NoEntity{

    /**
     * 公共或者个人
     * */
    String commonType  = HomeworkConstant.CommonType.personal.name();

    /**
     * 类别
     * */
    String styleType  = HomeworkConstant.ContentStyle.course.name();

    /**
     * 内容类别
     * */
    String detailType  = HomeworkConstant.DetailType.word.name();

    String name;
    String realpath;

    @Embedded
    SysUser owner;

    long modifyDate;

    int size;



    public String getCommonType() {
        return commonType;
    }

    public void setCommonType(String commonType) {
        this.commonType = commonType;
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public SysUser getOwner() {
        return owner;
    }

    public void setOwner(SysUser owner) {
        this.owner = owner;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}



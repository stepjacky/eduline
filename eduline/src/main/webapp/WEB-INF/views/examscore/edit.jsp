<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="static/css/examscore/edit.css" rel="stylesheet">
<p class="lead">
    编辑<span class="label label-info">成绩</span>
</p>
<form class="form-horizontal">
    <div class="form-group">
        <label for="student" class="col-sm-2 control-label">学生ID</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="student" name="student"
                   value="${bean.student}" placeholder="学生ID">
        </div>
    </div>
    <div class="form-group">
        <label for="studentName" class="col-sm-2 control-label">学生姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="studentName"
                   name="studentName" value="${bean.studentName}" placeholder="学生姓名">
        </div>
    </div>
    <div class="form-group">
        <label for="year" class="col-sm-2 control-label">年份</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="year" name="year"
                   value="${bean.year}" placeholder="年份">
        </div>
    </div>
    <div class="form-group">
        <label for="semester" class="col-sm-2 control-label">学期[上/下]</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="semester" name="semester"
                   value="${bean.semester}" placeholder="学期[上/下]">
        </div>
    </div>
    <div class="form-group">
        <label for="course" class="col-sm-2 control-label">课程编号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="course" name="course"
                   value="${bean.course}" placeholder="课程编号">
        </div>
    </div>
    <div class="form-group">
        <label for="courseName" class="col-sm-2 control-label">课程名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="courseName"
                   name="courseName" value="${bean.courseName}" placeholder="课程名称">
        </div>
    </div>
    <div class="form-group">
        <label for="monthly" class="col-sm-2 control-label">月考序号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="monthly" name="monthly"
                   value="${bean.monthly}" placeholder="月考序号">
        </div>
    </div>
    <div class="form-group">
        <label for="monthlyName" class="col-sm-2 control-label">月考名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="monthlyName"
                   name="monthlyName" value="${bean.monthlyName}" placeholder="月考名称">
        </div>
    </div>
    <div class="form-group">
        <label for="scoreValue" class="col-sm-2 control-label">分数</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="scoreValue"
                   name="scoreValue" value="${bean.scoreValue}" placeholder="分数">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-info persisteFormItem">
                <span class="glyphicon glyphicon-floppy-saved"></span> 保存
            </button>
        </div>
    </div>
</form>
<script src="static/scripts/examscore/edit.js"></script>
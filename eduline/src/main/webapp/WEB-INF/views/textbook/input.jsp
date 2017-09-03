<%--
  Created by Jackysoft.Inc on 2017/7/18 0018 23:15.
  User: qujiakang@126.com  
  Date: 2017/7/18 0018 23:15  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<div class="panel panel-primary">
    <div class="panel-heading">添加课本</div>
    <div class="panel-body" id="input-item">

        <div class="form-group">
            <label class=" control-label">名称 </label>
            <input type="text" class="form-control" v-model="name" placeholder="课本名称">

        </div>
        <div class="form-group">
            <label class=" control-label">年级</label>
            <select v-model="grade" class="form-control">
                <option disabled value="">请选择年级</option>
                <c:forEach items="${grades}" var="grade">
                    <option v-bind:value="{'name':'${grade.name}','value':'${grade.id}'}">${grade.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">课程</label>
            <select v-model="course" class="form-control">
                <option disabled value="">请选择课程</option>
                <c:forEach items="${courses}" var="course">
                    <option
                            v-bind:value="{'name':'${course.name}','value':'${course.id}'}">${course.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">


            <button class="btn btn-info action-save" type="button">
                <i class="icon-save"></i>保存
            </button>

        </div>
    </div>
</div>

<script src="static/scripts/textbook/input.js"></script>


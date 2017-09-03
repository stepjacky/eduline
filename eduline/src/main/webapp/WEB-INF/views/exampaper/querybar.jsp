<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="panel panel-info">
    <div class="panel-heading">选择标签</div>
    <div class="panel-body">
        <ol class="breadcrumb">
            <li><span class="label label-default">年份</span></li>
            <c:forEach var="y" begin="2000" end="2016">
                <li><a
                        href="/exampaper/query/0?query=course`${course};year`${y};category`${category}">${y}</a></li>
            </c:forEach>


        </ol>

        <ol class="breadcrumb">
            <li><span class="label label-default">分类</span></li>

            <c:forEach items="${categories}" var="cate">
                <li><a
                        href="/exampaper/query/0?query=course`${course};year`${year};category`${cate}">${cate}</a></li>
            </c:forEach>
        </ol>

        <ol class="breadcrumb">
            <li><span class="label label-default">课程</span></li>

            <c:forEach items="${courses}" var="course">
                <li><a
                        href="/exampaper/query/0?query=course`${course.id};year`${year};category`${category}">${course.name}</a>
                </li>
            </c:forEach>
        </ol>
    </div>
    <div class="panel-footer">
        <span class="label label-primary">${year}</span> <span
            class="label label-success">${category}</span> <span
            class="label label-info">${courseName}</span>
    </div>
</div>
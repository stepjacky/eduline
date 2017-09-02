<%--
  Created by Jackysoft.Inc on 2017/7/31 0031 22:40.
  User: qujiakang@126.com  
  Date: 2017/7/31 0031 22:40  
--%>
<%@ page pageEncoding="UTF-8" %><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %><%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %><%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<ul class="layui-nav layui-nav-tree" lay-filter="test">
    <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">班级管理</a>
        <dl class="layui-nav-child">
            <dd><a
                    href="/groupmember/query/0?query=teacher`${sysUser.username}&group=groupId&ajax=false">设置班级</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">资源&作业</a>
        <dl class="layui-nav-child">
            <dd><a href="/resource/listresource?commontype=personal&textbook=${sysUser.textbook}">教学资源</a></dd>
            <dd><a href="/exercise/listexercise?commontype=personal&textbook=${sysUser.textbook}">习题资源</a></dd>

            <dd><a href="/exercise/homework-exercise?&textbook=${sysUser.textbook}">布置作业</a></dd>
            <dd><a href="/homework/teacher/timeline">我的作业</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">成绩&行为</a>
        <dl class="layui-nav-child">
            <dd><a href="/examscore/score/0">
                成绩录入 </a></dd>

            <dd><a href="/examscore/input">
                成绩查询 </a></dd>
            <dd role="separator" class="divider"></dd>
            <dd><a href="/violates/violate/0">行为规范录入</a></dd>
            <dd><a
                    href="/violates/query/0?order=fireTime desc"> 行为规范查询 </a></dd>

        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">打印相关</a>
        <dl class="layui-nav-child">
            <dd><a href="/sysuser/xpconfirm/input">
                在读证明打印 </a></dd>
            <dd><a href="/examscore/score/print">
                成绩单打印 </a></dd>
            <dd><a
                    href="/examscore/graduates/inyear"> 学生评语打印 </a></dd>

            <dd><a href="/violates/userinput">
                行为规范打印 </a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">相关图书</a>
        <dl class="layui-nav-child">
            <dd><a href="/ebook/pager/0?ajax=false">图书管理</a></dd>
            <dd><a href="/ebook/student/0?ajax=false">图书浏览</a></dd>


        </dl>
    </li>
</ul>
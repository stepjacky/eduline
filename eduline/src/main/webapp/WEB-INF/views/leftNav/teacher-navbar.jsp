<%--
  Created by Jackysoft.Inc on 2017/7/31 0031 22:40.
  User: qujiakang@126.com  
  Date: 2017/7/31 0031 22:40  
--%>
<%@ page pageEncoding="UTF-8" %>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        班级管理 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a
                href="/groupmember/query/0?query=teacher`${sysUser.username}&group=groupId&ajax=false">设置班级</a></li>

    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        资源&作业 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="/resource/listresource?commontype=personal">教学资源</a></li>
        <li><a href="/exercise/listexercise?commontype=personal">习题资源</a></li>

        <li><a href="/exercise/homework-exercise">布置作业</a></li>
        <li><a href="/homework/teacher/timeline">我的作业</a></li>
        
    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        成绩&行为 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="/examscore/score/0">
            成绩录入 </a></li>

        <li><a href="/examscore/input">
            成绩查询 </a></li>
        <li role="separator" class="divider"></li>
        <li><a href="/violates/violate/0">行为规范录入</a>
        </li>
        <li><a
                href="/violates/query/0?order=fireTime desc"> 行为规范查询 </a></li>
    </ul>
    
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        打印相关 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="/sysuser/xpconfirm/input">
            在读证明打印 </a></li>
        <li><a href="/examscore/score/print">
            成绩单打印 </a></li>
        <li><a
                href="/examscore/graduates/inyear"> 学生评语打印 </a></li>

        <li><a href="/violates/userinput">
            行为规范打印 </a></li>
        
    </ul>
</li>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        图书<span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="/ebook/pager/0?ajax=false">图书管理</a></li>
        <li><a href="/ebook/student/0?ajax=false">图书浏览</a></li>
        
    </ul>
</li>

<%--
  Created by Jackysoft.Inc on 2017/7/31 0031 23:22.
  User: qujiakang@126.com  
  Date: 2017/7/31 0031 23:22  
--%>
<%@ page pageEncoding="UTF-8" %>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        资源&作业 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a
                href="/lecturenotes/lectures/student/0">教学资源</a></li>
        <li><a
                href="/homework/student/unsubmited/0">我的作业</a></li>
        <li><a
                href="/exampaper/student/0?ajax=false">历年考题</a></li>
        <li><a
                href="/ebook/student/0?ajax=false">图书浏览</a></li>

    </ul>
</li>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        规范&成绩 <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a
                href="/violates/student/0?ajax=false">行为规范</a></li>
        <li><a
                href="/examscore/student/score/${sysUser.username}/0">考试成绩</a></li>
    </ul>

</li>

<%--
  Created by Jackysoft.Inc on 2017/7/31 0031 23:22.
  User: qujiakang@126.com  
  Date: 2017/7/31 0031 23:22  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<ul class="layui-nav layui-nav-tree" lay-filter="test">
    <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">资源&作业</a>
        <dl class="layui-nav-child">
            <dd><a
                    href="/lecturenotes/lectures/student/0">教学资源</a></dd>

            <dd><a
                    href="/exampaper/student/0?ajax=false">历年考题</a></dd>
            <dd><a
                    href="/ebook/student/0?ajax=false">图书浏览</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;">规范&成绩</a>
        <dl class="layui-nav-child">
            <dd><a
                    href="/violates/student/0?ajax=false">行为规范</a></dd>
            <dd><a
                    href="/examscore/student/score/${sysUser.username}/0">考试成绩</a></dd>
        </dl>
    </li>
</ul>
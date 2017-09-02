<%@ page pageEncoding="UTF-8" %><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %><%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %><%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<ul class="layui-nav layui-nav-tree"  lay-filter="test">
    <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">系统管理</a>
        <dl class="layui-nav-child">
    <dd><a
            href="/sysuser/pager/0?ajax=false">用户管理</a></dd>
    <dd><a
            href="/textbook/pager/0?ajax=false">课本管理</a></dd>
    <dd><a
            href="/course/pager/0?ajax=false">课程管理</a></dd>
    <dd><a href="/courseingrade/query/0">课程设置</a>
    </dd>
    <dd><a href="/grade/pager/0?ajax=false">年级管理</a>
    </dd>
    <dd><a
            href="/examscore/pager/0?ajax=false">成绩管理</a></dd>
    <dd><a
            href="/examscore/anyway/scoreResult">成绩排名</a></dd>
        </dl>
    </li>
</ul>
<%--
  Created by Jackysoft.Inc on 2017/7/31 0031 23:27.
  User: qujiakang@126.com  
  Date: 2017/7/31 0031 23:27  
--%>
<%@ page pageEncoding="UTF-8" %><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %><%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %><%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<ul class="layui-nav layui-nav-tree"  lay-filter="test">
    <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">规范&成绩</a>
        <dl class="layui-nav-child">
    <dd><a
            href="/examscore/student/score/${sysUser.children}/0">考试成绩</a></dd>

    <dd><a
            href="/violates/parents/0?ajax=false">行为统计</a></dd>
        </dl>
    </li>
</ul>
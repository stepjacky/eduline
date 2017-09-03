<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<ul class="layui-timeline">
<c:forEach var="y" begin="0" end="10">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">
                <span class="layui-badge-dot layui-bg-green"></span>
                <a class="action-year-score" href='/examscore/gradeuser?inyear=${y+2009}'>${y+2009}届</a>
            </h3>
            <p>
               查询成绩
            </p>
        </div>
    </li>
</c:forEach>
</ul>
<script src="static/scripts/examscore/preinput.js"></script>

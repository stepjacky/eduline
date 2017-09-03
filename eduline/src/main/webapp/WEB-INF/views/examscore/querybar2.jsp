<link href="static/css/examscore/input.css" rel="stylesheet">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">浏览成绩排名表</h3>
    </div>

    <table class="table">
        <c:forEach var="y" begin="0" end="7">
            <c:if test="${y%5==0}">
                <tr>
            </c:if>
            <td><a href='/examscore/gradeuser?inyear=${y+2009}'> <span
                    class="label label-info"> ${y+2009}届 </span>
            </a></td>
            <c:if test="${(y+1)%5==0}">
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>

<script src="static/scripts/examscore/preinput.js"></script>
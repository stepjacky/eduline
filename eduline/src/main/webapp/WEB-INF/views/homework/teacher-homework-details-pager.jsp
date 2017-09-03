<%@include file="../pageHead.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/homework/pager.css" rel="stylesheet">
<%@ include file="teacher-bar.jsp" %>
<div class="table-responsive">

    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>班级</th>
            <th>课程</th>
            <th>章节</th>
            <th>学生</th>
            <th>布置时间</th>
            <th>有效日期</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.groupName}</td>
                <td>${item.courseName}</td>
                <td>${item.chapterName}</td>
                <td>${item.studentName}[${item.student}]</td>
                <td>${jxf:datetimeFormat(item.firetime)}</td>
                <td>${item.offset}天</td>

                <td>
                    <c:choose>
                        <c:when test="${type=='unscored' }">
                            <a class="btn btn-info btn-xs scoreItem"
                               href="/homework/teacher/homework/card/${item.id}?workId=${workId }">
                                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
                            </a>
                        </c:when>
                        <c:when test="${type=='scored' }">
                            <a class="btn btn-info btn-xs scoreItem"
                               href="/homework/forall/card/scored/${item.id}?workId=${workId }">
                                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <i class="fa fa-calendar-check-o" aria-hidden="true"></i>
                        </c:otherwise>
                    </c:choose>


                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="7">
                ${pager.pagination}
            </td>
        </tr>
        </tfoot>
    </table>
</div>

<%@include file="../pageFoot.jsp" %>
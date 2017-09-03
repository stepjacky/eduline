<%@include file="../pageHead.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/homework/pager.css" rel="stylesheet">
<%@ include file="student-bar.jsp" %>
<div class="table-responsive">

    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>班级</th>
            <th>课程</th>
            <th>章节</th>
            <th>老师</th>
            <th>布置时间</th>
            <th>得分</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.groupName}</td>
                <td>${item.courseName}</td>
                <td>${item.chapterName}</td>
                <td>${item.teacherName}</td>
                <td>${jxf:datetimeFormat(item.firetime)}</td>
                <td>${item.score}</td>

                <td>
                    <c:choose>
                        <c:when test="${type=='unsubmited'}">
                            <a href="/homework/student/homework/card/${item.id}" class="btn btn-primary btn-xs preWork"
                               key="${item.id}">
                                <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                            </a>

                        </c:when>
                        <c:when test="${type=='unscored'}">
                            <input name="answerName" class="answerName" type="radio" value="${item.id}">

                        </c:when>
                        <c:when test="${type=='scored'}">
                            <a class="btn btn-info btn-xs scoreItem" href="/homework/forall/card/scored/${item.id}">
                                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
                            </a>


                        </c:when>


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
<input type="file" id="fileupload" name="answerDoc"/>
<div class="docName">
    <img style="width:400px;height:300px">
</div>
<script src="static/scripts/homework/prehomework.js"></script>
<%@include file="../pageFoot.jsp" %>
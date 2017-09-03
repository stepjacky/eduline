<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/groupmember/query.css" rel="stylesheet">
<div class="table-responsive">
     <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>录入</th>
            <th>年级</th>
            <th>课程</th>
            <th>名称</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td><a class="btn btn-primary btn-xs action-input-score"
                       href="/examscore/scoreinput?groupId=${item.groupId}"> <span
                        class="glyphicon glyphicon-new-window"></span>成绩录入
                </a></td>
                <td>${item.gradeName}</td>
                <td>${item.courseName}</td>
                <td>${item.name}</td>


            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<script src="static/scripts/groupmember/query.js"></script>

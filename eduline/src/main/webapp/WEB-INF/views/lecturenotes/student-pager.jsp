<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/lecturenotes/pager.css" rel="stylesheet">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>年级</th>
            <th>课程</th>
            <th>名称</th>
            <th>发布日期</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.gradeName}</td>
                <td>${item.courseName}</td>
                <td>${item.ownerName}</td>
                <td>${jxf:datetimeFormat(item.firetime)}</td>
                <td>
                    <button type="button" data-key="${item.id}" data-name="${item.name}"
                            class="btn btn-info btn-xs expandItem"><i class="fa fa-expand" aria-hidden="true"></i>
                    </button>


                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="6">
                ${pager.pagination}
            </td>
        </tr>
        </tfoot>
    </table>
</div>
<script>
    var dtype = 'student';
</script>
<script src="static/scripts/lecturenotes/pager.js"></script>

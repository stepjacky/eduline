<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/userevent/pager.css" rel="stylesheet">
<div class="table-responsive">
    <div>
        <a class="btn btn-info" href="/userevent/input">返回</a>
    </div>
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>名称</th>
            <th>重复开始日期</th>
            <th>重复结束日期</th>
            <th>周天</th>
            <th>当天起始时间</th>
            <th>当天结束时间</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${jxf:dateFormat(item.repeatStart)}</td>
                <td>${jxf:dateFormat(item.repeatEnd)}</td>
                <td>${jxf:weekly(item.dayOfweek)}</td>
                <td>${jxf:datetimeFormat(item.starttime)}</td>
                <td>${jxf:datetimeFormat(item.endtime)}</td>
                <td>

                    <button type="button" class="btn btn-xs btn-danger removeAny"
                            key="${item.id}">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="7">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<script src="static/scripts/userevent/pager.js"></script>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/sysuser/pager.css" rel="stylesheet">
<%@ include file="querybar.jsp" %>
<div class="table-responsive">
    <div class="btn-group">
        <button type="button" class="btn btn-primary inputItem">
            <span class="glyphicon glyphicon-new-window"></span>新增用户
        </button>
        <button type="button" class="btn btn-info upgradeGrade">
            <span class="glyphicon glyphicon-retweet"></span>更新年级
        </button>

    </div>
    <input type="file" name="file" id="fileupload">
    <div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" >
            <span class="msg"></span>
        </div>
    </div>
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生</th>
            <th>类型</th>
            <th>年级</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.username}</td>
                <td>${item.nickname}</td>
                <td>${jxf:sex(item.sex)}</td>
                <td>${jxf:dateFormat(item.birthday)}</td>
                <td>${jxf:userType(item.userType)}</td>
                <td>${jxf:grade(item.grade)}</td>
                <td>

                    <button type="button" class="btn btn-xs btn-info editItem"
                            key="${item.username}">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-xs  btn-danger removeItem"
                            key="${item.username}">
                    </button>
                    <button type="button" class="btn btn-xs  btn-warning resetItem"
                            key="${item.username}">
                        <span class="glyphicon glyphicon-scissors" aria-hidden="true"></span>
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
<div id="inputArea" class="table-responsive"></div>
<script src="static/scripts/sysuser/pager.js"></script>

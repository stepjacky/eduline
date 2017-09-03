<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/urlpattern/query.css" rel="stylesheet">
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">查询资源</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline">
            <div class="form-group">
                <label for="name">名称</label> <input type="text" class="form-control"
                                                    name="name" id="name" placeholder="输入名称">
            </div>
            <button type="button" class="btn btn-primary queryItem">
                <span class="glyphicon glyphicon-search"> </span> 查询
            </button>
        </form>
    </div>
</div>

<div class="table-responsive">
    <button type="button" class="btn btn-primary inputItem">
        <span class="glyphicon glyphicon-new-window"></span>新增资源
    </button>
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>URL模式</th>
            <th>名称</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.urlPattern}</td>
                <td>${item.name}</td>
                <td class="btn-group">

                    <button type="button" class="btn btn-info editItem"
                            key="${item.urlPattern}">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-danger removeItem"
                            key="${item.urlPattern}">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="3">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<div id="inputArea" class="table-responsive"></div>

<script src="static/scripts/urlpattern/query.js"></script>

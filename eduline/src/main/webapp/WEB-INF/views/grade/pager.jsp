<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/grade/pager.css" rel="stylesheet">

<div class="table-responsive">
    <button type="button" class="btn btn-primary inputItem">
        <span class="glyphicon glyphicon-new-window"></span>新增年级
    </button>
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>名称</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>

                    <button type="button" class="btn btn-xs btn-info editItem"
                            key="${item.id}">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-xs  btn-danger removeItem"
                            key="${item.id}">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="2">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<div id="inputArea" class="table-responsive"></div>

<script src="static/scripts/grade/pager.js"></script>

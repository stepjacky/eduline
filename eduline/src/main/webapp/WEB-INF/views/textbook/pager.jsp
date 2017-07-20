<%--
  Created by Jackysoft.Inc on 2017/7/18 0018 23:16.
  User: qujiakang@126.com  
  Date: 2017/7/18 0018 23:16  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead.jsp" %>
<div class="alert alert-info">
    课本管理
</div>
<div class="btn-group">
    <a href="/textbook/input" class="btn btn-success btn-sm action-input">
        <i class="fa fa-plus-square-o" aria-hidden="true"></i>添加
    </a>

</div>

<table
        class="table table-striped table-bordered table-hover table-condensed">
    <thead>
    <tr>
        <th>名称</th>
        <th>年级</th>
        <th>课程</th>
        <th>管理</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pager.dataList}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.grade.name}</td>
            <td>${item.course.name}</td>
            <td>

                <a href="/textbook/edit/${item.id}" class="btn btn-xs btn-info editItem"
                        data-id="${item.id}">
                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                </a>

                <a href="/chapter/input?param=${item.id}" class="btn btn-xs btn-info">
                    <i class="fa fa-book" aria-hidden="true"></i>
                </a>

                <button type="button" class="btn btn-xs  btn-danger action-remove"
                        data-id="${item.id}">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>

            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="4">${pager.pagination}</td>
    </tr>
    </tfoot>
</table>
<script src="static/scripts/textbook/pager.js"></script>

<%@ include file="../pageFoot.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<div class="table-responsive">
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>选择</th>
            <th>用户ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生日期</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>

                <td><input type="checkbox" value="${item.username}"
                           onclick="selectUser(this,'${item.username}','${item.nickname}')">
                </td>

                <td>${item.username}</td>
                <td>${item.nickname}</td>
                <td>${jxf:sex(item.sex)}</td>
                <td>${jxf:dateFormat(item.birthday)}</td>


            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="5">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<script id="checkTemp" type="text/html">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-danger" type="button" onclick="unselectUser(this,'{{username}}')">
            <span class="glyphicon glyphicon-remove"></span> 
        </button>
      </span>
        <input type="text" class="form-control" value="{{nickname}}" readonly>
    </div>
</script>
<script>
    $(function () {

        var tableAdmin = window.DataAdmin;
        tableAdmin.init(
            {
                context: '/',
                namespace: 'sysuser'
            });
        tableAdmin.addPagerNodeListener("dataArea");

    });
</script>
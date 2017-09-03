<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/violates/pager.css" rel="stylesheet">
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">查询奖惩记录</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline">
            <div class="form-group">
                <label for="affirmative">正/负面</label> <input type="text"
                                                             class="form-control" name="affirmative" id="affirmative"
                                                             placeholder="输入正/负面">
            </div>
            <div class="form-group">
                <label for="studentName">当事人姓名</label> <input type="text"
                                                              class="form-control" name="studentName" id="studentName"
                                                              placeholder="输入当事人姓名">
            </div>
            <div class="form-group">
                <label for="teacherName">记录老师姓名</label> <input type="text"
                                                               class="form-control" name="teacherName" id="teacherName"
                                                               placeholder="输入记录老师姓名">
            </div>
            <div class="form-group">
                <label for="fireTime">发生时间</label> <input type="text"
                                                          class="form-control" name="fireTime" id="fireTime"
                                                          placeholder="输入发生时间">
            </div>
            <button type="button" class="btn btn-primary queryItem">
                <span class="glyphicon glyphicon-search"> </span> 查询
            </button>
        </form>
    </div>
</div>

<div class="table-responsive">
    <button type="button" class="btn btn-primary inputItem">
        <span class="glyphicon glyphicon-new-window"></span>新增奖惩记录
    </button>
    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>正/负面</th>
            <th>当事人姓名</th>
            <th>记录老师姓名</th>
            <th>情况说明</th>
            <th>得分</th>
            <th>发生时间</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr>
                <td>${item.affirmative}</td>
                <td>${item.studentName}</td>
                <td>${item.teacherName}</td>
                <td>${item.content}</td>
                <td>${item.scoreValue}</td>
                <td>${item.fireTime}</td>
                <td class="btn-group">

                    <button type="button" class="btn btn-info editItem"
                            key="${item.id}">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-danger removeItem"
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
<div id="inputArea" class="table-responsive"></div>
<script src="static/scripts/violates/pager.js"></script>

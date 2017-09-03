<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/groupmember/input.css" rel="stylesheet">
<p class="lead">
    <span class="label label-info">添加班级成员</span>
</p>
<div class="table-responsive">
    <form id="groupmember_form">
        <input type="hidden" name="teacher" value="${sysUser.username}">
        <input type="hidden" name="teacherName" value="${sysUser.nickname}">
        <table
                class="table table-hover table-condensed table-bordered table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>年级</th>
                <th>课程</th>
            </tr>
            </thead>
            <tbody id="dataBody">
            <tr>
                <td><input type="text" name="name"
                           class="form-control jsonData"></td>
                <td><select id="grade" name="grade"
                            class="form-control jsonData" key="/grade/options"></select> <input
                        type="hidden" name="gradeName"></td>
                <td><select id="course" name="course"
                            class="form-control jsonData" key="/course/options"></select> <input
                        type='hidden' id='courseName' name='courseName'></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="10">
                    <button class="btn btn-info persisteDataItem" type="button">
                        <span class="glyphicon glyphicon-ok"></span>提交
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>

    </form>
</div>
<div id="dataArea" class="table-responsive"></div>
<div id="selectArea"></div>

<script src="static/scripts/groupmember/input.js"></script>
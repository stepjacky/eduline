<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="static/css/exampaper/input.css" rel="stylesheet">
<%@include file="../uploadrs.jsp" %>
<span class="label label-info">添加试题</span>
<div class="table-responsive">
    <form id="exampaper_form" method="POST"
          action="/exampaper/collect/save" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
        <table
                class="table table-hover table-condensed table-bordered table-striped">
            <tbody>

            <tr>
                <td>分类</td>
                <td><select id='category' name='category' class='form-control'>
                    <option value='A2'>A2</option>
                    <option value='IGCSE'>IGCSE</option>
                    <option value='AS'>AS</option>
                </select></td>
            </tr>
            <tr>
                <td>年份</td>
                <td><select id='year' name='year' class='form-control'>
                    <c:forEach var="y" begin="2000" end="2016">
                        <option value='${y}' ${y==atNow.year?"selected":""}>${y}年</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>所属课程</td>
                <td><select id="course" name="course"
                            class="form-control jsonData" key="/course/options"></select> <input
                        type="hidden" name="courseName"></td>
            </tr>
            <tr>
                <td>选择文件</td>
                <td><input type="file" name="file" multiple></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div id="progress" class="progress">
                        <div
                                class="progress-bar progress-bar-success progress-bar-striped"
                                role="progressbar" aria-valuenow="0" aria-valuemin="0"
                                aria-valuemax="100" style="width: 0%"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="btn btn-info appendItem" type="submit">
                        <span class="glyphicon glyphicon-plus"></span>添加
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script src="static/scripts/exampaper/input.js"></script>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/lecturenotes/input.css" rel="stylesheet">
<p class="lead">添加<span class="label label-info">教师讲义</span></p>
<div class="table-responsive">

    <table class="table table-hover table-condensed table-bordered table-striped">
        <thead>
        <tr>
            <th>名称</th>
            <th>课程</th>
            <th>课程号</th>
            <th>年级号</th>
            <th>年级</th>

            <th>管理</th>
        </tr>
        </thead>
        <tbody id="dataBody">

        </tbody>
        <tfoot>
        <tr>
            <td colspan="9">
                <button class="btn btn-info persisteDataItem" type="button">
                    <span class="glyphicon glyphicon-ok"></span>提交
                </button>
            </td>
        </tr>
        </tfoot>
    </table>

</div>

<div class="table-responsive">
    <form id="lecturenotes_form">
        <table class="table table-hover table-condensed table-bordered table-striped">
            <tbody>

            <tr>
                <td>课程</td>
                <td>
                    <select id='course' name='course' class='form-control jsonData' key="/course/options">
                    </select>
                    <input type="hidden" name="courseName">
                </td>
            </tr>

            <tr>
                <td>年级</td>
                <td>
                    <select id="grade" name="grade" class="form-control jsonData" key="/grade/options">
                    </select> <input type="hidden" name="gradeName">
                </td>
            </tr>


            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <button class="btn btn-info appendItem" type="button">
                        <span class="glyphicon glyphicon-plus"></span>添加
                    </button>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script id="dataTemp" type="text/html">
    <tr>
        {{each list}}
        {{if $index!='keyname'}}
        <td>{{$value}}</td>
        {{/if}}
        {{/each}}
        <td>
            <button type='button' class='btn btn-danger btn-xs earseItem' keyname='{{list[' keyname
            ']}}'>
            <span class="glyphicon glyphicon-erase"></span>
            </button>
        </td>
    </tr>
</script>
<script src="static/scripts/lecturenotes/input.js"></script>
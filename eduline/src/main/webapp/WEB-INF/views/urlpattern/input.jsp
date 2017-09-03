<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<link href="static/css/urlpattern/input.css" rel="stylesheet">
<p class="lead">
    输入<span class="label label-info">资源</span>
</p>
<div class="table-responsive">

    <table
            class="table table-hover table-condensed table-bordered table-striped">
        <thead>
        <tr>
            <th>URL模式</th>
            <th>名称</th>
            <th>管理</th>
        </tr>
        </thead>
        <tbody id="dataBody">

        </tbody>
        <tfoot>
        <tr>
            <td colspan="3">
                <button class="btn btn-info persisteDataItem" type="button">
                    <span class="glyphicon glyphicon-ok"></span>保存
                </button>
            </td>
        </tr>
        </tfoot>
    </table>

</div>

<div class="table-responsive">
    <form id="urlpattern_form">
        <table
                class="table table-hover table-condensed table-bordered table-striped">
            <tbody>
            <tr>
                <td>URL模式</td>
                <td><input type='text' id='urlPattern' name='urlPattern'
                           class='form-control'></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input type='text' id='name' name='name'
                           class='form-control'></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <button class="btn btn-info appendItem" type="button">
                        <span class="glyphicon glyphicon-plus"></span>附加 
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
            <button type='button' class='btn btn-danger earseItem' keyname='{{list[' keyname
            ']}}'>
            <span class="glyphicon glyphicon-erase"></span>
            </button>
        </td>
    </tr>
</script>
<script src="static/scripts/urlpattern/input.js"></script>
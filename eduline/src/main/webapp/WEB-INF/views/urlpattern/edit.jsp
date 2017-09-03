<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/urlpattern/edit.css" rel="stylesheet">
<p class="lead">
    添加<span class="label label-info">资源</span>
</p>
<form class="form-horizontal">
    <div class="form-group">
        <label for="urlPattern" class="col-sm-2 control-label">URL模式</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="urlPattern"
                   name="urlPattern" value="${bean.urlPattern}" placeholder="URLæ¨¡å¼">
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="name"
                   value="${bean.name}" placeholder="名称">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-info persisteFormItem">
                <span class="glyphicon glyphicon-floppy-saved"></span> 保存
            </button>
        </div>
    </div>
</form>
<script src="static/scripts/urlpattern/edit.js"></script>
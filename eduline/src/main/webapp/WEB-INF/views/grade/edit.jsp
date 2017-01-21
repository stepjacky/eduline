<link href="static/css/grade/edit.css" rel="stylesheet">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<p class="lead">
	编辑<span class="label label-info">年级</span>
</p>
<form class="form-horizontal">
	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">编号</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="id" name="id"
				value="${bean.id}" placeholder="编号">
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
				<span class="glyphicon glyphicon-floppy-saved"></span> 保存
			</button>
		</div>
	</div>
</form>
<script src="static/scripts/grade/edit.js"></script>
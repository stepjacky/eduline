
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/course/edit.css" rel="stylesheet">
<p class="lead">
	<span class="label label-info">编辑课程</span>
</p>
<form class="form-horizontal">
	<input type="hidden" name="id" value="${bean.id}">
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
<script src="static/scripts/course/edit.js"></script>

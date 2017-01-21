<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="static/css/authority/edit.css" rel="stylesheet">
<p class="lead">
	编辑<span class="label label-info">权限</span>
</p>
<form class="form-horizontal">
	<div class="form-group">
		<label for="authority" class="col-sm-2 control-label">编号</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="authority"
				name="authority" value="${bean.authority}" placeholder="编号">
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
<script src="static/scripts/authority/edit.js"></script>
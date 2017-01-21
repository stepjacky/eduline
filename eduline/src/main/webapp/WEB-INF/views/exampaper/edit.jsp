<link href="static/css/exampaper/edit.css" rel="stylesheet">
<p class="lead">
	编辑<span class="label label-info">试题</span>
</p>
<form class="form-horizontal">
	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">试题ID</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="id" name="id"
				value="${bean.id}" placeholder="试题ID">
		</div>
	</div>
	<div class="form-group">
		<label for="category" class="col-sm-2 control-label">分类</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="category" name="category"
				value="${bean.category}" placeholder="分类">
		</div>
	</div>
	<div class="form-group">
		<label for="course" class="col-sm-2 control-label">所属课程</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="course" name="course"
				value="${bean.course}" placeholder="所属课程">
		</div>
	</div>
	<div class="form-group">
		<label for="year" class="col-sm-2 control-label">年份</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="year" name="year"
				value="${bean.year}" placeholder="年份">
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
		<label for="realPath" class="col-sm-2 control-label">真实路径</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="realPath" name="realPath"
				value="${bean.realPath}" placeholder="真实路径">
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
<script src="static/scripts/exampaper/edit.js"></script>
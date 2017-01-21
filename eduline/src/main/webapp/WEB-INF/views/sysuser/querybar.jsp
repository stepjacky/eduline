<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">查询用户</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<div class="form-group">
				<label for="nickname">学号</label> <input type="text"
					class="form-control" name="username" id="username"
					placeholder="输入学号">
			</div>
			<button type="button" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-search"> </span> 查询
			</button>
		</form>
	</div>
</div>
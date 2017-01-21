<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ include file="../pageHead.jsp"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">违纪统计打印</h3>
	</div>
	<div class="panel-body">
		<form method="get" target="_blank"
			action="/violates/reporter/userviolate.jasper" class="form-inline">
			<div class="form-group">
				<label>学生学号</label> <input type="text"
					class="form-control studentNo" name="query" placeholder="输入学生学号">
			</div>
			<button type="submit" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-print"> </span>打印
			</button>
		</form>
	</div>
</div>
<%@ include file="../pageFoot.jsp"%>
<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">打印在读证明</h3>
	</div>
	<div class="panel-body">
		<form method="get" target="_blank"
			action="/sysuser/reporter/xpconfirm.jasper" class="form-inline">
			<div class="form-group">
				<label>学生学号</label> <input type="text"
					class="form-control studentNo" name="query" placeholder="输入学生学号">
			</div>
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-print"> </span>打印证明
			</button>
		</form>
	</div>
</div>
<%@ include file="../pageFoot.jsp"%>
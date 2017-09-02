<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/violates/query.css" rel="stylesheet">
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">行为规范记录查询</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline" action="/violates/query/0" method="post">

			<div class="form-group">
				<label for="studentName">学生姓名</label> <input type="text"
					class="form-control" name="studentName" placeholder="输入学生姓名查询">
			</div>
			<input class="query" name="query" type="hidden"> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<button type="button" class="btn btn-primary querybtn">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form>
		<hr>
		<form class="form-inline" action="/violates/query/0" method="post">

			<div class="form-group">
				<label for="studentName">老师姓名</label> <input type="text"
					class="form-control" name="teacherName" placeholder="输入老师姓名查询">
			</div>
			<input class="query" name="query" type="hidden"> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<button type="button" class="btn btn-primary querybtn">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form>
		<hr>
		<form class="form-inline" action="/violates/currentgrade/0"
			method="post">

			<div class="form-group">
				<label for="studentName">所处学年</label> <select name="eduyear"
					class="form-control">
					<c:forEach var="g" begin="2015" end="2016">
						<option value="${g}">${g}学年</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="studentName">所在年级</label> <select name="grade"
					class="form-control">
					<c:forEach var="g" begin="7" end="12">
						<option value="${g}">${g}年级</option>
					</c:forEach>
				</select>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form>
		<hr>
		<a href="/violates/chart/monthly/${atNow.year}/${atNow.monthValue}">${atNow.year}年${atNow.monthValue}月各年级图标</a>
	</div>
</div>
<div class="alert alert-success" role="alert">

	学生 ${child.nickname}在 ${child.grade}年级共计 奖 <span class="badge">${mypoints.affirmative}</span>分
	罚 <span class="badge">${mypoints.negative}</span>分

</div>
<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<caption>
			<span class="label label-warning">查询条件</span> ${queryInfo}
		</caption>
		<thead>
			<tr>
				<th>学生姓名</th>
				<th>奖/罚</th>
				<th>得分</th>
				<th>所在年级</th>
				<th>情况说明</th>
				<th>记录时间</th>
				<th>记录老师</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>

					<td>${item.studentName}[${item.student}]</td>
					<td>${item.affirmative>0?"奖":"罚"}</td>
					<td><span class="label label-${item.affirmative>0?"info":"warning"}">
							${item.scoreValue} </span></td>
					<td>${item.gradeName}</td>
					<td>${item.content}</td>

					<td>${jxf:dateFormat(item.fireTime)}</td>
					<td>${item.teacherName}</td>
					<td class="btn-group">

						<button type="button" class="btn btn-danger btn-xs removeItem"
							key="${item.id}">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						</button>

					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<script src="static/scripts/violates/query.js"></script>
<%@ include file="../pageFoot.jsp"%>
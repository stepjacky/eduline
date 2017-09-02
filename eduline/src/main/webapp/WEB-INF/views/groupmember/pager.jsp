<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/groupmember/pager.css" rel="stylesheet">
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">查询班级成员</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<div class="form-group">
				<label for="teacherName">教师姓名</label> <input type="text"
					class="form-control" name="teacherName" id="teacherName"
					placeholder="输入教师姓名">
			</div>
			<div class="form-group">
				<label for="studentName">学生姓名</label> <input type="text"
					class="form-control" name="studentName" id="studentName"
					placeholder="输入学生姓名">
			</div>
			<div class="form-group">
				<label for="gradeName">年级名称</label> <input type="text"
					class="form-control" name="gradeName" id="gradeName"
					placeholder="输入年级名称">
			</div>
			<div class="form-group">
				<label for="courseName">课程名称</label> <input type="text"
					class="form-control" name="courseName" id="courseName"
					placeholder="输入课程名称">
			</div>
			<button type="button" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-search"> </span> 查询
			</button>
		</form>
	</div>
</div>

<div class="table-responsive">
	<button type="button" class="btn btn-primary inputItem">
		<span class="glyphicon glyphicon-new-window"></span>添加班级成员
	</button>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>教师姓名</th>
				<th>学生姓名</th>
				<th>年级名称</th>
				<th>课程名称</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.teacherName}</td>
					<td>${item.studentName}</td>
					<td>${item.gradeName}</td>
					<td>${item.courseName}</td>
					<td class="btn-group">

						<button type="button" class="btn btn-info editItem"
							key="${item.teacher}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-danger removeItem"
							key="${item.teacher}">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						</button>

					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div id="inputArea" class="table-responsive"></div>
<script src="static/scripts/groupmember/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
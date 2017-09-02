

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/groupmember/query.css" rel="stylesheet">

<div class="table-responsive">
	<div class="btn-group">
		<button type="button" class="btn btn-primary inputItem">
			<span class="glyphicon glyphicon-new-window"></span>新建学科群
		</button>

	</div>

	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>录入成绩</th>
				<th>名称</th>
				<th>教师姓名</th>
				<th>年级名称</th>
				<th>课程名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td><a class="btn btn-primary"
						href="/examscore/scoreinput?groupId=${item.groupId}"> <span
							class="glyphicon glyphicon-new-window"></span>成绩录入
					</a></td>
					<td>${item.name}</td>
					<td>${item.teacherName}</td>
					<td>${item.gradeName}</td>
					<td>${item.courseName}</td>
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

<script src="static/scripts/groupmember/query.js"></script>

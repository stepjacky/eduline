
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="table-responsive">
	<a class="btn btn-primary" href="/courseingrade/index"> <span
		class="glyphicon glyphicon-new-window"></span>设置课程
	</a>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>入学年份</th>
				<th>年级</th>
				<th>课程</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.inyear}</td>
					<td>${item.gradeName}</td>
					<td>${item.courseName}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<script src="static/scripts/courseingrade/query.js"></script>

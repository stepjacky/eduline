<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/examscore/pager.css" rel="stylesheet">
<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>学生姓名</th>
				<th>入学年份</th>
				<th>所在年级</th>
				<th>学期[上/下]</th>

				<th>课程名称</th>
				<th>月考序号</th>

				<th>分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.studentName}[${item.student}]</td>
					<td>${item.inyear}</td>
					<td>${item.gradeName}</td>
					<td>${jxf:semester(item.semester)}</td>

					<td>${item.courseName}</td>
					<td>${jxf:monthly(item.monthly)}</td>

					<td>${item.scoreValue}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<script src="static/scripts/examscore/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
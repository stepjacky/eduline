
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/examscore/pager.css" rel="stylesheet">
<%@ include file="querybar.jsp"%>

<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>课程名称</th>

				<th>分数</th>
				<th>学生姓名</th>
				<th>年级</th>


				<th>学期</th>


				<th>月考序号</th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.courseName}</td>
					<td><span class="label label-primary">
							${item.scoreValue}</span></td>
					<td>${item.studentName}</td>
					<td>${jxf:grade(item.grade)}</td>
					<td>${jxf:semester(item.semester)}</td>


					<td>${jxf:monthly(item.monthly)}</td>


				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div id="inputArea" class="table-responsive"></div>
<script>
  var ajax = false; 
</script>
<script src="static/scripts/examscore/pager.js"></script>

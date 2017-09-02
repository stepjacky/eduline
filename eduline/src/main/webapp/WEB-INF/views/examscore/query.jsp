
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/examscore/query.css" rel="stylesheet">
<%@ include file="querybar.jsp"%>
<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>

				<th>学生学号</th>
				<th>学生姓名</th>
				<th>课程名称</th>
				<th>月考序号</th>
				<th>分数</th>

				<th>年级</th>

				<th>学期</th>

				<th>修改</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.student}</td>
					<td>${item.studentName}</td>
					<td>${item.courseName}</td>
					<td>${jxf:monthly(item.monthly)}</td>
					<td>
					  <input type="number" id="${item.id}_scoreValue" class="form-control" value="${item.scoreValue}" >
					</td>
					<td>${item.gradeName}</td>
					<td>${jxf:semester(item.semester)}</td>
<td>
 <button type="button" class="btn btn-primary btn-xs updatePartial"
							key="${item.id}"							
							>
							<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
						</button>
</td>
					<td>
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
<script src="static/scripts/examscore/query.js"></script>

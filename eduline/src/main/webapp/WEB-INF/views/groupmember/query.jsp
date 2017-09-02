<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/groupmember/query.css" rel="stylesheet">

<div class="table-responsive">
	<div class="btn-group">
		<button type="button" class="btn btn-primary inputItem">
			<span class="glyphicon glyphicon-new-window"></span>新建班级
		</button>

	</div>

	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>教师姓名</th>
				<th>年级名称</th>
				<th>课程名称</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>

					<td><a href="/groupmember/edit/${item.groupId}" target="_self">${item.name}</a>
					</td>
					<td>${item.teacherName}</td>
					<td>${item.gradeName}</td>
					<td>${item.courseName}</td>
					<td class="btn-group">

						<button type="button" class="btn btn-danger btn-xs removeItem"
							key="${item.groupId}" keyname="groupId">
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

<script src="static/scripts/groupmember/query.js"></script>
<%@ include file="../pageFoot.jsp"%>
<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/exampaper/pager.css" rel="stylesheet">
<%@ include file="querybar.jsp"%>
<div class="table-responsive">
	<button type="button" class="btn btn-primary inputItem">
		<span class="glyphicon glyphicon-new-window"></span>新增试题
	</button>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>试题</th>
				<th>分类</th>
				<th>所属课程</th>
				<th>年份</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td><a class="btn btn-xs btn-info"
						href="/exampaper/download/${item.id}">${item.name}</a></td>
					<td>${item.category}</td>
					<td>${item.courseName}</td>
					<td>${item.year}</td>
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
				<td colspan="5">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div id="inputArea" class="table-responsive"></div>
<script src="static/scripts/exampaper/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/ebook/query.css" rel="stylesheet">
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">查询电子书</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<button type="button" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-search"> </span> 查询
			</button>
		</form>
	</div>
</div>

<div class="table-responsive">
	<button type="button" class="btn btn-primary inputItem">
		<span class="glyphicon glyphicon-new-window"></span>新增电子书
	</button>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>上传时间</th>
				<th>文件类型</th>
				<th>大小</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.firetime}</td>
					<td>${item.fileType}</td>
					<td>${item.size}</td>
					<td class="btn-group">

						<button type="button" class="btn btn-info editItem"
							key="${item.id}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-danger removeItem"
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
<script src="static/scripts/ebook/query.js"></script>
<%@ include file="../pageHead.jsp"%>
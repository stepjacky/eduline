
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/ebook/pager.css" rel="stylesheet">
<div class="table-responsive">
	<button type="button" class="btn btn-primary inputItem">
		<span class="glyphicon glyphicon-new-window"></span>新增电子书
	</button>
	<div id="inputArea" class="table-responsive"></div>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>标签</th>
				<th>名称</th>
				<th>下载次数</th>
				<th>上传时间</th>
				<th>文件类型</th>
				<th>大小</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td ><span class="label label-default"> ${item.tags}</span></td>
					<td>${item.name}</td>
					<td class="badge">${item.clicknum}</td>
					<td>${jxf:dateFormat(item.firetime)}</td>
					<td>${item.fileType}</td>
					<td>${item.size/1024}KB</td>
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
				<td colspan="7">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<script src="static/scripts/ebook/pager.js"></script>

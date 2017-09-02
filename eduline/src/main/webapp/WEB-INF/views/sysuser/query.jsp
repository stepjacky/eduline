<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/sysuser/query.css" rel="stylesheet">
<%@ include file="querybar.jsp"%>
<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>用户类型</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.username}</td>
					<td>${item.nickname}</td>
					<td>${jxf:sex(item.sex)}</td>
					<td>${jxf:dateFormat(item.birthday)}</td>
					<td>${jxf:userType(item.userType)}</td>
					<td>

						<button type="button" class="btn btn-xs btn-info editItem"
							key="${item.username}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-xs  btn-danger removeItem"
							key="${item.username}">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						</button> <span class="caret"></span>
						<button type="button" class="btn btn-xs  btn-warning resetItem"
							key="${item.username}">
							<span class="glyphicon glyphicon-scissors" aria-hidden="true"></span>
						</button>
					</td>
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
<script src="static/scripts/sysuser/query.js"></script>
<%@ include file="../pageFoot.jsp"%>
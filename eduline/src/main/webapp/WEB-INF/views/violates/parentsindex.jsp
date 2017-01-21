<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/violates/pager.css" rel="stylesheet">

<div class="table-responsive">
	<div class="alert alert-info" role="alert">

		您的孩子 ${child.nickname}在 ${child.grade}年级 共计 奖 <span class="badge">${mypoints.affirmative}</span>分
		罚 <span class="badge">${mypoints.negative}</span>分

	</div>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>类型</th>
				
				<th>原因</th>
				<th>得分</th>
				<th>发生时间</th>
				<th>记录老师</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${jxf:violateType(item.affirmative)}</td>
					
					<td>${item.content}</td>
					<td>${item.scoreValue}</td>
					<td>${jxf:dateFormat(item.fireTime)}</td>
					<td>${item.teacherName}</td>
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
<script src="static/scripts/violates/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
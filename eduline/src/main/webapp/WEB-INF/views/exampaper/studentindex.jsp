<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/exampaper/pager.css" rel="stylesheet">



<div class="panel panel-info">
	<div class="panel-heading">选择标签</div>
	<div class="panel-body">
		<ol class="breadcrumb">
			<li><span class="label label-default">年份</span></li>
			<c:forEach var="y" begin="2000" end="2016">
				<li><a
					href="/exampaper/student/0?course=${course}&year=${y}&category=${category}">${y}</a></li>
			</c:forEach>
		</ol>

		<ol class="breadcrumb">
			<li><span class="label label-default">分类</span></li>
			<c:forEach items="${categories}" var="cate">
				<li><a
					href="/exampaper/student/0?course=${course}&year=${year}&category=${cate}">${cate}</a></li>
			</c:forEach>
		</ol>

		<ol class="breadcrumb">
			<li><span class="label label-default">课程</span></li>
			<c:forEach items="${courses}" var="course">
				<li><a
					href="/exampaper/student/0?course=${course.id}&year=${year}&category=${category}">${course.name}</a></li>
			</c:forEach>
		</ol>
	</div>
	<div class="panel-footer">
		<span class="label label-primary">${year}</span> <span
			class="label label-success">${category}</span> <span
			class="label label-info">${courseName}</span>
	</div>
</div>




<div class="table-responsive">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>下载</th>
				<th>名称</th>
				<th>年份</th>
				<th>类别</th>

				<th>课程</th>



			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>

					<td><a class="btn btn-primary btn-xs"
						href="/exampaper/download/${item.id}"> <span
							class="glyphicon glyphicon-save"></span>
					</a></td>
					<td>${item.name}</td>
					<td>${item.year}</td>
					<td>${item.category}</td>
					<td>${item.courseName}</td>

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

<script src="static/scripts/exampaper/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="static/css/course/pager.css" rel="stylesheet">
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">查询课程</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<div class="form-group">
				<label for="name">名称</label> <input type="text" class="form-control"
					name="name" id="name" placeholder="输入名称">
			</div>
			<button type="button" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-search"> </span> 查询
			</button>
		</form>
	</div>
</div>

<div class="table-responsive">
	<button type="button" class="btn btn-primary inputItem">
		<span class="glyphicon glyphicon-new-window"></span>新增课程
	</button>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>类型</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td>${item.name}[${item.id}]</td>
					<td>
					<label>
					   <input type="radio" name="${item.id}_ctype" class="set-type" key="${item.id }" value="0" 
					   ${item.ctype=='0'?'checked':'' }>
					   <span class='label label-${item.ctype==0?'success':'default'}' >${jxf:courseType(0)}</span>
					</label>
					<label>
					   <input type="radio" name="${item.id}_ctype" class="set-type"  key="${item.id }"  value="1" 
					   ${item.ctype=='1'?'checked':'' }>
					    <span class='label label-${item.ctype==1?'info':'default'}' >${jxf:courseType(1)}</span>
					</label>
					
					<label>
					   <input type="radio" name="${item.id}_ctype" class="set-type"  key="${item.id }"  value="2" 
					   ${item.ctype=='2'?'checked':'' }>
					    <span class='label label-${item.ctype==2?'warning':'default'}' >${jxf:courseType(2)}</span>
					</label>
					</td>
					
					<td>

						<button type="button" class="btn btn-xs btn-info editItem"
							key="${item.id}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-xs  btn-danger removeItem"
							key="${item.id}">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						</button>

					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">${pager.pagination}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div id="inputArea" class="table-responsive"></div>
<script src="static/scripts/course/pager.js"></script>

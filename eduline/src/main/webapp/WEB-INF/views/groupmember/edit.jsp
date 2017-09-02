<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/groupmember/edit.css" rel="stylesheet">
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">编辑班级[${bean.gradeName}的班级 ${bean.name}]成员</h3>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<div class="btn-group">
				<button type="button" class="btn btn-primary inputItem">
					<span class="glyphicon glyphicon-new-window"></span>给${bean.gradeName}班级 ${bean.name}新增成员
				</button>

			</div>

			<table
				class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>学生信息</th>
						<th>本学年奖励</th>
						<th>本学年受罚</th>
						<th>最近考试排名</th>
						<th>父亲最后登陆时间</th>
						<th>母亲最后登陆时间</th>
						<th>管理</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${beans}">
						<tr>
							<td>${item.student}[${item.studentName}]</td>
							<td>${item.upviolate}</td>
							<td>${item.dwviolate}</td>
							<td>${item.totalSorted}</td>
							<td>${jxf:dateFormat(item.firetimef)}</td>
							<td>${jxf:dateFormat(item.firetimem)}</td>
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
						<td colspan="7"></td>
					</tr>
				</tfoot>
			</table>
		</div>

	</div>
</div>
<div id="dataArea" class="table-responsive"></div>
<div id="selectArea"></div>
<button class="btn btn-info persisteDataItem" type="button">
	<span class="glyphicon glyphicon-ok"></span>提交
</button>
<form id="groupmember_form">
	<input type="hidden" name="name" value="${bean.name}"> <input
		type="hidden" name="teacher" value="${sysUser.username}"> <input
		type="hidden" name="teacherName" value="${sysUser.nickname}">
	<input type="hidden" name="grade" value="${bean.grade}"> <input
		type="hidden" name="gradeName" value="${bean.gradeName}"> <input
		type="hidden" name="course" value="${bean.course}"> <input
		type="hidden" name="courseName" value="${bean.courseName}"> <input
		type="hidden" name="groupId" value="${bean.groupId}">


</form>
<script> 
  var grade = ${bean.grade};
</script>
<script src="static/scripts/groupmember/edit.js"></script>
<%@ include file="../pageFoot.jsp"%>
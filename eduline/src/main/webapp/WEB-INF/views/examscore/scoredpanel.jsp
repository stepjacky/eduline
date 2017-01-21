<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>

<div role="tabpanel" id="studentpanel">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#oldscored"
			aria-controls="home" role="tab" data-toggle="tab">已录成绩学生[${scores.size()}人]</a></li>
		<li role="presentation"><a href="#newscored"
			aria-controls="profile" role="tab" data-toggle="tab">未录成绩学生[${unscores.size()}]</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="oldscored">
			<div class="table-responsive">
				<table
					class="table table-hover table-condensed table-bordered table-striped">
					<caption>
						<span class="label label-danger">注意:</span>所有修改点击下方【确认修改】按钮生效
					</caption>
					<thead>
						<tr>
							<th>序号</th>
							<th>学生ID</th>
							<th>学生姓名</th>
							<th>年级</th>
							<th>学期</th>
							<th>考试次别</th>
							<th>分数</th>
							<th>评语</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:forEach items="${scores}" var="item" varStatus="vs">
							<tr>
								<td><span class="label label-default">${vs.index+1}</span></td>
								<td>${item.student}</td>
								<td>${item.studentName}</td>
								<td>${jxf:grade(item.grade)}</td>
								<td>${jxf:semester(item.semester)}</td>
								<td>${jxf:monthly(item.monthly)}</td>


								<td><input type="number" class="form-control scoreValue"
									min="0" max="100" key="${item.student}"
									value="${item.scoreValue}"></td>
								<td><textarea class="form-control remarkContent"
										key="${item.student}" rows="4">${item.remark}</textarea>
								</td>
							</tr>
							<script>
							gminfo['${item.student}'] = ${jxf:toJson(item)};
						</script>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8" align="center">
								<button type="button" class="btn btn-success submitAll"
									key="oldscored">
									<span class="glyphicon glyphicon-floppy-saved"></span> 确认修改
								</button>
							</td>
						</tr>
					</tfoot>
				</table>

			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="newscored">
			<div class="table-responsive">
				<table
					class="table table-hover table-condensed table-bordered table-striped">
					<caption>
						<span class="label label-danger">注意:</span>所有修改点击下方【确认录入】按钮生效
					</caption>
					<thead>
						<tr>
							<th>序号</th>
							<th>学生ID</th>
							<th>学生姓名</th>
							<th>分数</th>
							<th>评语</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:forEach var="item" items="${unscores}" varStatus="vs">
							<tr>
								<td><span class="label label-default">${vs.index+1}</span></td>
								<td>${item.student}</td>
								<td>${item.studentName}</td>
								<td><input type="number" min="0" max="100"
									class="form-control scoreValue" key="${item.student}">
								</td>
								<td><textarea class="form-control remarkContent" rows="4"
										key="${item.student}"></textarea></td>
							</tr>
							<script>
							gminfo['${item.student}'] = ${jxf:toJson(item)};
						</script>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5" align="center">
								<button type="button" class="btn btn-primary submitAll"
									key="newscored">
									<span class="glyphicon glyphicon-floppy-saved"></span> 确认录入
								</button>
							</td>
						</tr>
					</tfoot>
				</table>

			</div>

		</div>

	</div>

</div>


<script src="static/scripts/examscore/scoredpanel.js"></script>
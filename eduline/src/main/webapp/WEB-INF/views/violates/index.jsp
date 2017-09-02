
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/groupmember/query.css" rel="stylesheet">

<div class="table-responsive">
	<span class="label label-default">选择班级以录入行为规范</span>
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>录入行为规范</th>
				<th>名称</th>
				<th>教师姓名</th>
				<th>年级名称</th>
				<th>课程名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.dataList}" var="item">
				<tr>
					<td><a class="btn btn-primary btn-xs"
						href="/violates/group/input/${item.groupId}"> <span
							class="glyphicon glyphicon-new-window"></span>行为规范录入
					</a></td>
					<td>${item.name}</td>
					<td>${item.teacherName}</td>
					<td>${item.gradeName}</td>
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
<span class="label label-default">通过学号或者姓名</span>
<input id="student" class="form-control" placeholder="请输入学号或者姓名">
<div id="dataArea"></div>

<script id="dataTemp" type="text/html">
 <div class="table-responsive">
<form id="violates_form">
<table class="table table-hover table-condensed table-bordered table-striped">
  <tbody>
    <tr>
      <td>发生时间</td>
      <td><input type='text' class='datepicker form-control' >
       <input type='hidden' id='fireTime' name='fireTime'>
      </td>
    </tr>
    <tr>
      <td>正/负面</td>
      <td>
        <select id='affirmative' name='affirmative' class='form-control' >
            <option value="">请选择</option>
            <option value='-1'>惩罚</option>
			<option value='1'>奖励</option>
        </select><input type='hidden' id='affirmativeName' name='affirmativeName'></td>
    </tr>
    <tr>
      <td>学生学号</td>
      <td><input type='text' id='student' name='student' class='form-control' readonly value="{{student}}" ></td>
    </tr>   
    <tr>
      <td>学生姓名</td>
      <td><input type='text' name='studentName' class='form-control'readonly value="{{studentName}}" ></td>
    </tr>   
    <tr>
      <td>情况说明</td>
      <td><textarea id='content' name='content' class='form-control' ></textarea></td>
    </tr>
    <tr>
      <td>得分</td>
      <td>${jxf:vpoint('scoreValue')}</td>
    </tr>
    
  </tbody>
  <tfoot>
    <tr>
      <td colspan="2">
          <button type="button" class="btn btn-info submitViolate">
          <span class="glyphicon glyphicon-plus"></span>
                        添加</button>
     </td>
    </tr>
  </tfoot>  
</table>
</form>
</div>
</script>
<script src="static/scripts/violates/query.js"></script>

<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/violates/input.css" rel="stylesheet">
<script type="text/javascript">
var theInst = {};
</script>
<div class="table-responsive">
	<div class="panel panel-primary">
		<!-- Default panel contents -->
		<div class="panel-heading">奖/惩录入</div>
		<div class="panel-body"></div>
		<table
			class="table table-hover table-condensed table-bordered table-striped">
			<thead>
				<tr>
					<th>姓名</th>
					<th>时间</th>
					<th>评价</th>
					<th>情况</th>
					<th>得分</th>
					<th>添加</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${members}">
					<script>
    	theInst['${item.student}'] = {
    			'student':'${item.student}',
    			'studentName':'${item.studentName}',
    			'grade':'${item.grade}',
    			'gradeName':'${item.gradeName}',
    	        'enabled':false		
    	};
     </script>
					<tr>

						<td>${item.studentName}[${item.student}]</td>
						<td><input type="text" key="${item.student}"
							class="form-control datepicker datestyle"> <input
							type="hidden"></td>
						<td>罚 <input type="radio" class="affirmative"
							name="${item.student}" key="${item.student}" value="-1">
							奖 <input type="radio" class="affirmative" name="${item.student}"
							key="${item.student}" value="1">


						</td>

						<td><textarea class='form-control content'
								key="${item.student}"></textarea></td>

						<td><select class='form-control  scoreValue scorestyle'
							key="${item.student}">
								<option value='0'>0</option>
								<option value='0.5'>0.5</option>
								<option value='1'>1</option>
								<option value='2'>2</option>
								<option value='3'>3</option>
								<option value='4'>4</option>
								<option value='5'>5</option>
								<option value='10'>10</option>
						</select></td>
						<td><input type="checkbox" class="validedCheck"
							key="${item.student}"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td align="center" colspan="6">
						<button type="button" class="btn btn-primary savedItems">
							<span class="glyphicon glyphicon-floppy-saved"></span>确认提交
						</button>
					</td>
				</tr>
			</tfoot>
		</table>

	</div>

</div>
<script src="static/scripts/violates/input.js"></script>
<%@ include file="../pageFoot.jsp"%>
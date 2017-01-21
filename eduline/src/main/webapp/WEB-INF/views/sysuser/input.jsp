<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/sysuser/input.css" rel="stylesheet">
<span class="label label-info">添加用户</span>

<div class="table-responsive">

	<table
		class="table table-hover table-condensed table-bordered table-striped">
		<thead>
			<tr>
				<th>学号</th>
				<th>昵称</th>
				<th>姓</th>
				<th>名</th>
				<th>性别</th>
				<th>生日</th>
				<th>用户类型</th>
				<th>擦除</th>
			</tr>
		</thead>
		<tbody id="dataBody">

		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<button class="btn btn-info persisteDataItem" type="button">
						<span class="glyphicon glyphicon-ok"></span>保存
					</button>
				</td>
			</tr>
		</tfoot>
	</table>

</div>

<div class="table-responsive">
	<form id="sysuser_form">
		<table
			class="table table-hover table-condensed table-bordered table-striped">
			<tbody>
				<tr>
					<td>用户名/学号</td>
					<td><input type='text' id='username' name='username'
						class='form-control' onblur="checkusername(this)"> <span
						class="label label-danger"></span></td>
				</tr>
				<tr>
					<td>昵称</td>
					<td><input type='text' id='nickname' name='nickname'
						class='form-control'></td>
				</tr>
				<tr>
					<td>姓</td>
					<td><input type='text' id='surname' name='surname'
						class='form-control'></td>
				</tr>
				<tr>
					<td>名</td>
					<td><input type='text' id='givename' name='givename'
						class='form-control'></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><select id='sex' name='sex' class='form-control'><option
								value='0'>女</option>
							<option value='1'>男</option></select></td>
				</tr>
				<tr>
					<td>生日</td>
					<td><input type='text' class='datepicker form-control'><input
						type='hidden' id='birthday' name='birthday'></td>
				</tr>
				<tr>
					<td>用户类型</td>
					<td><select id='userType' name='userType' class='form-control'><option
								value='1'>学生</option>
							<option value='2'>老师</option>
							<option value='3'>家长</option></select></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button class="btn btn-info appendItem" type="button">
							<span class="glyphicon glyphicon-plus"></span>添加
						</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>
<script id="dataTemp" type="text/html">
<tr>
    {{each list}}
        {{if $index!='keyname'}}
        <td>{{$value}}</td>
        {{/if}}    
    {{/each}}
	<td>
		<button type='button' class='btn btn-danger btn-xs earseItem' keyname='{{list['keyname']}}'>
         	<span class="glyphicon glyphicon-erase"></span>
    	</button>  
    </td>
</tr>
</script>
<script src="static/scripts/sysuser/input.js"></script>
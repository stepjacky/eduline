<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/examscore/input.css" rel="stylesheet">
<div class="panel panel-info">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<span class="label label-success"> ${bean.name} </span> [ <span
			class="label label-primary">
			${bean.gradeName}-${bean.courseName} </span> ]录入成绩
	</div>
	<div class="panel-body">
		<form class="form-inline" id="common_form">

			<div class="form-group">
				<label for="semester">学期</label> <input type='text'
					class='form-control' name='semesterName' value="${semesterName}"
					readonly> <input type='hidden' id='semester'
					name='semester' value="${semester}">


			</div>

			<div class="form-group">
				<label for="monthly">考试次别</label>
				<div class="radio">
					<label> <input type="radio" name="monthly"
						class="withHidden" id="optionsRadios1" value="0" checked>
						第一次月考
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="monthly"
						class="withHidden" id="optionsRadios2" value="1"> 期中考试
					</label>
				</div>
				<div class="radio ">
					<label> <input type="radio" name="monthly"
						class="withHidden" id="optionsRadios3" value="2"> 第二次月考
					</label>
				</div>
				<div class="radio ">
					<label> <input type="radio" name="monthly"
						class="withHidden" id="optionsRadios4" value="3"> 期末考试
					</label>
				</div>
				<input type='hidden' id='monthlyName' name='monthlyName'
					value='第一次月考'>

			</div>

		</form>
	</div>

</div>

<script type="text/javascript">
   var gminfo = {};
   var groupId = '${groupId}';
</script>
<script src="static/scripts/examscore/input.js"></script>
<div id="userpanel"></div>

<%@ include file="../pageFoot.jsp"%>

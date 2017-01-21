<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel-group" role="tablist">

	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading1">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup1"
					aria-expanded="true" aria-controls="collapseListGroup1"> 学生情况 </a>
			</h4>
		</div>
		<div id="collapseListGroup1" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapseListGroupHeading1"
			aria-expanded="true">
			<ul class="list-group">

				<li class="list-group-item"><a
					href="/examscore/student/score/${sysUser.children}/0">考试成绩</a></li>

				<li class="list-group-item"><a
					href="/violates/parents/0?ajax=false">行为规范统计</a></li>


			</ul>

		</div>
	</div>
<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading6">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup6"
					aria-expanded="true" aria-controls="collapseListGroup1">电子校历
				</a>
			</h4>
		</div>
		<div id="collapseListGroup6" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapseListGroupHeading6"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a href="/userevent/input">电子校历</a>
				</li>
				

			</ul>

		</div>
	</div>
</div>




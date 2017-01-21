<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel-group" role="tablist">


	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading1">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup1"
					aria-expanded="true" aria-controls="collapseListGroup1"> 系统管理 </a>
			</h4>
		</div>
		<div id="collapseListGroup1" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapseListGroupHeading1"
			aria-expanded="true">
			<ul class="list-group">
				<!--  <li class="list-group-item">
             <a href="/authority/pager/0?ajax=false">权限管理</a>
          </li>        
          <li class="list-group-item">
             <a href="/urlpattern/pager/0?ajax=false">访问资源管理</a>
          </li>  -->
				<li class="list-group-item"><a
					href="/sysuser/pager/0?ajax=false">用户管理</a></li>
				<li class="list-group-item"><a
					href="/course/pager/0?ajax=false">课程管理</a></li>
				<li class="list-group-item"><a href="/courseingrade/query/0">课程设置</a>
				</li>
				<li class="list-group-item"><a href="/grade/pager/0?ajax=false">年级管理</a>
				</li>
				<li class="list-group-item"><a
					href="/examscore/pager/0?ajax=false">成绩管理</a></li>
				<li class="list-group-item"><a
					href="/examscore/anyway/scoreResult">成绩排名</a></li>

			</ul>

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

</div>




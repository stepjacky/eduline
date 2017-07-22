<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel-group" role="tablist">

	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading8">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup8"
					aria-expanded="false" aria-controls="collapseListGroup8"> 班级管理 </a>
			</h4>
		</div>
		<div id="collapseListGroup8" class="panel-collapse collapse out"
			role="tabpanel" aria-labelledby="collapseListGroupHeading8"
			aria-expanded="false">
			<ul class="list-group">
				<li class="list-group-item"><a
					href="/groupmember/query/0?query=teacher`${sysUser.username}&group=groupId&ajax=false">班级管理</a>
				</li>

			</ul>

		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading1">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup1"
					aria-expanded="true" aria-controls="collapseListGroup1">教学管理 </a>
			</h4>
		</div>
		<div id="collapseListGroup1" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapseListGroupHeading1"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a
					href="/resource/listresource">我的资源</a></li>

<li class="list-group-item"><a href="/homework/homework/prehomework">布置作业</a></li>
<li class="list-group-item"><a href="/homework/teacher/homeworks/0">我的作业</a></li>

			</ul>

		</div>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading2">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup2"
					aria-expanded="true" aria-controls="collapseListGroup2"> 成绩管理 </a>
			</h4>
		</div>
		<div id="collapseListGroup2" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="collapseListGroupHeading2"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a href="/examscore/score/0">
						成绩录入 </a></li>

				<li class="list-group-item"><a href="/examscore/input">
						成绩查询 </a></li>

			</ul>

		</div>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading7">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup7"
					aria-expanded="true" aria-controls="collapseListGroup7"> 证明打印 </a>
			</h4>
		</div>
		<div id="collapseListGroup7" class="panel-collapse collapse out"
			role="tabpanel" aria-labelledby="collapseListGroupHeading7"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a href="/sysuser/xpconfirm/input">
						在读证明打印 </a></li>
				<li class="list-group-item"><a href="/examscore/score/print">
						打印成绩单 </a></li>
				<li class="list-group-item"><a
					href="/examscore/graduates/inyear"> 学生评语打印 </a></li>

				<li class="list-group-item"><a href="/violates/userinput">
						行为规范打印 </a></li>
			</ul>

		</div>
	</div>


    <div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading3">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup3"
					aria-expanded="true" aria-controls="collapseListGroup3"> 行为规范管理
				</a>
			</h4>
		</div>
		<div id="collapseListGroup3" class="panel-collapse collapse out"
			role="tabpanel" aria-labelledby="collapseListGroupHeading3"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a href="/violates/violate/0">行为规范录入</a>
				</li>
				<li class="list-group-item"><a
					href="/violates/query/0?order=fireTime desc"> 行为规范查询 </a></li>

			</ul>

		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading4">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup4"
					aria-expanded="true" aria-controls="collapseListGroup4"> 电子书管理
				</a>
			</h4>
		</div>
		<div id="collapseListGroup4" class="panel-collapse collapse out"
			role="tabpanel" aria-labelledby="collapseListGroupHeading4"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a href="/ebook/pager/0?ajax=false">电子图书</a></li>
				<li class="list-group-item"><a href="/ebook/student/0?ajax=false">图书浏览</a></li>

			</ul>

		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading" role="tab" id="collapseListGroupHeading5">
			<h4 class="panel-title">
				<a class="" data-toggle="collapse" href="#collapseListGroup5"
					aria-expanded="true" aria-controls="collapseListGroup5"> 题库管理 </a>
			</h4>
		</div>
		<div id="collapseListGroup5" class="panel-collapse collapse out"
			role="tabpanel" aria-labelledby="collapseListGroupHeading5"
			aria-expanded="true">
			<ul class="list-group">
				<li class="list-group-item"><a
					href="/exampaper/query/0?ajax=false"> 试题管理 </a></li>

			</ul>

		</div>
	</div>

     
    

</div>



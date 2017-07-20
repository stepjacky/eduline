<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:set var="base" value="http://${domain}/" />
<c:set var="sbase" value="${base}" />
<fmt:setBundle basename="application" var="appctx"/>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${base}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="<fmt:message key="appName" bundle="${appctx}" />">
<meta name="author" content="qujiakang@126.com(QQ285799123)">

<link rel="icon" href="${sbase}static/images/flag.ico">

<title><fmt:message key="appName" bundle="${appctx}" /></title>

<!-- Bootstrap core CSS -->
<link href="${sbase}static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${sbase}static/lib/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<!-- <link href="static/lib/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"> -->

<!-- Custom styles for this template -->
<!-- 
  <link href="${sbase}static/lib/bootstrap/dashboard.css" rel="stylesheet">
 -->
<link href="${sbase}static/lib/bootstrap/docs.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="${sbase}static/lib/bootstrap-calendar/css/calendar.css">
<link rel="stylesheet"
	href="${sbase}static/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
<link rel="stylesheet"
	href="${sbase}static/lib/font-awesome/css/font-awesome.css">
<link href="${sbase}static/css/index.css" rel="stylesheet">

<script src="${sbase}static/lib/jquery-1.11.2.min.js"></script>
<script src="${sbase}static/lib/lodash.js"></script>
<script src="${sbase}static/lib/jquery.md5.js"></script>
<script src="${sbase}static/lib/bloodhound.js"></script>
<script src="${sbase}static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${sbase}static/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>  
<script src="${sbase}static/lib/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.min.js"></script> 
<script src="${sbase}static/lib/bootstrap3-typeahead.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/underscore-min.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/calendar.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/language/zh-CN.js"></script>
<script	src="${sbase}static/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script	src="${sbase}static/lib/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>   


<!-- <script src="static/lib/ztree/js/jquery.ztree.core-3.5.min.js"></script>   
<script src="static/lib/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>    -->


<!-- <script src="static/lib/ie-emulation-modes-warning.js"></script> -->
<script src="${sbase}static/lib/moment/moment.js"></script>
<script src="${sbase}static/lib/moment/locale/zh-cn.js"></script>
<script src="${sbase}static/lib/highcharts/js/highcharts.js"></script>
<script src="${sbase}static/lib/template.js"></script>
<script src="${sbase}static/lib/tools.js"></script>
<script src="${sbase}static/lib/datacrud.js"></script>

<!-- <script src="static/lib/trees.js"></script>  -->


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="static/lib/html5shiv.min.js"></script>
      <script src="static/lib/respond.min.js"></script>
    <![endif]-->
</head>

<body>

 <header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="headlogo">
      <img src="${sbase}static/images/headlogo.png" >
      </div>
    </div>
    <nav class="collapse navbar-collapse navbar-blues bs-navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/">主页</a></li>
					<li><a href="/profile">个人设置</a></li>
		<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
             ${sysUser.username}
              <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">${sysUser.nickname}</a></li>
                <li><a href="#">${jxf:grade(sysUser.grade)}</a></li>
                <li><a href="#">${jxf:userType(sysUser.userType)}</a></li>
                <li class="divider"></li>
                <li><a href="/logout">退出</a></li>
              </ul>
            </li>				
					 
      </ul>
         
    </nav>
  </div>
</header> 

<div class="bs-docs-header" id="content">
      <div class="container">
        <a href="#">
		<img src="${sbase}static/images/hotlogo/hotlogo.png" style="width:100%;height:200px">
		</a>       
      </div>
</div>
<div class="container bs-docs-container">
<div class="row">

<div class="col-md-12" role="main" id="contentbody">
 

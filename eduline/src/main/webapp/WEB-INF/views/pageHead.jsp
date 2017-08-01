<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


<c:set var="base" value="http://${domain}/" />
<c:set var="sbase" value="${base}" scope="session" />
<c:set var="tableClass" value="table table-striped table-bordered table-hover table-condensed" scope="session" />
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

<link href="${sbase}static/lib/bootstrap/css/bootstrap.css" rel="stylesheet">


    <link rel="stylesheet" href="${sbase}static/lib/bootstrap/css/theme.css">
    <link href="${sbase}static/lib/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<!-- <link href="static/lib/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
 -->
<link href="static/lib/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">


<link rel="stylesheet"
	href="${sbase}static/lib/bootstrap-calendar/css/calendar.css">
<link rel="stylesheet"
	href="${sbase}static/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
<link rel="stylesheet"
	href="${sbase}static/lib/font-awesome/css/font-awesome.css">
<link href="${sbase}static/css/index.css" rel="stylesheet">

    <link rel="stylesheet" href="${sbase}static/lib/bootstrap/bootstrap-custom-nav.css">
<script src="${sbase}static/lib/jquery-1.11.2.js"></script>

<script src="${sbase}static/lib/jquery.md5.js"></script>
<script src="${sbase}static/lib/bloodhound.js"></script>
<script src="${sbase}static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${sbase}static/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>  
<script src="${sbase}static/lib/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${sbase}static/lib/bootstrap3-typeahead.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/underscore-min.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/calendar.js"></script>
<script src="${sbase}static/lib/bootstrap-calendar/js/language/zh-CN.js"></script>
<script	src="${sbase}static/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script	src="${sbase}static/lib/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>   
<script src="${sbase}static/lib/jquery.blockUI.js"></script>
<script src="static/lib/ztree/js/jquery.ztree.all.js"></script>  

<!-- <script src="static/lib/ie-emulation-modes-warning.js"></script> -->
<script src="${sbase}static/lib/moment/moment.js"></script>
<script src="${sbase}static/lib/moment/locale/zh-cn.js"></script>
<script src="${sbase}static/lib/highcharts/js/highcharts.js"></script>


<script src="${sbase}static/lib/fileupload/js/vendor/jquery.ui.widget.js"></script>
<script src="${sbase}static/lib/fileupload/js/jquery.iframe-transport.js"></script>
<script src="${sbase}static/lib/fileupload/js/jquery.fileupload.js"></script>
<script src="${sbase}static/lib/lodash.js"></script>
<script src="${sbase}static/lib/pdfjs/build/pdf.js"></script>
<script src="${sbase}static/lib/pdfobject/pdfobject.js" ></script>
<script src="${sbase}static/lib/vue/vue.js"></script>
<script src="${sbase}static/lib/bootbox.js"></script>
<script src="${sbase}static/lib/pdftool.js"></script>

<script src="${sbase}static/lib/template.js"></script>
<script src="${sbase}static/lib/tools-simple.js"></script>
<script src="${sbase}static/lib/tools.js"></script>
<script src="${sbase}static/lib/datacrud.js"></script>

<script src="${sbase}static/lib/trees.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="static/lib/html5shiv.min.js"></script>
      <script src="static/lib/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <img src="${sbase}static/images/brand.png" >
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/profile">设置</a></li>
                <c:choose>
                    <c:when test="${sysUser.userType==0}">
                        <%@include file="leftNav/admin-navbar.jsp" %>
                    </c:when>
                    <c:when test="${sysUser.userType==1}">
                        <%@include file="leftNav/student-navbar.jsp" %>
                    </c:when>
                    <c:when test="${sysUser.userType==2}">
                        <%@include file="leftNav/teacher-navbar.jsp" %>
                    </c:when>

                    <c:when test="${sysUser.userType==3}">
                        <%@include file="leftNav/parents-navbar.jsp" %>
                    </c:when>
                    <c:when test="${sysUser.userType==4}">
                        <%@include file="leftNav/teacher-navbar.jsp" %>
                    </c:when>
                </c:choose>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        ${sysUser.nickname}[${sysUser.username}] <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">${sysUser.nickname}</a></li>
                        <li><a href="#">${jxf:grade(sysUser.grade)}</a></li>
                        <li><a href="#">${jxf:userType(sysUser.userType)}</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
<div class="row">
<div class="col-md-12 main-body" role="main" id="contentbody">
 

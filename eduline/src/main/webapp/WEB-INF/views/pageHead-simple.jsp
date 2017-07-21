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


    <link href="${sbase}static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${sbase}static/lib/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet">

    <link href="${sbase}static/lib/bootstrap/docs.min.css" rel="stylesheet">

    <link rel="stylesheet"
          href="${sbase}static/lib/font-awesome/css/font-awesome.css">
    <link href="${sbase}static/css/index.css" rel="stylesheet">

    <script src="${sbase}static/lib/jquery-1.11.2.min.js"></script>
    <script src="${sbase}static/lib/lodash.js"></script>
    <script src="${sbase}static/lib/bootstrap/js/bootstrap.min.js"></script>

    <script src="${sbase}static/lib/moment/moment.js"></script>
    <script src="${sbase}static/lib/moment/locale/zh-cn.js"></script>
    <script src="${sbase}static/lib/vue/vue.js"></script>
    <script src="${sbase}static/lib/bootbox.js"></script>
    <script src="${sbase}static/lib/tools-simple.js"></script>

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
<div class="container bs-docs-container">


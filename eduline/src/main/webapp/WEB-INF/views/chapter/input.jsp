<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<%@ include file="../pageHead-simple.jsp" %>
<div class="alert alert-info">
    ${book.name}章节添加,年级,${book.grade.name},课程,${book.course.name}
</div>
<div class="row">
     <div class="col-md-4">



      </div><!-- left list group of chapter -->
      <div class="col-md-8">

      </div>
</div>

<template id="chaperTpl">
    <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="panel-heading">Panel heading</div>
        <!-- List group -->
        <ul class="list-group">
            <li class="list-group-item">Cras justo odio</li>
            <li class="list-group-item">Dapibus ac facilisis in</li>
            <li class="list-group-item">Morbi leo risus</li>
            <li class="list-group-item">Porta ac consectetur ac</li>
            <li class="list-group-item">Vestibulum at eros</li>
        </ul>
    </div>
</template>

<script src="static/scripts/chapter/input.js"></script>
<%@ include file="../pageFoot-simple.jsp" %>

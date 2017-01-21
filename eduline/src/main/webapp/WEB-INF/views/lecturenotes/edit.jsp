<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/lecturenotes/edit.css" rel="stylesheet">
<p class="lead">

编辑<span class="label label-info">教师讲义</span>
</p>
<form class="form-horizontal">
  <input type="hidden" class="form-control" 
      id="id" 
      name="id" value="${bean.id}" > 
 <div class="form-group">
    <label for="name" class="col-sm-2 control-label">名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" 
      id="name" 
      name="name" value="${bean.name}"  placeholder="名称">
    </div>
  </div>
  <div class="form-group">
    <label for="courseName" class="col-sm-2 control-label">课程名称</label>
    <div class="col-sm-10">
      ${bean.courseName}
    </div>
  </div>

  <div class="form-group">
    <label for="gradeName" class="col-sm-2 control-label">年级名称</label>
    <div class="col-sm-10">
     ${bean.gradeName}
    </div>
  </div>
 
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-info persisteFormItem">
    <span class="glyphicon glyphicon-floppy-saved"></span>    
      保存</button>
    </div>
  </div>
</form>
<script src="static/scripts/lecturenotes/edit.js"></script>
<%@ include file="../pageFoot.jsp"%>
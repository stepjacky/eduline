
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/lecturenotes/query.css" rel="stylesheet">

<div class="table-responsive">
  <button type="button" class="btn btn-primary inputItem">
<span class="glyphicon glyphicon-new-window"></span>新增教师讲义
</button>
  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
        <th>课程</th>        
        <th>课程名称</th>        
        <th>年级</th>        
        <th>年级名称</th>        
        <th>所有人</th>        
        <th>所有人名称</th>        
        <th>名称</th>        
        <th>发布日期</th>        
         <th>管理</th>
       </tr>
     </thead>
     <tbody>
       #for(item : pager.dataList?![])
         <tr>
        		<td>${item.course}</td>
        		<td>${item.courseName}</td>
        		<td>${item.grade}</td>
        		<td>${item.gradeName}</td>
        		<td>${item.owner}</td>
        		<td>${item.ownerName}</td>
        		<td>${item.name}</td>
        		<td>${item.firetime}</td>
           <td class="btn-group">
                       
             <button type="button" class="btn btn-info editItem" key="${item.id}">       
        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
             </button>
             <button type="button" class="btn btn-danger removeItem" key="${item.id}">       
        		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
             </button>
             
           </td>
         </tr>       
       #end
     </tbody>
     <tfoot>
       <tr>
         <td colspan="9">
            ${pager.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
</div>
<div id="inputArea" class="table-responsive">
  
</div>

<script src="static/scripts/lecturenotes/query.js"></script>

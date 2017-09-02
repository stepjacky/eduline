<%@include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>

<link href="static/css/homework/pager.css" rel="stylesheet">
<div class="table-responsive">

  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
       <th>详情</th>
       <th>班级</th>
       <th>课程</th>      
       <th>时间</th>      
          <th>管理</th>
       </tr>
     </thead>
     <tbody>
       <c:forEach items="${pager.dataList}" var="item">
         <tr>
           <td>
           <a href="/homework/teacher/unsubmited/${item.workId}/0"
            class="btn btn-sm btn-info"
          
           >
             作业详情
           </a>
           </td>
           <td>${item.groupName}</td>
           <td>${item.courseName}</td>
           <td>                   
             ${jxf:datetimeFormat(item.firetime)}</td>
           <td class="btn-group">         
            
             <button type="button" class="btn btn-danger btn-xs removeItem" key="${item.id}" >       
        		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
             </button>
             
           </td>
         </tr>       
     </c:forEach>
     </tbody>
     <tfoot>
       <tr>
         <td colspan="5">
           ${pager.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
  </div>
<script src="static/scripts/homework/pager.js"></script>

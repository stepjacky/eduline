<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>

<link href="static/css/lecturenotes/pager.css" rel="stylesheet">
<div class="table-responsive">
  <button type="button" class="btn btn-primary inputItem">
<span class="glyphicon glyphicon-new-window"></span>新增教师讲义
</button>
  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
        <th>年级名称</th>       
        <th>课程名称</th>            
        <th>发布教师</th>        
        <th>发布日期</th> 
        <!-- <th>协作</th>      -->  
         <th>管理</th>
       </tr>
     </thead>
     <tbody>
        <c:forEach items="${pager.dataList}" var="item">
         <tr>
        	<td>${item.gradeName}</td>
        	<td>${item.courseName}</td>       
        	<td>${item.ownerName}</td>
        	<td>${jxf:datetimeFormat(item.firetime)}</td>
        	<%-- <td><input class="co-lecture" type="checkbox" value="${item.id}"
        	  ${item.shared==1?'checked':''}
        	  
        	  disabled
        	></td> --%>
           <td>
             <button type="button" data-key="${item.id}" data-name="${item.courseName}" class="btn btn-info btn-xs expandItem"> <i class="fa fa-expand" aria-hidden="true"></i></button>   
            
              <button type="button" data-key="${item.id}" data-name="${item.name}" class="btn btn-info btn-xs sharedItem">
               <i class="fa fa-share-alt-square" aria-hidden="true"></i>
             </button>
             
             <button type="button" class="btn btn-danger btn-xs removeItem" key="${item.id}" >       
        		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
             </button>
             
            
           </td>
         </tr>       
       </c:forEach>
     </tbody>
     <tfoot>
       <tr>
         <td colspan="7">
           ${pager.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
  </div>
<div id="inputArea" class="table-responsive">
  
</div>
<script>
  var dtype=false;
</script>
<script src="static/scripts/lecturenotes/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
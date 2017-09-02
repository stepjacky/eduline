<%@include file="../pageHead-new.jsp"%>
<%@ page pageEncoding="UTF-8"%>

<div class="table-responsive">
  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
      <tr>
         <th>名称</th>
         <th>课程</th>
         <th>年级</th>         
         <th>作业</th>
       </tr>
     </thead>
     <tbody>
       <c:forEach items="${lectures.dataList}" var="item"  >
         <tr>
           <td>${item.name}</td>
           <td>${item.courseName}</td>
           <td>${item.gradeName}</td>
         
           <td class="btn-group">
                
             <a class="btn btn-info btn-xs" href="/homework/homework/chapters/${groupId}/${item.id}" >       
        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
             </a>
                         
           </td>
         </tr>       
      </c:forEach>
     </tbody>
     <tfoot>
       <tr>
         <td colspan="4">
           ${lectures.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
  </div>
<%@include file="../pageFoot.jsp"%>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<div class="table-responsive">
 <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
         <th>班级名</th>
         <th>年级</th>
         <th>管理</th>
       </tr>
     </thead>
     <tbody>
      <c:forEach items="${groups}" var="item">
         <tr>
          <td>${item.name}</td>
          <td>${jxf:grade(item.grade)}</td>
           <td class="btn-group">
                
             <a class="btn btn-info  btn-xs preSend"  href="/homework/homework/lecture/${item.course}/${item.groupId}/0" >       
        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
             </a>
                       
           </td>
         </tr>       
    </c:forEach>
     </tbody>
    <%--  <tfoot>
       <tr>
         <td colspan="3">
           ${pager.pagination}
         </td>
       </tr>
     </tfoot> --%>
  </table>
  </div>

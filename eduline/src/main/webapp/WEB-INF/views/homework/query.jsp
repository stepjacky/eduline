<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/homework/query.css" rel="stylesheet">

<div class="table-responsive">
  <button type="button" class="btn btn-primary inputItem">
<span class="glyphicon glyphicon-new-window"></span>新增家庭作业
</button>
  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
         <th>管理</th>
       </tr>
     </thead>
     <tbody>
       #for(item : pager.dataList?![])
         <tr>
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
         <td colspan="1">
            ${pager.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
</div>
<div id="inputArea" class="table-responsive">
  
</div>

<script src="static/scripts/homework/query.js"></script>
<%@ include file="../pageFoot.jsp"%>
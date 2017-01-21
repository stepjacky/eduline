<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
 
<div class="container-fluid">
  <div class="row">
    <div class="col-md-4">
       <ul id="cpt" class="ztree"></ul>
    </div>
    <div class="col-md-8" id="letcpt">
   
  
    <h3><span class="label label-info">${group.name}作业</span>,
    <span class="label label-default">从[${simpleNow}]算起 </span></h3>
    <div class="input-group">
      <select class="form-control">
        <c:forEach begin="1" end="30" var="days">
            <option value="${days}">${days}天</option>
        </c:forEach>
      </select>
      <span class="input-group-btn">
        <button type="button" class="btn btn-info sendWork">布置本章作业</button>
      </span>
    </div><!-- /input-group -->
    	
    </div>
        
   
  </div>
</div> 
<script>
  var nid = '${bean.id}';
  var name='${bean.name}';
  var gid = '${groupId}';
</script>  
<script src="static/scripts/homework/chapters.js"></script>  
<%@ include file="../pageFoot.jsp"%>
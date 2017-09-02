<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="center-block" style="text-align:center">
  <h3> <span class="label label-default">${card.title}</span></h3>
</div>
<table class="table table-striped table-bordered table-hover table-condensed">

<thead>
  <tr>
   <th>课程</th>
   <th>老师</th>
   <th>分数</th>
   <th>评语</th>
  </tr>
  
</thead>
  <tbody>
    <c:forEach items="${card.details}" var="item">
       <tr>
         <td>${item.course}</td>
         <td>${item.teacher}</td>
         <td>${item.scoreValue}</td>
         <td>${item.remark}</td>
       </tr>    
    </c:forEach>
  </tbody>
</table>


<%@ include file="../pageFoot.jsp"%>
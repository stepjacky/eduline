<%@ include file="pageHead.jsp" %>
 <c:choose>
   <c:when test="${sysUser.userType==0}">
      <%@ include file="admin.jsp" %>
   </c:when>
   <c:when test="${sysUser.userType==1}">
      <%@ include file="student.jsp" %>
   </c:when>
   <c:when test="${sysUser.userType==2}">
      <%@ include file="teacher.jsp" %>
   </c:when>
  
   <c:when test="${sysUser.userType==3}">
      <%@ include file="parents.jsp" %>
   </c:when>
  <c:when test="${sysUser.userType==4}">
      <%@ include file="teacher.jsp" %>
   </c:when>
 </c:choose>
 		
<%@ include file="pageFoot.jsp" %>

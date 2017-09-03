<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
${error}
<hr>
<p>${message}</p>
<p>
    <c:forEach var="er" items="errors">
        ${er}  <br/>
    </c:forEach>
</p>
<hr/>
<a href="/login">Login</a> or contact us!

<%@ include file="pageHead.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:if test="${not empty message }">
	<div class="alert alert-success" role="alert" style="width:400px;margin:0 auto">
		${message}
	</div>
</c:if>
<div class="panel panel-info" style="width:400px;margin:0 auto">
	<div class="panel-heading">
		<h3 class="panel-title">${ownerUser.nickname}个人资料修改</h3>
	</div>
	<div class="panel-body">
		<form action="/modify/password" method="post">
		
		<input type="hidden" name="username" value="${sysUser.username}">
		<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
			<div class="form-group">
				<label for="password">旧密码</label> 
				<input
					type="password" class="form-control" id="password"
					name="password"
					placeholder="输入旧密码">
			</div>
			<div class="form-group">
				<label for="passwordn">输入新密码</label> <input
					type="password" class="form-control" 
					id="passwordn"
					name="passwordn"
					placeholder="输入新密码">
			</div>
			
			<div class="form-group">
				<label for="passwordr">输入新密码</label> <input
					type="password" class="form-control" 
					id="passwordr"
					name="passwordr"
					placeholder="确认新密码">
			</div>
			<button type="submit" class="btn btn-info">保存修改</button>
		</form>
	</div>
</div>
<%@include file="pageFoot.jsp" %>
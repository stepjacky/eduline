<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北京市新英才学校剑桥中心</title>
<link href="static/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>

     <div id="login">
         <c:if test="${not empty error }">
            <div>
                Failed to login.
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
                  Reason: ${SPRING_SECURITY_LAST_EXCEPTION.message}                 
                </c:if>
            </div>
        </c:if>
			   <c:if test="${not empty logout}">
			   <div>
                you have logout                
               </div>
			   	</c:if>
	     <div id="top">
		      <div id="top_left"><img src="static/images/login/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			 
			   <form action="/login" id="loginForm" method="post">
			     <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" /> 
			       <div id="user">用 户
			         <input type="text" name="username" value="${testable?'cuizheng':'' }"/>
			       </div>
				   <div id="password">密   码
				     <input type="password" name="password" value="${testable?'123456':'' }" />				    
				   </div>
				   <div id="btn">
				   <a href="javascript:;" onclick="login();">登录</a>
				   <a href="javascript:;" onclick="reset();">清空</a></div>
			    </form>
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">北京市新英才学校剑桥中心教育系统 2015 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
	<script>
	   function login(){
		   document.getElementById('loginForm').submit();
	   }
	   function reset(){
		   document.getElementById('loginForm').reset();
	   }

	</script>
</body>
</html>

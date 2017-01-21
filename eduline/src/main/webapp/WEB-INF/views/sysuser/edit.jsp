<%@page import="java.util.*"%>
<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/sysuser/edit.css" rel="stylesheet">

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">修改${bean.nickname}信息</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" action="/sysuser/persiste" method="post">
			<input type="hidden" name="username" value="${bean.username}">
			<input type="hidden" name="userType" value="${bean.userType}">
			<div class="form-group">
				<label for="nickname" class="col-sm-2 control-label">昵称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nickname"
						name="nickname" value="${bean.nickname}" placeholder="输入昵称">
				</div>
			</div>
			<div class="form-group">
				<label for="surname" class="col-sm-2 control-label">姓</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="surname" name="surname"
						value="${bean.surname}" placeholder="输入姓">
				</div>
			</div>
			<div class="form-group">
				<label for="givename" class="col-sm-2 control-label">名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="givename"
						name="givename" value="${bean.givename}" placeholder="输入名">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<label>
					<input type="radio" name="sex" value="0"
						${bean.sex==0?"checked":""}>
						<span class="label label-primary">女</span>
					</label>	
					<label>	
						<input type="radio"
						name="sex" value="1" ${bean.sex==1?"checked":""}>
						<span  class="label label-primary">男</span>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="birthday" class="col-sm-2 control-label">生日</label>
				<div class="col-sm-10">
					<input type="text" class="form-control datepicker"
						value="${jxf:dateFormat(bean.birthday)}"> <input
						type="hidden" name="birthday" value="${bean.birthday}">
				</div>
			</div>
   
   <%
      Map<Integer,String> utypeInfo = new HashMap<>();
      utypeInfo.put(1, "学生");
      utypeInfo.put(2, "老师");
      utypeInfo.put(3, "家长");
      utypeInfo.put(4, "事件管理员");
      pageContext.setAttribute("uinfos", utypeInfo);
   %>     
           
   <div class="form-group">
    <label class="col-sm-2 control-label">用户类别</label>
    <div class="col-sm-10">
    
      <div class="radio">
      
        <c:forEach begin="1" end="4" var="item">
          <label>
             <input type="radio"  name="userType" value="${item}" ${bean.userType==item?'checked':''} >
             <span class="label label-primary">${uinfos[item]}</span>
          </label>   
        
        </c:forEach>
           
              
          
                
                 
      </div>   
    </div>
  </div>
           
           
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-info persisteFormItem">
						<span class="glyphicon glyphicon-floppy-saved"></span>保存
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="static/scripts/sysuser/edit.js"></script>
<%@ include file="../pageFoot.jsp" %>
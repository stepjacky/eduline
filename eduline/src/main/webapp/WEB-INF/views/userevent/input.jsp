<%@ include file="../pageHead-new.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>

<div class="btn-group">
	<button class="btn btn-primary" data-calendar-nav="prev">
     <i class='fa fa-arrow-circle-left'></i>
    </button>
	<button class="btn btn-default" data-calendar-nav="today">今天</button>
	<button class="btn btn-primary" data-calendar-nav="next">
    <i class='fa fa-arrow-circle-right'></i>
    </button>
   
</div>
<div class="btn-group pull-right">
				<button class="btn btn-warning" data-calendar-view="year">年</button>
				<button class="btn btn-warning" data-calendar-view="month">月</button>
				<button class="btn btn-warning" data-calendar-view="week">周</button>
				<button class="btn btn-warning" data-calendar-view="day">日</button>
				<a href="/userevent/repeated/0" class="btn btn-info">计划事件管理</a>
</div>
<h3 class="title"></h3>
<div id="calendar"></div>

<ul id="eventlist"></ul>



<c:if test="${sysUser.userType!=3}">	
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">添加日程</h3>
	</div>
	<div class="panel-body">
<form class="form-horizontal" id="dataForm">
  <div class="form-group">
    <label  class="col-sm-2 control-label">名称</label>
    <div class="col-sm-10">
      <input type="text" name="name" class="form-control" placeholder="请输入名称">
    </div>
  </div>
  <div id="timeOccured">
  
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    
      <div class="radio">
      
           
            <label>
             <input type="radio"  name="className" value="event-important" checked>
             <span class="label label-primary">&nbsp;&nbsp;&nbsp;&nbsp;</span>
             

            </label>
            <label>
             <input type="radio"  name="className" value="event-success">
              <span class="label label-success">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </label>
          
            <label>
             <input type="radio"  name="className" value=" event-warning" checked>
             <span class="label label-warning">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </label>
            <label>
             <input type="radio"  name="className" value="event-info">
             <span class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;</span>  
            </label>      
            <label>
             <input type="radio"  name="className" value="event-inverse">
              <span class="label label-inverse">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </label>      
            <label>
             <input type="radio"  name="className" value="event-special">
              <span class="label label-default">&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </label>      
      </div>   
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    
      <div class="radio">
      
            <c:if test="${sysUser.userType==4}">
            <label>
             <input type="radio" class="eventType" name="eventType" value="0">全体可见
            </label>
            <label>
             <input type="radio" class="eventType"  name="eventType" value="1">所有老师可见
            </label>
            </c:if>
            <label>
             <input type="radio" class="eventType"  name="eventType" value="2" checked>仅自己可见
            </label>
            <label>
             <input type="radio" class="eventType"  name="eventType" value="3">仅自己可见且按周重复
            </label>      
      </div>   
    </div>
  </div>
  <div  id="repeatRange">
  
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">内容</label>
    <div class="col-sm-10">
      <textarea class="form-control" name="content" ></textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-info persisteFormItem">保存</button>
    </div>
  </div>
</form>
	</div>
</div>
</c:if>

<script src="static/scripts/userevent/input.js"></script>

<div class="modal fade" id="events-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
                  <i class='fa fa-times-circle-o'></i>
                  </button>
				<!-- <h4>用户日程详情</h4> -->
			</div>
			<div class="modal-body" style="min-height: 80px;height:auto"></div>
			<!-- <div class="modal-footer">
				<a href="#" data-dismiss="modal" class="btn">关闭</a>
			</div> -->
		</div>
	</div>
</div>
<%@ include file="../pageFoot.jsp"%>
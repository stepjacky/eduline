<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<div class="panel panel-info">
    <div class='panel-heading'>
      ${bean.name}
    </div>
	<div class="panel-body">
	
         ${bean.content}
         <hr>         
         <span class="label label-default">${jxf:datetimeFmt(bean.starttime,'yyyy-MM-dd HH:mm')}</span>
         <i class="fa fa-long-arrow-right"></i>
         <span class="label label-default">${jxf:datetimeFmt(bean.endtime,'yyyy-MM-dd HH:mm')}</span>
         <button class="removeItem btn btn-danger btn-xs" key="${bean.id}" data-dismiss="modal">
		  <i class="fa fa-remove"></i>
		</button>
                  
	</div>
	
</div>
<script>
$(function(){
	   
    var da  =  window.DataAdmin;
    da.init(
    {
   	 		context:'/',       		
			namespace : 'userevent',			
			dataFormId:  'userevent_form'
    }); 
    da.addRemoveListener();
});

</script>
 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'sysuser',			
			dataFormId:  'sysuser_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener();    
    dataAdmin.addEditListener(); 
    //dataAdmin.addDatePickerListener();   
    $("button.upgradeGrade").on('click',function(){
    	$.post('/sysuser/upgrade/grade',function(data){
    		notify("年级更新完毕");
    		
    		//window.location.href=window.location.href;
    	});
    });
    $("button.resetItem").on('click',function(){
    	var key = 	$(this).attr('key');
    	if(confirm("你确认要重置该用户"+key+"的密码为初始密码?")){
    		$.post('/reset/password/'+key,function(data){
        		notify("用户"+key+"密码已经重置为初始密码");        		
        	});
    	}
    });
    
    $('#fileupload').fileupload({
		url : '/sysuser/upload',
		dataType : 'text',
		paramName : 'file',
		multipart : true,
		formData : function() {
			var data = [];			
			data.push(csrfinfo.formData);
			return data;
		},
		acceptFileTypes : /(\.|\/)(xls|xlsx?)$/i,
		maxFileSize : 10000000,// 10m
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progress .progress-bar').css('width', progress + '%');
		},
		done : function(e, data) {
			window.location.href='/sysuser/pager/0?ajax=false';
		}
	});
    
});
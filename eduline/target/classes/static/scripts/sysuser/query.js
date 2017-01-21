 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'sysuser',			
			dataFormId:  'sysuser_form'
    }); 
    //dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener();  
    dataAdmin.addEditListener(); 
    dataAdmin.addDatePickerListener();  
    $("button.resetItem").on('click',function(){
    	var key = 	$(this).attr('key');
    	if(confirm("你确认要重置该用户"+key+"的密码为初始密码?")){
    		$.post('/reset/password/'+key,function(data){
        		notify("用户"+key+"密码已经重置为初始密码");        		
        	});
    	}
    });
});
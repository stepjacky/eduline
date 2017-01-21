 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'userevent',			
			dataFormId:  'userevent_form'
    });    
    dataAdmin.addPagerListener(false);
    $('button.removeAny').on('click',function(){
    	var key = $(this).attr('key');
    	var tr = $(this).parents('tr');
    	$.post('/userevent/repeated/remove/'+key,function(){
    		tr.remove();
    	});
    });
     
});
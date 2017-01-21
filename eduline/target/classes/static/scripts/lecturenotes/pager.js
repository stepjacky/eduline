 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',   
            addCallback:'/lectures/0?ajax=false',
			namespace : 'lecturenotes',			
			dataFormId:  'lecturenotes_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(false);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('lecturenotes_form');    
    dataAdmin.addEditListener(); 
   
    $('button.expandItem').on('click',function(){
    	var k = $(this).data('key');
    	var n = $(this).data('name');
    	console.log(dtype);
    	window.location.href='/lecturenotes/anyway/expand?noteId='+k+'&name='+n+(dtype?'&dtype=student':'');
    });
    
    $('button.sharedItem').on('click',function(){
    	var k = $(this).data('key');
    	var n = $(this).data('name');
    	window.location.href='/lecturenotes/anyway/start-shared?noteId='+k+'&name='+n;
    });
    
    $(':checkbox.co-lecture').on('click',function(){
    	
    	var id = $(this).val();
    	if($(this).prop('checked'))
    	   $.get('/lecturenotes/set/publiced/1/'+id);
    	else
    		$.get('/lecturenotes/set/publiced/0/'+id);
    });
});
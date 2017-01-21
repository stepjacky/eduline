 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'course',			
			dataFormId:  'course_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(false);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('course_form');    
    dataAdmin.addEditListener();
    
    
    $('input:radio.set-type').on('click',function(){
    	var id=$(this).attr('key');
    	var type = $(this).val();
    	var compiled = _.template("/course/settype/<%= id%>/<%= type%>");
    	var url = compiled({ 'id': id,'type':type});
    	$.post(url);
    });
});
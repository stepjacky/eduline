 $(function(){
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'examscore',			
			dataFormId:  'examscore_form'
    });    
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();    
	
    $('button.updatePartial').on('click',function(){
    	
        var id = $(this).attr('key');
        var val = $('#'+id+"_scoreValue").val();
        if(isNaN(val)) return;
        var url = tmpstr("/examscore/update/partial?id=<%=id%>&props=scoreValue:<%=value%>",{id:id,value:val});
        $.post(url);
    	
    });
    
});

$(function(){
	var crtInst = {};
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'violates',			
			dataFormId:  'violates_form'
    }); 
   
    dataAdmin.addDatePickerListener(setFireTime);
    
    function setFireTime(time){
      	var key = $(this).attr('key'); 
        theInst[key]['fireTime'] =time;    
    }
    
    $(":radio.affirmative").on('click',function(event){
        var key = $(this).attr('key'); 
        theInst[key]['affirmative'] = $(this).val();    	
        	 
         
    });
    $("textarea.content").on('blur',function(event){
    	  
    	 var key = $(this).attr('key'); 
         theInst[key]['content'] = $(this).val();   
    });
    
    $("input:checkbox.validedCheck").on('click',function(event){
    	var key = $(this).attr('key'); 
    	theInst[key]['enabled'] = true;
    });
    
    
    $("button.savedItems").on('click',function(event){
    	var parray = [];
    	for(var key in theInst){
    		if(theInst[key]['enabled']){
    			
    			var obj = $.extend({},theInst[key]);
    			delete obj['enabled'];
    			parray.push(obj);
    		}
    	}    	
    	var param = "data="+JSON.stringify(parray);
    	var url="/violates/persisties";
    	$.post(url,param,function(){
    		//loadUrl('/violates/violate/0?order=fireTime desc');
			layer.alert('录入成功');
    	})
    });
    
    $(".scoreValue").on('change',function(event){
    	
    	 var key = $(this).attr('key'); 
         theInst[key]['scoreValue'] = $(this).val();  
         
    });
});
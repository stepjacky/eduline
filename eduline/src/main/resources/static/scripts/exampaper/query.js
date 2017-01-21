 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'exampaper',			
			dataFormId:  'exampaper_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);  
    dataAdmin.addQueryListener('exampaper_form');  
   
   
});
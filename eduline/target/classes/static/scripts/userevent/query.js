 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'userevent',			
			dataFormId:  'userevent_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('userevent_form');  
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();    
});
 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'authority',			
			dataFormId:  'authority_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('authority_form');  
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();    
});
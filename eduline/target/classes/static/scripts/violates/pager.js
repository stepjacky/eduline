 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'violates',			
			dataFormId:  'violates_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(false);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('violates_form');    
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();     
});
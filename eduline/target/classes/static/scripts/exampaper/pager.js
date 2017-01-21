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
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('exampaper_form');    
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();     
});
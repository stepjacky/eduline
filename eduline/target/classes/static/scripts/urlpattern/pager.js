 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'urlpattern',			
			dataFormId:  'urlpattern_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('urlpattern_form');    
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();     
});
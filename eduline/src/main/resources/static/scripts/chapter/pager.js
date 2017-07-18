 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'chapter',
			dataFormId:  'notechapter_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('notechapter_form');    
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();     
});
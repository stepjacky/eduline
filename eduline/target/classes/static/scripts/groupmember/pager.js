 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'groupmember',			
			dataFormId:  'groupmember_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('groupmember_form');    
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();     
});
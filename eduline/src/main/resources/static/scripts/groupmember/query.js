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

    $('.action-open-group,.action-input-score').on('click',function (event) {
        event.preventDefault();
        loadUrl($(this).attr('href'));
        return false;
    })
});
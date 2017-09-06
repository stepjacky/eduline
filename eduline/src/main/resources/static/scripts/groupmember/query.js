 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'groupmember',			
			dataFormId:  'groupmember_form'
    }); 
    dataAdmin.addInputListener("contentbody");
    dataAdmin.addPagerListener();
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('groupmember_form');  
    dataAdmin.addDatePickerListener();

    $('.action-open-group,.action-input-score').on('click',function (event) {
        event.preventDefault();
        loadUrl($(this).attr('href'));
        return false;
    })
});
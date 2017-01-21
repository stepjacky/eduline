$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'userevent',			
			dataFormId:  'userevent_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('userevent_form');
    dataAdmin.addDatePickerListener();    
});
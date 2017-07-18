$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'chapter',
			dataFormId:  'notechapter_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('notechapter_form');
    dataAdmin.addDatePickerListener();    
});
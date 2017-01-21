$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'authority',			
			dataFormId:  'authority_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('authority_form');
    dataAdmin.addDatePickerListener();    
});
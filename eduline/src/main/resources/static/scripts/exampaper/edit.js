$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'exampaper',			
			dataFormId:  'exampaper_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('exampaper_form');
    dataAdmin.addDatePickerListener();    
});
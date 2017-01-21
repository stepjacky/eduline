$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'examscore',			
			dataFormId:  'examscore_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('examscore_form');
    dataAdmin.addDatePickerListener();    
});
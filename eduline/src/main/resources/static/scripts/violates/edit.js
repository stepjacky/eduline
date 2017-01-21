$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'violates',			
			dataFormId:  'violates_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('violates_form');
    dataAdmin.addDatePickerListener();    
});
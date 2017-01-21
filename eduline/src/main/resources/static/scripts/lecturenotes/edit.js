$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'lecturenotes',			
			dataFormId:  'lecturenotes_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('lecturenotes_form');
    dataAdmin.addDatePickerListener();    
});
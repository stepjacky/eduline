$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'grade',			
			dataFormId:  'grade_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('grade_form');
    dataAdmin.addDatePickerListener();    
});
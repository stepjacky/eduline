$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'sysuser',			
			dataFormId:  'sysuser_form'
    }); 
    //dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener();
    dataAdmin.addDatePickerListener();    
});
$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'urlpattern',			
			dataFormId:  'urlpattern_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('urlpattern_form');
    dataAdmin.addDatePickerListener();    
});
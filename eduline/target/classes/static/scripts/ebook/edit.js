$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'ebook',			
			dataFormId:  'ebook_form'
    }); 
    dataAdmin.addRemoveListener();
    dataAdmin.addPersisteFormListener('ebook_form');
    dataAdmin.addDatePickerListener();    
});
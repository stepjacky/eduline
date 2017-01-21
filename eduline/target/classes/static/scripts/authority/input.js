$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'authority',			
			dataFormId:  'authority_form'
    }); 
    dataAdmin.addAppendListener();
    dataAdmin.addEarseListener();
    dataAdmin.addPersisteDataListener(); 
    dataAdmin.addJsonSourceListener();  
    dataAdmin.addDatePickerListener();    
});
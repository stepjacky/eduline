$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'grade',			
			dataFormId:  'grade_form'
    }); 
    dataAdmin.addAppendListener();
    dataAdmin.addEarseListener();
    dataAdmin.addPersisteDataListener(); 
    dataAdmin.addJsonSourceListener();  
    dataAdmin.addDatePickerListener();    
});
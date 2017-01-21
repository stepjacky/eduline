$(function(){   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',       		
			namespace : 'course',			
			dataFormId:  'course_form'
    });    
    dataAdmin.addPersisteFormListener();   
});
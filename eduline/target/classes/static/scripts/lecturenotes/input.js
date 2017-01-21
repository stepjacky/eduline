$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',  
   	 		addCallback:'/lectures/0?ajax=false',
			namespace : 'lecturenotes',			
			dataFormId:  'lecturenotes_form'
    }); 
    dataAdmin.addAppendListener();
    dataAdmin.addEarseListener();
    dataAdmin.addPersisteDataListener(); 
    dataAdmin.addJsonSourceListener();        
});
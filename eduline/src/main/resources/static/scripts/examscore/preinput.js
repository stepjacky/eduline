$(function(){
	 var dataAdmin  =  window.DataAdmin;
	    dataAdmin.init(
	    {
	   	 		context:'/',       		
				namespace : 'examscore',			
				dataFormId:  'examscore_form'
	    }); 
	 dataAdmin.addJsonSourceListener(); 
	 dataAdmin.addDisableListener(true);
	 dataAdmin.addQueryListener('qform');
});
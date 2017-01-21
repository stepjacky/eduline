 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'lecturenotes',			
			dataFormId:  'lecturenotes_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('lecturenotes_form');  
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();    
});
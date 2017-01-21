 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'ebook',			
			dataFormId:  'ebook_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(ajax);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('ebook_form');  
    //dataAdmin.addJsonSourceListener(); 
    dataAdmin.addDatePickerListener();    
});
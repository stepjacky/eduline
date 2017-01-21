$(function(){
     
	 var dataAdmin  =  window.DataAdmin;
	    dataAdmin.init(
	    {
	   	 		context:'/',       		
				namespace : 'groupmember',			
				dataFormId:  'groupmember_form'
	    }); 	   
	    dataAdmin.addJsonSourceListener();  
	 $('#fileupload').fileupload({
	        dataType: 'json',	 
	        done: function (e, data) {
	           notify("上传完成!");
	        },
	        acceptFileTypes:/(\.|\/)(pdf|docx?)$/i,
	        maxFileSize:10000000,//10m
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress .progress-bar').css(
	                'width',
	                progress + '%'
	            );
	        } 
	    });
    
});
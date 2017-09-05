 $(function(){
    
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
            context:'/',         		
			namespace : 'examscore',			
			dataFormId:  'examscore_form'
    }); 
    dataAdmin.addInputListener("inputArea");
    dataAdmin.addPagerListener(false);
    dataAdmin.addRemoveListener();
    dataAdmin.addQueryListener('examscore_form');    
    dataAdmin.addJsonSourceListener();
    dataAdmin.addDatePickerListener();   
    var filedata = null;
    $('#fileupload').fileupload({
		url : '/examscore/upload',
		dataType : 'text',
		paramName : 'file',
		multipart : true,
		formData : function() {
			var data = [];			
			data.push(csrfinfo.formData);
			return data;
		},
		autoUpload:false,
		maxFileSize : 10000000,// 10m
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progress .progress-bar').css('width', progress + '%');
		},
		add :function(e,data){
			filedata = data;
		},
		done : function(e, data) {
			notify("成绩导入完成");
		}
	});
    
    $('#doUpload').on('click',function(){
    	if(filedata)
    	filedata.submit();
    });
});
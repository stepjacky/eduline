$(function(){
   
	 $('#fileupload').fileupload({
	        dataType: 'json',	 
	        acceptFileTypes:/(\.|\/)(pdf|docx?)$/i,
	        maxFileSize:10000000,//10m
	        formData: function(form){
	        	var data = form.serializeArray();
	        	data.push(csrfinfo.formData);
	        	return data;
	        },
	       
	        //redirect:'/ebook/pager/0?ajax=false',
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress .progress-bar').css(
	                'width',
	                progress + '%'
	            );
	        },	
	        fail:function(e,data){
	        	notify("上传书籍完成,还可以继续上传");
	        	$(e.target).parents('form')[0].reset();
	        },
	        done:function (e, data) {
	            log(data);	        	
	        },
	        always:function(e,data){
	        	//window.location.href=window.location.href;
	        }
	    });
	 $('#coverupload').fileupload({
	        dataType: 'json',	 
	        acceptFileTypes:/(\.|\/)(png|jpg|gif?)$/i,
	        maxFileSize:10000000,//10m
	        formData:[csrfinfo.formData],
	     
	        //redirect:'/ebook/pager/0?ajax=false',
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress .progress-bar').css(
	                'width',
	                progress + '%'
	            );
	        },
	        fail:function(e ,data){
	        	
	        	var path = (data.jqXHR);
	        	$(e.target).next("input:hidden").val(path.responseText);
	        	$("#prevImg").attr('src','/ebook/cover/path?path='+encodeURIComponent(path.responseText));
	        },
	        done:function (e, data) {	        	     	
	        },
	        always:function(e,data){	        	
	        }
	    });
	 var seltags = {};
	 $('button.small-tag').on('click',function(){
		 var tag = $(this).attr('key');
		 seltags[tag] = !seltags[tag];
		 for(var t in seltags) if (!seltags[tag])delete seltags[tag];
		 
		 $('#tags').val(Object.keys(seltags).join(','));
	 })
});
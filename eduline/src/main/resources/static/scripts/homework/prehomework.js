/**
 * 
 */
$(function(){
	var fileData = null;
	var homeId = null;
	$('#fileupload').fileupload({		
		dataType : 'text',
		paramName : 'answerDoc',
		multipart : true,
		formData : function() {
			var data = [];			
			data.push(csrfinfo.formData);
			return data;
		},
		acceptFileTypes : /(\.|\/)(jpg|png|doc|docx?)$/i,
		maxFileSize : 10000000,// 10m
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progress .progress-bar').css('width', progress + '%');
		},
		done : function(e, data) {
			$('.docName img').attr('src','/gradle/'+data.result);
		},
		add: function(e,data){
			if(homeId==null){
				alert("请先选择作业");
				return ;
			}
			data.url='/homework/student/answerdoc/update?id='+homeId;
			data.submit();
		}
		
	});
	
	$('input:radio.answerName').on('click',function(){
		homeId = $(this).val();
		
	});
})
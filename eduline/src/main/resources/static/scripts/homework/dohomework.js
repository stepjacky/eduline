$(function(){

    PDFObject.embed(url, "#pdfcontent",{
        height: "550px",
        page: '1',
        pdfOpenParams: {
            view: 'Fit',
            pagemode: 'thumbs',
            navpanes:1,
            toobar:0
        }
    });
	
	var fileId = null;
	var answer  = [];
	for(var i=0;i<len;i++){
		answer[i] = 'x';
	}
	$('#fileupload').fileupload({
		url : '/homework/student/preview/homework/'+hwid,
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
			fileId = data.result;
			
			$('.docName img').attr('src','/gradle/'+data.result);
		}
	});
	$('button.unblock').on('click',$.unblockUI);
	$('input:radio.answer').on('click',function(){
		var index = parseInt($(this).data('key'));
		var val = $(this).val();
		answer[index] = val;
		$(this).parents('td').css('background-color','transparent');
		$(this).parent('label').siblings('label').css('background-color','#e2e2e2');
		$(this).parent('label').css('background-color','#060');
		
	});	
	$('button.submitHomework').on('click',function(){
		
		 var unselect = [];
			for(var i=0;i<len;i++){
				if(answer[i]=='x'){
					unselect.push(i+1);
				}
			}
			var hasNoSelect = unselect[0]!=undefined;
			
			
			if(hasNoSelect && fileId==null){
				notify('你还没有填写任何习题答案,请检查');
				if(hasNoSelect){
					$('td.answer-td').css('background-color','transparent');
					notify('你还有'+unselect.join(',')+'<br/>题没有作答,请检查')
					for(var j=0;j<unselect.length;j++){
						$('td.answer-'+unselect[j]+'-td').css('background-color','#fc3');
					}
				
					//return;
					
				}
				if(fileId==null){
					notify('没有选择答案文件');
					//return;
				}
				//return;
			}

        layer.confirm('is not?', function(index){

        	if(index==0){
                var url = '/homework/student/homework/submit/'+hwid;
                var postData = {
                    'answer':answer.join(''),
                    'fileId':fileId
                };
                postData[csrfinfo.name]=csrfinfo.token;
                $.post(url,postData).done(function(){
                    window.location.href='/homework/student/unscored/0';
                });
			}

            layer.close(index);
        });
		
	});
	
});
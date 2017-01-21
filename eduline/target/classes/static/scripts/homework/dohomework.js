$(function(){
	var pdfDoc = null, pageNum = 1,pageNums=1, pageRendering = false, pageNumPending = null, scale = 2.0, canvas = document
			.getElementById('pdfcanvas'), ctx = canvas.getContext('2d');
	function renderPage(num) {
		pageRendering = true;
		// Using promise to fetch the page
		pdfDoc.getPage(num).then(function(page) {
			var viewport = page.getViewport(scale);
			canvas.height = viewport.height;
			canvas.width = viewport.width;

			// Render PDF page into canvas context
			var renderContext = {
				canvasContext : ctx,
				viewport : viewport
			};
			var renderTask = page.render(renderContext);

			// Wait for rendering to finish
			renderTask.promise.then(function() {
				pageRendering = false;
				if (pageNumPending !== null) {
					// New page rendering is pending
					renderPage(pageNumPending);
					pageNumPending = null;
				}
			});
		});

		$('.pageNum').html(pageNum);
	}
	function queueRenderPage(num) {
		if (pageRendering) {
			pageNumPending = num;
		} else {
			renderPage(num);
		}
	}
	function onPrevPage() {
		if (pageNum <= 1) {
			return;
		}
		pageNum--;
		queueRenderPage(pageNum);
	}
	function onNextPage() {
		if (pageNum >= pageNums) {
			return;
		}
		pageNum++;
		$('.pageNum').html(pageNum);
		queueRenderPage(pageNum);
	}

	
	$('.prevPage').on('click', onPrevPage);
	$('.nextPage').on('click', onNextPage);
	PDFJS.getDocument(url).then(function(doc) {		
		pdfDoc = doc;		
		pageNums = doc.numPages;
		$('.pageNum').html(pageNum);
        $('.pageCount').html(pdfDoc.numPages);
		// Initial/first page rendering
		renderPage(pageNum);
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
			
			
		
		
			$.blockUI(
					{
						message: confirmDlg,
						css:{
							border:'1px solid #ccc'
						},
						onUnblock:function(element, options){
                           
							var actBtn = $(options.originalEvent.target);
							if(actBtn.val()=='yes'){
							
								var url = '/homework/student/homework/submit/'+hwid;
								var postData = {
									'answer':answer.join(''),
									'fileId':fileId					
								};
								postData[csrfinfo.name]=csrfinfo.token;
								console.log(postData);
								$.post(url,postData).done(function(){
									window.location.href='/homework/student/unscored/0';
								});
								
							}


						}
					}
				);
		 
		 
		
	});
	
});
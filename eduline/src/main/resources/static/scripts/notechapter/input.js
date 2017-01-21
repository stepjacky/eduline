var head=[];
$(function() {
		
	$('.fileupload').fileupload({
		url : '/notechapter/upload/params',
		dataType : 'text',
		paramName : 'file',
		multipart : true,
		formData : function() {
			var data = [];
			data.push({
				name : 'type',
				value : this.fileInput[0].name
			});
			data.push({
				name : 'id',
				value : vid
			});
			if(this.fileInput[0].name=='anwserFile'){
				data.push({
					name : 'anwserNum',
					value: $('#numBig').val()
				});
			}
			data.push(csrfinfo.formData);
			return data;
		},
		acceptFileTypes : /(\.|\/)(pdf|ppt|pptx|doc|docx|mp3?)$/i,
		maxFileSize : 10000000,// 10m
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progress .progress-bar').css('width', progress + '%');
		},
		done : function(e, data) {
			
			bootbox.alert("大题答案已保存", 
					
				function(){ 
				
				var d = new Date();
			    $.get('/static/tmps/notechapter/chapter-file.html?_='+d.getTime())
			    .done(function(tmp){
			    	$.get('/notechapter/getinfo/'+vid)
			    	.done(function(tmpdata){
			    		
			    		$('#letcpt').html(tmpstr(tmp,tmpdata));
			    	})
			    	
			    });
			
			        }
			);
			
			
		}
	});
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

		$('#pageNum').html(pageNum);
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
		$('#pageNum').html(pageNum);
		queueRenderPage(pageNum);
	}

	
	$('.prevPage').on('click', onPrevPage);
	$('.nextPage').on('click', onNextPage);
	$('button.pdfView').on('click', function() {
		var frag = $(this).data('key');
		var url = "/notechapter/viewpdf/" + frag;
		var base = '/static/lib/pdfjs/web/viewer.html?file=';
		//window.open(base+url, '_blank');
		//window.focus();
		//$('#pdfcnt').load(base+url);
		PDFJS.getDocument(url).then(function(doc) {
			
			pdfDoc = doc;		
			pageNums = doc.numPages;
			$('#pageNum').html(pageNum);
	        $('#pageCount').html(pdfDoc.numPages);
			// Initial/first page rendering
			renderPage(pageNum);
		});
	});
	
	
	
	
	$('#headShow').on('click',function(){
		var num  = $('#numSelect').val();
		for(var j=0;j<num;j++){
			if(heads[j])
			  head[j]=heads[j];
			else
				head[j]='X';
		}
		var msg = tmpstr(selects,{len:num,head:head});	
		 
		bootbox.alert(msg, function(){ 
			saveHeads();			
		});
	
		
	});	
	
	$('.saveHeads').on('click',function(){
		saveHeads();
	});
	
	function saveHeads(){
		var asw = head.join('');
		$.get('/notechapter/update/heads/'+vid+'/'+(asw?asw:'X'))
		.done(function(){
			if(parentNode) refreshNode('cpt',parentNode);
		});
	}
	$('#refreshHead').on('click',function(){
		var url ='/notechapter/chapter/refresh/homework/'+vid;
		$.get(url).done(function(){
			bootbox.alert({
			    message: "相关作业标准答案已刷新,请注意",
			    size: 'small'
			});
		});
		
	})
});

function checkOption(i,opt){
	head[i] = opt;
}
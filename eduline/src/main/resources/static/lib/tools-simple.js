function postJson(url,data,cak){
	if(!url || !data) {
		console.error("param for ajax is not enough");
		return;
	}
	var param = JSON.stringify(data);
	if($.type(data)=='string'){
		param = data;
	}
	if($.isPlainObject(data)){
		param = JSON.stringify(data);
	}
    $.ajax({
            'url':url,
            'contentType': 'application/json',
            'data': param,
            'type':'POST',
            'dataType':'json',
            'success':function(rst){

               if($.isFunction(cak)){
               	cak(rst);
			   }
            }

        }
    )
}

function reloadPage() {
	location.reload();
}
function guid() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16)
				.substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4()
			+ s4() + s4();
}


function notify(text) {
   layer.msg(text);
}

function redirectTo(url) {
	window.location.href = url;
}

function loadUrl(url) {

	if(url && url.indexOf('javascript')<0 && url!='/' && url!='/logout' ){
        $('#contentbody').load(url);
    }

    if(url=='/'){
		reloadPage();
	}

	if(url=='/logout'){
    	location.href = url;
	}

}

$.ajaxPrefilter(function(options, originalOptions, jqXHR) {

	if (typeof csrfinfo != 'undefined' && csrfinfo && (options.type == 'post' || options.type=='POST' )) {
		// log(options.url);

		if (options.url.indexOf('?') == -1)
			options.url += '?' + csrfinfo.param();
		else
			options.url += '&' + csrfinfo.param();

	}

	// log(options);
});
var console;
var ajax = false;
(function() {
	var method;
	var noop = function() {
	};
	var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml',
			'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
			'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'table',
			'time', 'timeEnd', 'timeStamp', 'trace', 'warn' ];
	var length = methods.length;
	console = (window.console || {});
	while (length--) {
		method = methods[length];
		if (!console[method]) {
			console[method] = noop;
		}
	}

}());

function log(message) {
	console.log(message);
}

// system function
function doExtend(funcName) {
	try {
		if (typeof (eval(funcName)) == "function") {
			// alert("function is "+funcName);
			if (arguments.length == 1) {
				funcName();
			} else if (arguments.length == 2) {
				funcName(arguments[1]);
			}
		}

	} catch (e) {
		log(e.message);

	}
}

function doPost(link, postData, callback) {
	$("#contentbody").empty();
	$.ajax({
		type : "POST",
		url : link,
		cache : false,
		data : postData,
		scriptCharset : "utf-8",
		success : function(msg) {
			$("#contentbody").html(msg);
			doExtend(callback);

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			reloadPage();
		}
	});
}
function doSubmit(link, postData, callback) {
	$.ajax({
		type : "POST",
		url : link,
		cache : false,
		data : postData,
		scriptCharset : "utf-8",
		success : function(msg) {
			doExtend(callback);
		},
		error : function(msg) {
			$("#contentbody").html(msg);
		}
	});
}
function doAction(url, data, callback) {

	$.ajax({
		type : "GET",
		url : url,
		cache : false,
		data : data,
		scriptCharset : "utf-8",
		success : function(msg) {
			if (typeof callback == 'function') {
				callback.call(this, msg);
			}
		},
		error : function(msg) {
			log(msg);
		}
	});
}
function doGet(url, data, call) {
	$("#contentbody").empty();
	$.ajax({
		type : "GET",
		url : url,
		cache : false,
		data : data,
		scriptCharset : "utf-8",
		success : function(msg) {
			$("#contentbody").html(msg);

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			reloadPage();
		}
	});
}

function doAjaxNode(url, id) {
	$("#" + id).load(url);
}

function doForm(action, data) {
	var fid = "queryForm";
	var oform = document.getElementById(fid);
	if (!oform) {
		oform = document.createElement('form');
		oform.id = fid;
		oform.target = '_self';
		oform.method = "POST";
		oform.action = action;
		$(document.body).append(oform);
	}
	$(oform).empty();
	if (!$.isEmptyObject(data)) {
		for ( var key in data) {
			$(oform).append(newHiddenElement(key, data[key]));
		}

	} else {
		notify("没有任何查询参数,查询返回");
		// return;
	}
	if (csrfinfo) {
		$(oform).append(newHiddenElement(csrfinfo['name'], csrfinfo['token']));
	}
	oform.submit();
}

function isIE() {
	if (-[ 1, ]) {
		return false;
	} else {
		return true;
	}

}

function reloadPage() {
	window.location.href = window.location.href;
}
function guid() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16)
				.substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4()
			+ s4() + s4();
}
var confirmDlg, redirectDlg;
$(function() {
	$('[data-toggle="tooltip"]').tooltip();
	confirmDlg = $("#confirm_dialog");
	redirectDlg = $("#redirect_dialog");
	$(document.body).on('click', '.closeBlock', $.unblockUI);
});

function notify(text) {
	$.blockUI({
		message :"<span class='label label-primary'>系统消息</span><p>"+text+"</p>",
		css : {
			border : '1px solid #ccc',
			padding : '8px',
			backgroundColor : '#333',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : 1,
			color : '#fff'
		},
		onOverlayClick:$.unblockUI
		
	});
}

function redirectTo(url) {
	window.location.href = url;
}

function tellUser(text, jump) {
	
	window.location.href = window.location.href;

}

function tmpstr(tmp,data){
	var prestr = _.template(tmp);	
	var rst = prestr(data);	
    return	rst;
}
/**
 * 根据元素类型创建form元素 <input type="text" /> <input type="checkbox" /> <input
 * type="radio" /> <input type="image" /> <input type="file" /> <input
 * type="submit" /> <input type="reset" /> <input type="password" /> <input
 * type="button" /> <select><option/></select> <textarea></textarea> 返回的是jq对象
 */
function elementByType(type) {
	switch (type) {
	case "select":
		var e = $("<select></select>");
		return e;
	case "label":
		var e = $("<label></label>");
		return e;
	case "textarea":
		return $("<textarea></textarea>");
		;
	case "img":
		return $("<img></img>");
		;
	default:
		return $("<input type='" + type + "' />");
		;
	}
}

function newHiddenElement(name, value) {
	var ele = elementByType('hidden');
	ele.attr('name', name);
	ele.val(value);
	return ele;
}


$.ajaxSetup({
	beforeSend : function(xhr) {
		$.blockUI({
			message : '<i class="fa fa-cog fa-spin fa-2x"></i>',
			css : {
				border : 'none',
				padding : '15px',
				backgroundColor : '#000',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .5,
				color : '#fff'
			}
		});

	},
	start:function(){
		console.dir(arguments);
	},
	complete : function(xhr, ts) {
		
		if(!$.unblockUI){
			window.location.replace("/");
		}else{
			$.unblockUI();
		}
	}
});

$.ajaxPrefilter(function(options, originalOptions, jqXHR) {

	if (typeof csrfinfo != 'undefined' && csrfinfo && options.type == 'post') {
		// log(options.url);

		if (options.url.indexOf('?') == -1)
			options.url += '?' + csrfinfo.param();
		else
			options.url += '&' + csrfinfo.param();

		console.log(options.url);
	}

	// log(options);
});
(function(w) {
	var version = "0.0.1";
	var JackyJs = function() {
		return function() {
		};
	}
	if (!window.JackyJs) {
		window.JackyJs = JackyJs;
	}
	return JackyJs;
}(window));

/**
 * tool for use PDF.js
 * 
 */
(function(w, jacky) {
	function PDFTool() {
		
	}
	PDFTool.prototype = {
		constructor : PDFTool,		
		init : function(opts) {
			var base = '/static/lib/pdfjs/web/viewer.html?file=';
			$('#pdfcnt').load(base+opts.url);
		}
	}
	if (!jacky.createPDFTool) {
		jacky.createPDFTool = function() {
			return new PDFTool();
		}
	}

	return jacky;
}(window, JackyJs));
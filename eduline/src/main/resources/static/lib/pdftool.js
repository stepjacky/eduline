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
		this.url = null;
		this.selector = null;
		this.pdfoptions = {};
	}
	PDFTool.prototype = {
		constructor : PDFTool,
		init : function(opts) {
			if(!opts) opts = {};

			this.pdfoptions = opts.options || {};
			this.url = opts.url || null;
			this.selector = opts.selector || null;
		},
		render : function(page) {
			if(page)
			    this.pdfoptions.page = page;
			else
                this.pdfoptions.page = 1;
            PDFObject.embed(this.url, this.selector,this.pdfoptions);
		}
	}
	if (!jacky.createPDFTool) {
		jacky.createPDFTool = function() {
			return new PDFTool();
		}
	}

	return jacky;
}(window, JackyJs));
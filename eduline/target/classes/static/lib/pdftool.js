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
		this.pdfDoc = null;
		this.pageNum = 1;
		this.pageNums = 1;
		this.pageRendering = false;
		this.pageRotation = 0;
		this.pageNumPending = null;
		this.canvas = null;
		this.ctx = null;
		this.namespace = '';
	}
	PDFTool.prototype = {
		constructor : PDFTool,
		scale : 2.0,
		isValid : function(func) {
			return (func && $.isFunction(func));
		},
		selector : {
			prevPage : function(ns) {
				return '#' + ns + ' .prevPage';
			},
			nextPage : function(ns) {
				return '#' + ns + ' .nextPage';
			},
			pageNum : function(ns) {
				return '#' + ns + ' .pageNum';
			},
			pageCount : function(ns) {
				return '#' + ns + ' .pageCount';
			}
		},
		init : function(opts) {
			this.namespace = opts.namespace;
			this.scale = opts.scale || 2.0;
			this.canvas = document.getElementById(opts.namespace + '-'
					+ opts.pdfcanvas);
			this.ctx = this.canvas.getContext('2d');
			// console.dir(this.selector);
			console.log(this.selector.prevPage(this.namespace));
			$(this.selector.prevPage(this.namespace)).off('click',
					this.onPrevPage);
			$(this.selector.prevPage(this.namespace)).on('click',
					this.onPrevPage);
			$(this.selector.nextPage(this.namespace)).off('click',
					this.onNextPage);
			$(this.selector.nextPage(this.namespace)).on('click',
					this.onNextPage);
			var that = this;
			PDFJS.getDocument(opts.url).then(
					function(doc) {
						that.pdfDoc = doc;
						that.pageNums = doc.numPages;
						console.log(that.selector.pageNum(that.namespace));
						$(that.selector.pageNum(that.namespace)).html(
								that.pageNum);
						$(that.selector.pageCount(that.namespace)).html(
								that.pdfDoc.numPages);
						// Initial/first page rendering
						that.renderPage(that.pageNum);
					});
		},
		renderPage : function(num) {
			this.pageRendering = true;
			// Using promise to fetch the page
			var that = this;
			this.pdfDoc.getPage(num).then(function(page) {
				var viewport = page.getViewport(that.scale);
				that.canvas.height = viewport.height;
				that.canvas.width = viewport.width;

				// Render PDF page into canvas context
				var renderContext = {
					canvasContext : that.ctx,
					viewport : viewport
				};
				var renderTask = page.render(renderContext);

				// Wait for rendering to finish
				renderTask.promise.then(function() {
					that.pageRendering = false;
					if (that.pageNumPending !== null) {
						// New page rendering is pending
						that.renderPage(that.pageNumPending);
						that.pageNumPending = null;
					}
				});
			});

			$(that.selector.pageNum(that.namespace)).html(this.pageNum);
		},
		queueRenderPage : function(num) {
			if (this.pageRendering) {
				this.pageNumPending = num;
			} else {
				this.renderPage(num);
			}
		},
		onPrevPage : function() {
			if (this.pageNum <= 1) {
				return;
			}
			this.pageNum--;
			this.queueRenderPage(this.pageNum);
		},
		onNextPage : function() {
			if (this.pageNum >= this.pageNums) {
				return;
			}
			this.pageNum++;
			this.queueRenderPage(this.pageNum);
		},
		rotatePages : function(delta) {
			var pageNumber = this.pageNum;
			this.pageRotation = (this.pageRotation + 360 + delta) % 360;
			this.pdfViewer.pagesRotation = this.pageRotation;
			this.pdfThumbnailViewer.pagesRotation = this.pageRotation;

			this.forceRendering();

			this.pdfViewer.scrollPageIntoView(pageNumber);
		},
		cleanup : function() {
			if (!this.pdfDoc) {
				return; // run cleanup when document is loaded
			}
			this.pdfViewer.cleanup();
			this.pdfThumbnailViewer.cleanup();
			this.pdfDoc.cleanup();
		},

		forceRendering : function() {
			this.pdfRenderingQueue.printing = this.printing;
			this.pdfRenderingQueue.isThumbnailViewEnabled = this.pdfSidebar.isThumbnailViewVisible;
			this.pdfRenderingQueue.renderHighestPriority();
		},

	}
	if (!jacky.createPDFTool) {
		jacky.createPDFTool = function() {
			return new PDFTool();
		}
	}

	return jacky;
}(window, JackyJs));
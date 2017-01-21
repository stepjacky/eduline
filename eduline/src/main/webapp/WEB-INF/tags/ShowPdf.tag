<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="url" %>
<%@ attribute name="namespace" %>
<div class="container-fluid" id="${namespace}">
	<div class="row">
		<div class="col-md-12">
			<div class="center-block" style="text-align: center">
				<button type="button" class="btn btn-xs btn-info prevPage">
					<i class="fa fa-chevron-left" aria-hidden="true"></i>
				</button>
				<button type="button" class="btn btn-xs btn-info nextPage">
					<i class="fa fa-chevron-right" aria-hidden="true"></i>
				</button>
				<span class="badge pageNum"></span>/<span class="badge pageCount"></span>
			</div>
			<canvas id="${namespace}-pdfcanvas" style="width: 100%; height: 100%;"></canvas>
			<div id="pdfcnt"></div>
		</div>
	</div>
</div>

<script>
	$(function() {
		var pdfjs = JackyJs.createPDFTool();
		pdfjs.init({
			namespace:'${namespace}',
			pdfcanvas:'pdfcanvas',
			url:'${url}'
		});
	});
</script>
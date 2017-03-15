<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="url" %>
<%@ attribute name="namespace" %>
<div class="container-fluid" id="${namespace}">
	<div class="row">
		<div class="col-md-12" id="pdfcontent">

		</div>
	</div>
</div>

<script>
	$(function() {
		var pdfjs = JackyJs.createPDFTool();
		pdfjs.init({
			selector:'#pdfcontent',
			url:'${url}',
			options:{
                height: "550px",
                width:'100%',
                pdfOpenParams: {
                    view: 'Fit',
                    pagemode: 'thumbs',
                    navpanes: 1,
                    toobar: 1
                }
			}
		});
		pdfjs.render(1);
	});
</script>
<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>


<div class="container-fluid">
  <div class="row">
    <div class="col-md-4" style="min-height:300px">
       <ul id="cpt" class="ztree"></ul>
    </div>
    <div class="col-md-8" id="letcpt">
        
    </div>
  </div>
  <div class="row">
    <div class="col-md-12" id="pdfcontent">
    <div class="center-block" style="text-align:center">
      <button type="button" class="btn btn-xs btn-info prevPage"><i class="fa fa-chevron-left" aria-hidden="true"></i></button>
      <button type="button" class="btn btn-xs btn-info nextPage"><i class="fa fa-chevron-right" aria-hidden="true"></i></button>
      <span class="badge" id="pageNum"></span>
      <span class="badge" id="pageCount"></span>
    </div>
       <canvas id="pdfcanvas" style="width:100%;height:100%;"></canvas> 
    </div>
    <div id="pdfcnt"></div>
  </div>
</div> 
<script>
  var nid = '${noteId}';
  var name='${name}';
  var dtype='${dtype}'||false;
</script>  

  

<script src="/static/scripts/lecturenotes/expand.js"></script>
<%@ include file="../pageFoot.jsp"%>
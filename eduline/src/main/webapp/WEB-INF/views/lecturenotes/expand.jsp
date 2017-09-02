<%@ include file="../pageHead-new.jsp"%>
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
    <div id="pdfcontent" class="col-md-12" style="width:100%; min-height: 600px;">


    </div>
  </div>
</div> 
<script>
  var nid = '${noteId}';
  var name='${name}';
  var dtype='${dtype}'||false;
</script>  

  

<script src="/static/scripts/lecturenotes/expand.js"></script>
<%@ include file="../pageFoot.jsp"%>
<%@include file="../pageHead-new.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="/static/lib/pdfjs/build/pdf.js"></script>
<link href='/static/css/homework/input.css' rel="stylesheet" />
<table class="table table-bordered table-hover" id="answerCard">
<caption><button type="button" class="close unblock" aria-label="Close"><span aria-hidden="true">&times;</span></button></caption>
<tbody>
<c:forEach items="${modelAnswers}" var="item" varStatus="vs">
  <c:if test="${vs.index%4==0}">
     <tr>
  </c:if>
  <td><div class="serial">${vs.index+1}</div></td>
  <td class="answer-${vs.index+1}-td answer-td" align="left">
   
   <label class="btn-label"><input type="radio" class="answer"  data-key="${vs.index}" name="na${item}me${vs.index}" value="A">
    [A]</label>
    <label class="btn-label"><input type="radio" class="answer"  data-key="${vs.index}" name="na${item}me${vs.index}" value="B">
    [B]</label>
    <label class="btn-label"><input type="radio" class="answer"  data-key="${vs.index}" name="na${item}me${vs.index}" value="C">
    [C]</label>    
    <label class="btn-label"><input type="radio" class="answer"  data-key="${vs.index}" name="na${item}me${vs.index}" value="D">
    [D]</label>
      
  </td>
  <c:if test="${(vs.index+1)%4==0}">
     </tr>
  </c:if>

</c:forEach> 
  </tbody> 
</table>

<div class="container-fluid">
 <div class="row">
    <div class="col-md-12" id="pdfcontent">

    </div>
  </div>
</div> 
<input type="file" id="fileupload" name="answerDoc">
<div class="docName">
  <img src="" style="width:400px;height:300px">
</div>
<button type="button" class="btn btn-primary btn-lg btn-block submitHomework"><i class="fa fa-hand-o-up" aria-hidden="true"></i>交卷</button>
<script type="text/javascript">
   var url="${url}";
   var hwid='${hwid}';
   var len = ${fn:length(modelAnswers)};
</script>
<script src="static/scripts/homework/dohomework.js" ></script>
<%@include file="../pageFoot.jsp"%>
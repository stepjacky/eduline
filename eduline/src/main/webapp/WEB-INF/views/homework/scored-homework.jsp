<%@include file="../pageHead-new.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="edu" %>
<div class="alert alert-info">
<span class="label label-info">选择答案</span>
<p>
标准答案:${bean.modelAnswer}<br>
你的答案:${bean.answer}<br>

</p>
<p>
 大题答案:
<c:forTokens items="${bean.answerResult}" delims="," var="r" varStatus="vs">
  <p><span class="label label-info">大题 ${vs.index+1}</span>
   <label><input type="radio" name="answer-${vs.index+1}"  ${r==1?'checked':'' }    value="1">全对 </label>
    <label><input type="radio" name="answer-${vs.index+1}" ${r==0?'checked':'' }    value="0">半对 </label>
   <label> <input type="radio" name="answer-${vs.index+1}" ${r==-1?'checked':'' }   value="-1">全错 </label>
    </p>
</c:forTokens>
</p>

总得分:${bean.score}
<p>
<p>
 <span class="label label-info">批阅点评</span>
 ${bean.note}
</p>
</div>

<span class="label label-info">大题解答</span>
<edu:ShowPdf url="${url}" namespace="answerDoc"></edu:ShowPdf>
<span class="label label-info" >标准答案</span>
<edu:ShowPdf url="${modelUrl}" namespace="modelAnswer"></edu:ShowPdf>

<script src="static/scripts/homework/doscorehomework.js" ></script>
<%@include file="../pageFoot.jsp"%>
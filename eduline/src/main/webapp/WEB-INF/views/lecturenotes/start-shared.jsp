<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageHead.jsp" %>
<div class="alert alert-info">
  你即将<i class="fa fa-share-alt-square" aria-hidden="true"></i>分享 讲义${name}
</div>
<div class="alert alert-info" id="users"></div>

<button type="button" class="btn btn-primary doShared">
 <i class="fa fa-share-alt-square" aria-hidden="true"></i>分享
</button>
<script>
 var noteId = '${noteId}';
</script>
<script src="static/scripts/lecturenotes/shared.js"></script>
<%@ include file="../pageFoot.jsp" %>
<%--
  Created by Jackysoft.Inc on 2017/7/22 0022 22:15.
  User: qujiakang@126.com  
  Date: 2017/7/22 0022 22:15  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead-simple.jsp" %>
<div class="alert alert-info">
    教学资源管理
</div>
<div class="alert alert-success">
    <ol class="breadcrumb">
        <li>公开形式</li>
        <c:forEach items="${commonTypes}" var="item">
           <li class="${commonType==item.key?'active':''}">
               <a href="javascript:;">${item.name}</a></li>
        </c:forEach>


    </ol>
    <ol class="breadcrumb">
        <li>内容形式</li>
        <c:forEach items="${styleTypes}" var="item">
            <li class="${styleType==item.key?'active':''}">
                <a href="javascript:;">${item.name}</a></li>
        </c:forEach>


    </ol>
    <ol class="breadcrumb">
        <li>文件形式</li>
        <c:forEach items="${fileTypes}" var="item">
            <li class="${fileType==item.key?'active':''}">
                <a href="javascript:;">${item.name}</a></li>
        </c:forEach>


    </ol>

</div>
<div class="row">
    <div class="col-md-4" id="leftside">
        <select class="form-control action-textbook-change"   v-model="textbook" >
            <c:forEach items="${books}" var="item">
                <option ${book.id==item.id?'selected':''}  value="${item.id}">
                        ${item.grade.name}-${item.course.name}-${item.name}</option>
            </c:forEach>
        </select>

        <c:forEach items="${chapters}" var="item">
            <div class="panel panel-info panel-item">

                <div class="panel-heading">${item.name}</div>
                <ul class="list-group">
                    <c:forEach items="${item.children}" var="child">
                        <li class="list-group-item">
                            <a href="javascript:;" class="action-checked" data-id="${child.id}">${child.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </c:forEach>


    </div><!-- left list group of chapter -->
    <div class="col-md-8">
    </div>
</div>
<script src="${sbase}static/scripts/resource/list.js"></script>
<%@ include file="../pageFoot-simple.jsp" %>

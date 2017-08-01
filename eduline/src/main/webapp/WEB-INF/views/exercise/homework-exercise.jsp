<%--
  Created by Jackysoft.Inc on 2017/7/24 0024 11:18.
  User: qujiakang@126.com  
  Date: 2017/7/24 0024 11:18  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead.jsp" %>
<link rel="stylesheet" href="${sbase}static/css/exercise/list.css" />
<div class="alert alert-info">
    选择习题

</div>

<div class="alert alert-info">
    <ol class="breadcrumb">
        <li>公开形式</li>
        <c:forEach items="${commontypes}" var="item">
            <li>
                <a href="/exercise/homework-exercise?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${item.key}" >
                    <span class="${commontype==item.key?'label label-info':''}">${item.name}</span>
                </a>
            </li>
        </c:forEach>


    </ol>
</div>

<div class="row">
    <div class="col-md-3" id="leftside">
        <select class="form-control action-textbook-change"   v-model="textbook" >
            <c:forEach items="${books}" var="item">
                <option ${textbook==item.id?'selected':''}
                        data-url="/exercise/homework-exercise?textbook=${item.id}&chapter=${chapter}&page=${page}&commontype=${commontype}"
                        value="${item.id}">
                        ${item.grade.name}-${item.course.name}-${item.name}</option>
            </c:forEach>
        </select>

        <c:forEach items="${chapters}" var="item">
            <div class="panel panel-primary panel-item">

                <div class="panel-heading">${item.name}</div>
                <ul class="list-group">
                    <c:forEach items="${item.children}" var="child">
                        <li class="list-group-item">
                            <a href="/exercise/homework-exercise?textbook=${textbook}&chapter=${child.id}&page=${page}&commontype=${commontype}" >
                                <span class="${chapter==child.id?'label label-info':''}">${child.name}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </c:forEach>


    </div><!-- left list group of chapter -->
    <div class="col-md-9">
        <div class="pull-right">

            <a href="/homework/select-groups?course=${book.course.value}" class="btn btn-info action-next-step">
                下一步
                <i class="fa fa-arrow-right "></i>
            </a>
        </div>

        <table class="${tableClass}" id="tlabe-exercises">
            <thead>
            <tr>
                <th>选择</th>
                <th>名称</th>
                <th>选择题</th>
                <th>解答题</th>
                <th>大小</th>
                <th>所有者</th>
                <th>上传日期</th>

            </tr>

            </thead>
            <tbody>
            <c:forEach items="${pager.dataList}" var="item">
                <tr class="btn-gp">
                    <td>
                        <input type="radio" v-model="exercise" value="${item.id}"  />
                    </td>
                    <td>

                     ${item.name}

                    </td>
                    <td>${item.csize}</td>
                    <td>${item.esize}</td>
                    <td>${jxf:datasize(item.size)}</td>
                    <td>${item.owner.name}</td>
                    <td>${jxf:dateFormat(item.modifyDate)}</td>

                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="6">
                    ${pager.pagination}
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<script>
    var exercise = '${exercise}'.split(',');
</script>
<script src="${sbase}static/scripts/exercise/homework.js"></script>


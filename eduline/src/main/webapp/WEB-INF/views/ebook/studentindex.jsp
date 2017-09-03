<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="static/css/ebook/pager.css" rel="stylesheet">
<p>

<ol class="breadcrumb-ext">
    <li><a class="active action-href-nav" href="/ebook/student/0?ajax=false">
  <span class="label label-info">
       回到主页
       </span>
    </a></li>
    <c:forEach items="${tags}" var="tag">
        <li>
            <a href="/ebook/with/tags/0/10?tags=${tag}" class="action-href-nav">
                <span class="label label-default">
                        <c:out value="${tag}"/></span>
            </a>
        </li>
    </c:forEach>
</ol>

</p>

<div class="table-responsive">
    <table
            class="table "
            style="table-layout:fixed"
    >
        <tbody>
        <c:forEach items="${pager.dataList}" var="item" varStatus="vs">
            <c:if test="${vs.index%4==0}">
                <tr>
            </c:if>

            <td align="center">
                <p>
                    <a href="/ebook/edit/${item.id}" class="action-href-nav">
                        <img class="img-thumbnail"
                             src="/mycover/large/${(fn:endsWith(item.coverPath,'.htm') || fn:endsWith(item.coverPath,'.html'))?'404.jpg':item.coverPath}"
                             style="width:80px;height:100px">
                    </a>
                </p>

                <p>
                    <c:forTokens items="${item.tags}" delims="," var="tag">

                        <a class="label label-info action-href-nav " href="/ebook/with/tags/0/10?tags=${tag}">
                                ${tag}
                        </a>

                    </c:forTokens>
                </p>
                <p style="width:150px;word-wrap:break-word;">
                    <a href="/ebook/edit/${item.id}">
                            ${fn:substringBefore(item.name, ".")}
                    </a>
                </p>
                <p>
                    下载<span class="badge">${item.clicknum}</span>次
                </p>
            </td>

            <c:if test="${(vs.index+1)%4==0}">
                </tr>
            </c:if>


        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">${pager.pagination}</td>
        </tr>
        </tfoot>
    </table>
</div>
<script src="static/scripts/ebook/pager.js"></script>

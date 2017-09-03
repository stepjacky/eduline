<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<link rel="stylesheet" href="${sbase}static/css/resource/list.css">
<p>
<span class="list-types">
    <a href="javascript:;">公开形式</a>
    <c:forEach items="${commontypes}" var="item">
           <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${item.key}&styletype=${styletype}&filetype=${filetype}">
               <span class="layui-badge-rim layui-bg-${commontype==item.key?'cyan':'blue'}">${item.name}</span>
           </a>

    </c:forEach>
</span>
</p>
<p>
<span class="list-types">
      <a href="javascript:;">所属类型</a>
    <c:forEach items="${styletypes}" var="item">
              <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${item.key}&filetype=${filetype}">
                  <span class="layui-badge-rim layui-bg-${styletype==item.key?'cyan':'blue'}">${item.name}</span>
              </a>
    </c:forEach>
</span>
</p>

<p>
<span class="list-types">
     <a href="javascript:;">文件类型</a>

                <c:forEach items="${filetypes}" var="item">

                        <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${item.key}">
                              <span class="layui-badge-rim layui-bg-${filetype==item.key?'cyan':'blue'}">${item.name}</span>
                        </a>

                </c:forEach>

</span>
</p>

<div class="layui-col-xs3 layui-col-sm3 layui-col-md3" id="leftside">
    <select class="action-textbook-change" v-model="textbook">
        <c:forEach items="${books}" var="item">
            <option ${textbook==item.id?'selected':''}
                    data-url="/resource/listresource?textbook=${item.id}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${filetype}"
                    value="${item.id}">
                    ${item.grade.name}-${item.course.name}-${item.name}</option>
        </c:forEach>
    </select>

    <ul class="layui-nav layui-nav-tree layui-nav-tree-inner layui-bg-chapter">
        <c:forEach items="${chapters}" var="item">

            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">${item.name}</a>
                <dl class="layui-nav-child">
                    <c:forEach items="${item.children}" var="child">
                        <dd class="${chapter==child.id?'layui-bg-chapter-selected':''}">
                            <a href="/resource/listresource?textbook=${textbook}&chapter=${child.id}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${filetype}">
                                    ${child.name}
                            </a>
                        </dd>
                    </c:forEach>

                </dl>
            </li>
        </c:forEach>
    </ul>


</div>

<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
    <div class="btn-group">
        <button class="btn btn-info action-start-upload"
                data-textbook="${textbook}"
                data-chapter="${chapter}"
                data-commontype="${commontype}"
                data-styletype="${styletype}"
                data-filetype="${filetype}"
                data-course="${book.course.value}"
                data-grade="${book.grade.value}"
                type="button">
            <i class="fa fa-upload "></i>上传资源
        </button>

    </div>
    <table class="${tableClass}">
        <thead>
        <tr>
            <th>名称</th>
            <th>大小</th>
            <th>所有者</th>
            <th>上传日期</th>
            <th>管理</th>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${pager.dataList}" var="item">
            <tr class="btn-gp">
                <td>
                    <a href="/resource/download/${item.id}" target="_blank">
                        <i class="fa fa-download"></i>${item.name}
                    </a>
                </td>
                <td>${jxf:datasize(item.size)}</td>
                <td>${item.owner.name}</td>
                <td>${jxf:dateFormat(item.modifyDate)}</td>
                <td>
                    <c:if test="${item.owner.value==sysUser.username}">
                        <a href="javascript:;" class="btn btn-danger btn-xs action-remove"
                           data-id="${item.id}"
                        >
                            <i class="fa fa-remove"></i>
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="5">
                ${pager.pagination}
            </td>
        </tr>
        </tfoot>
    </table>
</div>
<script src="${sbase}static/scripts/resource/list.js"></script>


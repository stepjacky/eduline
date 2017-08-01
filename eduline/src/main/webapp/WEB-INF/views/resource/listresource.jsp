<%--
  Created by Jackysoft.Inc on 2017/7/22 0022 22:15.
  User: qujiakang@126.com  
  Date: 2017/7/22 0022 22:15  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead.jsp" %>
<link rel="stylesheet" href="${sbase}static/css/resource/list.css" />
<div class="alert alert-info">
    教学资源管理
</div>
<div class="alert alert-info">
    <ol class="breadcrumb">
        <li>公开形式</li>
        <c:forEach items="${commontypes}" var="item">
           <li>
               <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${item.key}&styletype=${styletype}&filetype=${filetype}" >
                    <span class="${commontype==item.key?'label label-primary':''}">${item.name}</span>
               </a>
           </li>
        </c:forEach>


    </ol>
    <ol class="breadcrumb">
        <li>内容形式</li>
        <c:forEach items="${styletypes}" var="item">
            <li>
                <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${item.key}&filetype=${filetype}" >
                    <span class="${styletype==item.key?'label label-primary':''}">${item.name}</span>
                </a></li>
        </c:forEach>


    </ol>
    <ol class="breadcrumb">
        <li>文件形式</li>
        <c:forEach items="${filetypes}" var="item">
            <li>
                <a href="/resource/listresource?textbook=${textbook}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${item.key}">
                    <span class="${filetype==item.key?'label label-primary':''}">${item.name}</span>
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
                        data-url="/resource/listresource?textbook=${item.id}&chapter=${chapter}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${filetype}"
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
                            <a href="/resource/listresource?textbook=${textbook}&chapter=${child.id}&page=${page}&commontype=${commontype}&styletype=${styletype}&filetype=${filetype}" >
                                <span class="${chapter==child.id?'label label-primary':''}">${child.name}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </c:forEach>


    </div><!-- left list group of chapter -->
    <div class="col-md-9">
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
                       <td >
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
</div>
<script src="${sbase}static/scripts/resource/list.js"></script>
<%@ include file="../pageFoot.jsp" %>

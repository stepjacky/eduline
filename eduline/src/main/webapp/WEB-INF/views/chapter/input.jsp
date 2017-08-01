<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<%@ include file="../pageHead.jsp" %>
<link rel="stylesheet" href="${sbase}static/css/textbook/list.css">
<div class="alert alert-info">
   <a class="btn btn-xs btn-info " href="/textbook/pager/0?ajax=false">
       <i class="fa fa-list"></i>

   </a> ${book.name}

        章节添加,年级,${book.grade.name},课程,${book.course.name}
</div>
<div class="alert alert-success">

    <ul class="breadcrumb">
        <li>点击标题</li>
        <li>修改</li>
        <li><i class="fa fa-plus"></i>添加</li>
        <li><i class="fa fa-address-book"></i>添加二级章节</li>
        <li><i class="fa fa-times-circle"></i>删除</li>

    </ul>
</div>
<div class="row">
     <div class="col-md-4">

        <c:forEach items="${roots}" var="item">
            <div class="panel panel-info panel-item">

                <div class="panel-heading">

                        <a href="javascript:;" class="action-edit" data-id="${item.id}">
                                ${item.name}
                        </a>
                        <button class="btn btn-danger btn-xs action-new" data-id="${item.id}">
                            <i class="fa fa-plus"></i>

                        </button>
                            <button class="btn btn-danger btn-xs action-new-child" data-id="${item.id}">
                               <i class="fa fa-address-book"></i>

                            </button>
                            <button class="btn btn-danger btn-xs action-remove" data-id="${item.id}">
                                <i class="fa fa-times-circle"></i>

                            </button>
                </div>
                <ul class="list-group">
                    <c:forEach items="${item.children}" var="child">
                        <li class="list-group-item">
                            <a href="javascript:;" class="action-edit" data-id="${child.id}">${child.name}</a>
                            <button class="btn btn-danger btn-xs action-remove" data-id="${child.id}">
                                <i class="fa fa-times-circle"></i>

                            </button>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </c:forEach>


      </div><!-- left list group of chapter -->
      <div class="col-md-8">
          <div class="panel panel-info" id="input-body">
              <div class="panel-heading">添加{{ parent=='root'?'顶级':'子' }}章节</div>
              <div class="panel-body">
                  <div class="form-group">
                      <label >名称</label>
                      <input type="text" class="form-control" v-model="name" placeholder="请填写课本名称" >

                  </div>
                  <div class="form-group">
                      <label class="radio-inline">
                          <input type="radio" name="inlineRadioOptions" v-model="parent" value="root">
                          顶级
                      </label>
                      <label class="radio-inline">
                          <input type="radio" name="inlineRadioOptions" v-model="parent" value="">
                          子章节
                      </label>
                  </div>

                  <button type="button" class="btn btn-info action-save">
                      <i class="fa fa-save"></i>
                      保存</button>

              </div>
          </div>
      </div>
</div>

<script>
    var odata = {
        'parent':'root',
        'textbook':'${book.id}',
        'course':'${book.course.value}',
        'grade':'${book.grade.value}',
        'name':''
    };

</script>
<script src="static/scripts/chapter/input.js"></script>
<%@ include file="../pageFoot.jsp" %>

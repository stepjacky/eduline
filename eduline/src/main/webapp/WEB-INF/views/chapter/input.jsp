<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>

<%@ include file="../pageHead-simple.jsp" %>

<div class="alert alert-info">
    ${book.name}章节添加,年级,${book.grade.name},课程,${book.course.name}
</div>
<div class="row">
     <div class="col-md-4">
        <c:forEach items="${roots}" var="item">
            <div class="panel panel-info panel-item">

                <div class="panel-heading">



                        <a href="javascript:;" class="action-edit" data-id="${item.id}">
                                ${item.name}
                        </a>
                        <a href="javascript:;" class="action-new" data-id="${item.id}">
                            <i class="fa fa-plus"></i>

                        </a>
                            <a href="javascript:;" class="action-new-child" data-id="${item.id}">
                               <i class="fa fa-address-book"></i>

                            </a>
                            <a href="javascript:;" class="action-remove" data-id="${item.id}">
                                <i class="fa fa-times-circle"></i>

                            </a>
                </div>
                <ul class="list-group">
                    <c:forEach items="${item.children}" var="child">
                        <li class="list-group-item">
                            <a href="javascript:;" class="action-edit" data-id="${child.id}">${child.name}</a>
                            <a href="javascript:;" class="action-remove" data-id="${child.id}">
                                <i class="fa fa-times-circle"></i>

                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </c:forEach>


      </div><!-- left list group of chapter -->
      <div class="col-md-8">
          <div class="panel panel-info" id="input-body">
              <div class="panel-heading">添加<span class="label label-info">{{ parent=='root'?'顶级':'子' }}</span>章节</div>
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
<%@ include file="../pageFoot-simple.jsp" %>

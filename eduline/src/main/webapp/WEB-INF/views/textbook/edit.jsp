<%--
  Created by Jackysoft.Inc on 2017/7/18 0018 23:15.
  User: qujiakang@126.com  
  Date: 2017/7/18 0018 23:15  
--%>
<%@ page  pageEncoding="UTF-8" %>
<%@ include file="../pageHead-new.jsp" %>
<div class="panel panel-primary">
    <div class="panel-heading">编辑课本</div>
    <div class="panel-body" id="input-item">


          <div class="form-group">
              <label class=" control-label">名称 </label>
          <input type="text" class="form-control" v-model="name" placeholder="课本名称">

          </div>
        <div class="form-group">
            <label class=" control-label">年级</label>
              ${bean.grade.name}
              <select v-model="grade"  class="form-control" >
              <option disabled value="">请选择年级</option>
              <c:forEach items="${grades}" var="grade">
                  <option
                          v-bind:value="{'name':'${grade.name}','value':'${grade.id}'}"
                          ${bean.grade.value==grade.id? 'selected':''}

                  >${grade.name}</option>
              </c:forEach>
           </select>
        </div>
        <div class="form-group">
            <label class="control-label">课程</label>
            ${bean.course.name}
            <select v-model="course"  class="form-control" >
                <option disabled value="">请选择课程</option>
                <c:forEach items="${courses}" var="course">
                    <option
                            v-bind:value="{'name':'${course.name}','value':'${course.id}'}"
                        ${bean.course.value==course.id? 'selected':''}
                    >${course.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">


                <button class="btn btn-info action-save" type="button">
                    <i class="icon-save"></i>保存
                </button>

        </div>
    </div>
</div>
<script>
    var selected = ${jxf:toJson(bean)};
</script>
<script src="static/scripts/textbook/edit.js"></script>
<%@ include file="../pageFoot.jsp" %>

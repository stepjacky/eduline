<%--
  Created by Jackysoft.Inc on 2017/7/25 0025 9:23.
  User: qujiakang@126.com  
  Date: 2017/7/25 0025 9:23  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


<div id="input-body">
    <c:if test="${empty exercises}">
        <textarea v-model="content" class="form-control" rows="3" placeholder="填写作业内容"></textarea>
    </c:if>

    <div class="alert alert-info">
        第一步:选择班级
    </div>

    <table class="table table-condensed table-groups">
        <tbody>
        <c:forEach items="${groups}" var="item" varStatus="vs">
            <c:if test="${vs.index%3==0}">
                <tr>
            </c:if>
            <td>
                <label>
                    <input type="checkbox" v-model="groups" value="${item.id}">${item.name}
                </label>
            </td>

            <c:if test="${(vs.index+1)%3==0}">
                </tr>
            </c:if>


        </c:forEach>
        </tbody>
    </table>
    <div class="alert alert-info">
        第二步:选择发布时间

    </div>


    <div class="input-group date" style="width:30%">
        <input type="text" class="datepicker form-control" placeholder="默认今日" readonly/>
        <div class="input-group-addon">
            <i class="fa fa-calendar"></i>
        </div>
    </div>

    <div class="alert alert-success">
        <button type="button" class="btn btn-info btn-lg action-put-homework">
            <i class="fa fa-save"></i>发布
        </button>
    </div>
</div>
<script>

    $(function () {

        var odata = {
            'exercise': '${exercise}',
            'course': '${course}',
            'content': '',
            'startdate': 0,
            'groups': []
        }

        var vm = new Vue({
            'el': '#input-body',
            'data': odata
        })


        $('.datepicker').datepicker()
            .on('changeDate', function (e) {
                odata['startdate'] = e.date.getTime();
            });

        $('.action-put-homework').on('click', function () {
            var param = odata;
            if (odata['groups'].length == 0) {
                layer.alert('请选择班级');
                return;
            }
            param['groups'] = odata['groups'].join(',');
            var url = '/homework/put-homework';
            $.post(url, param).done(function (rst) {
                loadUrl('/homework/teacher/timeline')
            })
        })
    });
</script>

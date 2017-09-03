<%--
  Created by Jackysoft.Inc on 2017/7/25 0025 15:51.
  User: qujiakang@126.com  
  Date: 2017/7/25 0025 15:51  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<ul class="layui-timeline">
    <c:forEach items="${list}" var="item">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">
                        ${jxf:relativetime(item.startdate)}
                    <i class="glyphicon glyphicon-time"></i>
                        ${jxf:dateFormat(item.startdate)}
                </h3>
                <p>
                <div class="layui-collapse timeline-panel" data-homework="${item.id}">
                    <div class="layui-colla-item">
                        <h4 class="layui-colla-title">${item.name}</h4>
                        <div class="layui-colla-content layui-show">
                            <div class="layui-progress layui-bg-orange">
                                <div class="layui-progress-bar"
                                     lay-percent="${item.amountsubmited*100/(item.amount==0?1:item.amount)}%"></div>
                            </div>
                                ${item.amountsubmited}/${item.amount} 已交作业
                                ${jxf:dateFormat(item.startdate)}

                        </div>
                    </div>
                </div>
                </p>
            </div>
        </li>
    </c:forEach>
</ul>
<script>
    $(function () {
        $('.timeline-panel').on('click', function () {
            loadUrl('/homework/teacher-summary?homework=' + $(this).data('homework'));
        })
    })
</script>


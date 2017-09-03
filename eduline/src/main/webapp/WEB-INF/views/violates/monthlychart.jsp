<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<ol class="breadcrumb">
    <li>年份</li>
    <c:forEach var="year" begin="2014" end="2016">
        <c:choose>
            <c:when test="${year==eduyear}">
                <li class="active"><span class="badge">${year}年</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="/violates/chart/monthly/${year}/${edumonth}">${year}年</a></li>
            </c:otherwise>
        </c:choose>

    </c:forEach>

</ol>
<ol class="breadcrumb">
    <li>月份</li>
    <c:forEach var="m" begin="1" end="12">
        <c:choose>
            <c:when test="${m==edumonth}">
                <li class="active"><span class="badge">${m}月</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="/violates/chart/monthly/${eduyear}/${m}">${m}月</a></li>
            </c:otherwise>
        </c:choose>

    </c:forEach>

</ol>
<div id="charcontainer" style="width: 100%; height: 500px"></div>
<script>
    $(function () {
        $(function () {
            $('#charcontainer').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '${eduyear}年${edumonth}月各年级奖惩情况'
                },
                xAxis: {
                    categories: ['${labels}']
                },
                credits: {
                    enabled: false
                },
                series: [{
                    name: '奖励',
                    data: [${uppoints}]
                }, {
                    name: '处罚',
                    data: [${downpoints}]
                }]
            });
        });
    })
</script>

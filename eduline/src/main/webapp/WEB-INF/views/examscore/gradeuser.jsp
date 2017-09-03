<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<c:set var="info" value="${ examinfo.scoreInfo }"></c:set>
<link href="static/css/sysuser/query.css" rel="stylesheet">

<div class="table-responsive">


    <table
            class="table table-striped table-bordered table-hover table-condensed">
        <caption>
            <c:if test="${examinfo.prev!=-1}">
                <a
                        href="/examscore/gradeuser?inyear=${info.inyear}&page=${examinfo.prev}"
                        class="btn btn-success btn-sm action-score-nav" target="_self">下一次考试</a>
            </c:if>
            <c:if test="${examinfo.scoreInfo!=null}">
                <a
                        href="/examscore/gradeuser?inyear=${info.inyear}&page=${examinfo.page}"
                        class="btn btn-default  btn-sm action-score-nav" target="_self">
                        ${info.inyear}届${info.gradeName}${info.semester==0?"上":"下"}学期 <code>
                        ${info.monthlyName}</code> 成绩
                </a>
            </c:if>
            <c:if test="${examinfo.next!=-1}">
                <a
                        href="/examscore/gradeuser?inyear=${info.inyear}&page=${examinfo.next}"
                        class="btn btn-success btn-sm action-score-nav" target="_self">上一次考试</a>
            </c:if>
            <span>${examinfo.page+1}/${examinfo.counts}次考试</span>
            <a
                    class="btn btn-success btn-sm  action-score-nav"
                    href="/examscore/series/avgtotal/${info.inyear}/${info.grade}/${info.semester}/${info.monthly}">总分分布图表</a>
            <a
                    class="btn btn-primary btn-sm "
                    href="/examscore/gradeuser/excel/${info.inyear}/${examinfo.page}">下载</a>
        </caption>

        <thead>
        <tr>
            <th>序号</th>
            <th>学生</th>
            <th>排名</th>
            <th>总分</th>
            <th>总均分</th>

            <th>${courseHead}</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach items="${examinfo.scoreDetails }" var="item"
                   varStatus="vs">

            <tr>
                <td>${vs.index+1}</td>
                <td><a class="btn btn-success btn-xs  action-score-nav" type="button"
                       href="/examscore/student/score/${item.student}/0"
                       target="gradeuser"> ${item.studentName}</a></td>
                <td><span class="label label-info"> ${item.totalSorted}
                </span></td>

                <td>${jxf:formatFloat(item.totalScore,1)}</td>
                <td>${jxf:formatFloat(item.avgScore,1)}</td>

                <td>${item.scoreHtml}</td>

            </tr>

        </c:forEach>


        </tbody>
    </table>
</div>
<script>
    $(function () {
       $('.action-score-nav').on('click',function (event) {
           event.preventDefault();

           loadUrl($(this).attr('href'));

           return false;
       })
    })
</script>

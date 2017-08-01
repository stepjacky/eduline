<%--
  Created by Jackysoft.Inc on 2017/7/25 0025 15:51.
  User: qujiakang@126.com  
  Date: 2017/7/25 0025 15:51  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead.jsp" %>
<link rel="stylesheet" href="${sbase}static/css/homework/timeline.css">
   <div class="page-header">
        <h1>作业</h1>
    </div>
    <div id="timeline">

        <c:forEach items="${list}" var="item">
        <div class="timeline-movement">

            <div class="timeline-badge">

            </div>


            <div class="col-sm-4  timeline-item">
                <div class="row">
                    <div class="col-sm-11">
                        <div class="timeline-panel credits">
                            <ul class="timeline-panel-ul">
                                <li>
                                        ${jxf:relativetime(item.startdate)}
                                        <i class="glyphicon glyphicon-time"></i>
                                        ${jxf:dateFormat(item.startdate)}

                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>

            <div class="col-sm-8  timeline-item">
                <div class="row">
                    <div class="col-sm-offset-1 col-sm-11">
                        <div class="timeline-panel debits" data-homework="${item.id}">
                            <ul class="timeline-panel-ul">
                                <li>
                                    <span class="importo">

                                            ${item.name}
                                    </span>
                                </li>
                                <li>
                                    <div class="progress" style="height: 8px;">
                                        <div class="progress-bar progress-bar-info progress-bar-striped"
                                             role="progressbar"
                                             aria-valuenow="${item.amountsubmited}"
                                             aria-valuemin="0"
                                             aria-valuemax="${item.amount}"
                                             style="width: ${item.amountsubmited*100/(item.amount==0?1:item.amount)}%">
                                            <span class="sr-only"></span>
                                        </div>
                                    </div>
                                    ${item.amountsubmited}/${item.amount} 已交作业
                                </li>
                                <li>
                                    <p>
                                        <small class="text-muted"><i class="glyphicon glyphicon-time"></i> 
                                            ${jxf:dateFormat(item.startdate)}
                                        </small>
                                    </p>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
        <!-- end of one timeline -->
    </div>

<script>
    $(function () {
        $('.timeline-panel').on('click',function () {
            redirectTo('/homework/teacher-summary?homework='+$(this).data('homework'));
        })
    })
</script>
<%@ include file="../pageFoot.jsp" %>

<%--
  Created by Jackysoft.Inc on 2017/7/26 0026 8:32.
  User: qujiakang@126.com  
  Date: 2017/7/26 0026 8:32  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../pageHead.jsp" %>
<link rel="stylesheet" href="${sbase}static/css/homework/summary.css">
<div class="alert alert-info">
    作业批阅
</div>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-info">

            <div class="panel-heading">作业信息</div>
            <div class="panel-body">
                <p class="bg-success">${not empty bean.content?bean.content:'无要求'}</p>
                <p class="bg-success">${bean.name}</p>
                <p class="bg-success">${jxf:relativetime(bean.startdate)}</p>
                <p class="bg-success"><a href="/exercise/view/${exercise.id}">习题正文</a></p>
            </div>
        </div>

    </div>
    <div class="col-md-3 taken-list">
      <ul>
          <c:forEach items="${takens}" var="item">
              <li>${item.student.name}</li>
          </c:forEach>
      </ul>

    </div>

</div>
<ul class="nav nav-tabs tab-answer" role="tablist">
    <li role="presentation" class="active">
        <a href="#home"  aria-controls="home" role="tab" data-toggle="tab">选择题</a>
    </li>
    <c:forEach begin="1" end="${exercise.esize}" var="item">
        <li role="presentation">
            <a  href="#explan-${item}-panel" aria-controls="explan-${item}-panel" role="tab" data-toggle="tab">
                解答题${item}
            </a></li>
    </c:forEach>

</ul>
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
        <c:forEach begin="0" end="${exercise.csize-1}" var="item">
            <c:set var="itemkey">std#${item}</c:set>
            <ul class="abcd">
             <li>
             <span class="label label-info">${item+1}</span></li>
             <li>
                 <c:set var="userkeyA">A#${item}</c:set>
                 <c:set var="userkeyB">B#${item}</c:set>
                 <c:set var="userkeyC">C#${item}</c:set>
                 <c:set var="userkeyD">D#${item}</c:set>

                 <button class="btn btn-sm ${stdchoices[itemkey]=='A'?'btn-warning':'btn-info'}"
                         type="button"
                         data-container="body"
                         data-toggle="popover"
                         data-placement="top"

                         data-content="<ul>
                          <c:forEach items="${choiceUser[userkeyA]}" var="cuser">
                           <li>${cuser.name}</li>
                          </c:forEach>
                         </ul>"
                 >A</button>




                 [${fn:length(choiceUser[userkeyA])}<small>选择</small>]</li>
             <li>
                 <button
                         class="btn btn-sm ${stdchoices[itemkey]=='B'?'btn-warning':'btn-info'}"
                         type="button"
                         data-container="body"
                         data-toggle="popover"
                         data-placement="top"

                         data-content="<ul>
                          <c:forEach items="${choiceUser[userkeyB]}" var="cuser">
                           <li>${cuser.name}</li>
                          </c:forEach>
                         </ul>"
                 >B</button>
                 [${fn:length(choiceUser[userkeyB])}<small>选择</small>]</li>
             <li>
                 <button
                         class="btn btn-sm ${stdchoices[itemkey]=='C'?'btn-warning':'btn-info'}"
                         type="button"
                         data-container="body"
                         data-toggle="popover"
                         data-placement="top"

                         data-content="<ul>
                          <c:forEach items="${choiceUser[userkeyC]}" var="cuser">
                           <li>${cuser.name}</li>
                          </c:forEach>
                         </ul>"
                 >C</button>

                 [${fn:length(choiceUser[userkeyC])}<small>选择</small>]</li>
             <li>
                 <button class="btn btn-sm ${stdchoices[itemkey]=='D'?'btn-warning':'btn-info'}"
                         type="button"
                         data-container="body"
                         data-toggle="popover"
                         data-placement="top"

                         data-content="<ul>
                          <c:forEach items="${choiceUser[userkeyD]}" var="cuser">
                           <li>${cuser.name}</li>
                          </c:forEach>
                         </ul>"
                 >D</button>
                 [${fn:length(choiceUser[userkeyD])}<small>选择</small>]</li>
             <li>
                 <c:set var="ritemkey">rgt#${item}</c:set>
                     ${rights[ritemkey]}<small>人答对</small></li>

         </ul>


        </c:forEach>

    </div>
    <c:forEach begin="1" end="${exercise.esize}" var="eitem">
        <div role="tabpanel" class="tab-pane" id="explan-${eitem}-panel">
             <c:forEach items="${takens}" var="taken">

                <div class="panel panel-warning">
                    <div class="panel-heading">${taken.student.name}</div>
                    <div class="panel-body">
                        <img src="${taken.explains[eitem]}" style="height: 50px;">
                    </div>
                    <div class="panel-footer">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-success">
                                <input type="radio"
                                       class="action-score-explain"
                                       name="options_${taken.student.value}_${eitem}"
                                       data-takenid="${taken.id}"
                                       data-eindex="${eitem}"
                                       value="2"
                                       autocomplete="off" >全对
                            </label>
                            <label class="btn btn-warning">
                                <input type="radio"
                                       class="action-score-explain"
                                       name="options_${taken.student.value}_${eitem}"
                                       data-takenid="${taken.id}"
                                       data-eindex="${eitem}"
                                       value="1"
                                       autocomplete="off">半对
                            </label>
                            <label class="btn btn-danger">
                                <input type="radio"
                                       class="action-score-explain"
                                       name="options_${taken.student.value}_${eitem}"
                                       data-taken="${taken.id}"
                                       data-eindex="${eitem}"
                                       data-score="0"
                                       autocomplete="off">全错
                            </label>
                        </div>
                    </div>
                </div>






            </c:forEach>
        </div>
    </c:forEach>
</div>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover({
            'html':true
        });
        $('.action-score-explain').on('click',function () {
            var url = '/homework/scoreexplain';
            $.post(url,$(this).data()).done(function (rst) {
                console.log(rst);
            })

        })
    })

</script>
<%@ include file="../pageFoot.jsp" %>

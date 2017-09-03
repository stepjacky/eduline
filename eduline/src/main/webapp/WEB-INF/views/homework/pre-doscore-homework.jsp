<%@include file="../pageHead.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="edu" %>
<div class="alert alert-info">
    <span class="label label-info">选择答案</span>
    <p>
        标准答案:${modelAnswers}<br>
        学生答案:${answer}<br>
        得分:${score}
    </p>
    总得分
    <p>
        <c:forTokens items="A+,A,A-,B+,B,B-,C,D,E,F" delims="," var="item">
        <label>
            <input type="radio" class="topscore" name="topscore" value="${item }">${item }
        </label>
        </c:forTokens>
        <c:forEach begin="1" end="${numText}" var="item">
    <p><span class="label label-info">大题 ${item}</span>
        <label><input type="radio" name="answer-${item}" value="1">全对 </label>
        <label><input type="radio" name="answer-${item}" value="0">半对 </label>
        <label> <input type="radio" name="answer-${item}" value="-1">全错 </label>
    </p>
    </c:forEach>
    <p>
        <span class="label label-info">批阅点评</span>
        <textarea name="notes" id="notes" rows="10" cols="80"></textarea>
    </p>

    <p>
        <button type="button" class="btn btn-primary btn-lg scoreHomework"><i class="fa fa-hand-o-up"
                                                                              aria-hidden="true"></i>批阅
        </button>
    </p>
    </p>

</div>

<span class="label label-info">大题解答</span>
<edu:ShowPdf url="${url}" namespace="answerDoc"></edu:ShowPdf>

<script src="/static/lib/ckeditor/ckeditor.js"></script>


<script>
    var len = '${numText}' || 0;
    var result = [];
    for (var i = 0; i < len; i++) result[i] = 0;
    $(function () {
        CKEDITOR.replace('notes');

        var workId = "${param.workId}";

        $('button.scoreHomework').on('click', function () {
            var url = '/homework/teahcer/homework/doscore/${hwid}';
            var data = CKEDITOR.instances['notes'].getData();
            $.post(url,
                {
                    "score": $('input:radio.topscore:checked').val()
                    , "note": data
                    , "answerResult": result.join(',')
                })
                .done(function () {
                    window.location.href = '/homework/teacher/unscored/${param.workId}/0';
                });
        });
    });

    function setResult(i, v) {
        result[i] = v;
    }


</script>
<script src="static/scripts/homework/doscorehomework.js"></script>
<%@include file="../pageFoot.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">成绩打印</h3>
    </div>
    <div class="panel-body">
        <form method="get" target="_blank" class="form-inline"
              action="/examscore/reporter/scorepaper.jasper">
            <div class="form-group">
                <label>学生学号</label> <input type="text"
                                           class="form-control studentNo" name="student" placeholder="输入学生学号">
            </div>
            <div class="form-group">
                <label> 初中<input type="radio" name="jors" value="0" checked></label>
                <label>高中 <input type="radio" name="jors" value="1"></label>
                <br>
                <label>英式<input type="radio" name="style" value="0" checked></label>
                <label>美式<input type="radio" name="style" value="1"></label>

            </div>
            <input type="hidden" id="query" name="query">
            <button type="button" class="btn btn-primary"
                    onclick="printform(event)">
                <span class="glyphicon glyphicon-print"> </span>打印
            </button>
        </form>
    </div>
</div>
<script>
    function printform(event) {
        var form = $(event.currentTarget).parent('form');
        var datas = form.serializeArray();
        var da = [];
        for (var d in datas) {
            if (datas[d].name == 'query') continue;
            da.push(datas[d].name + '`' + datas[d].value);
        }
        $("#query").val(da.join(';'));
        form.submit();

    }
</script>

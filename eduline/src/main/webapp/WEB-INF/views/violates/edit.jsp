<link href="static/css/violates/edit.css" rel="stylesheet">
<p class="lead">
    编辑<span class="label label-info">奖惩记录</span>
</p>
<form class="form-horizontal">
    <div class="form-group">
        <label for="id" class="col-sm-2 control-label">编号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="id" name="id"
                   value="${bean.id}" placeholder="编号">
        </div>
    </div>
    <div class="form-group">
        <label for="affirmative" class="col-sm-2 control-label">正/负面</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="affirmative"
                   name="affirmative" value="${bean.affirmative}" placeholder="正/负面">
        </div>
    </div>
    <div class="form-group">
        <label for="student" class="col-sm-2 control-label">当事人ID</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="student" name="student"
                   value="${bean.student}" placeholder="当事人ID">
        </div>
    </div>
    <div class="form-group">
        <label for="studentName" class="col-sm-2 control-label">当事人姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="studentName"
                   name="studentName" value="${bean.studentName}" placeholder="当事人姓名">
        </div>
    </div>
    <div class="form-group">
        <label for="teacher" class="col-sm-2 control-label">记录老师ID</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="teacher" name="teacher"
                   value="${bean.teacher}" placeholder="记录老师ID">
        </div>
    </div>
    <div class="form-group">
        <label for="teacherName" class="col-sm-2 control-label">记录老师姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="teacherName"
                   name="teacherName" value="${bean.teacherName}" placeholder="记录老师姓名">
        </div>
    </div>
    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">情况说明</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="content" name="content"
                   value="${bean.content}" placeholder="情况说明">
        </div>
    </div>
    <div class="form-group">
        <label for="scoreValue" class="col-sm-2 control-label">得分</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="scoreValue"
                   name="scoreValue" value="${bean.scoreValue}" placeholder="得分">
        </div>
    </div>
    <div class="form-group">
        <label for="fireTime" class="col-sm-2 control-label">发生时间</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="fireTime" name="fireTime"
                   value="${bean.fireTime}" placeholder="发生时间">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-info persisteFormItem">
                <span class="glyphicon glyphicon-floppy-saved"></span> 保存
            </button>
        </div>
    </div>
</form>
<script src="static/scripts/violates/edit.js"></script>
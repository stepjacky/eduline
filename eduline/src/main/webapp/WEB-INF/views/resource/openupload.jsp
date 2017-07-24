<%--
  Created by Jackysoft.Inc on 2017/7/23 0023 18:48.
  User: qujiakang@126.com  
  Date: 2017/7/23 0023 18:48  
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../uploadrs.jsp" %>
<div class="panel panel-info">
    <!-- Default panel contents -->
    <div class="panel-heading">上传资源</div>
    <div id="file-body" class="panel-body">
        <p>
        <span class="btn btn-info btn-xs fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>添加文件</span>
                    <input id="fileupload" type="file" name="file" data-url="/fileupload/upload">
        </span>
        </p>
        <p>
        <span class="label label-info file-added"></span>

        <div class="progress hide" style="height:10px">
            <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar"

                 aria-valuenow="0"
                 aria-valuemin="0"
                 aria-valuemax="100">

            </div>
        </div>
        </p>
    </div>


</div>
<script>
    $(function () {
        var odata = {
            'name':null,
            'realpath':null,
            'size':null,
            'suffix':null,
            'grade':'${bean.grade}',
            'course':'${bean.course}',
            'textbook':'${bean.textbook}',
            'chapter':'${bean.chapter}',
            'owner':{'value':'${sysUser.username}','name':'${sysUser.nickname}'},
            'commontype':'${bean.commontype}',
            'styletype':'${bean.styletype}',
            'filetype':'${bean.filetype}'
        }

        $('#fileupload').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.context =
                    $('<button/>').html("<i class='fa fa-upload'></i>上传")
                        .addClass('btn btn-success btn-xs')
                    .appendTo("#file-body")
                    .click(function () {
                        $('.progress').removeClass('hide');
                        data.context = $('<p/>').text('上传中...').replaceAll($(this));
                        data.submit();

                    });
                $('.file-added').html(data.files[0].name);
                console.log(data);
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress .progress-bar').css(
                    'width',progress + '%'
                );
            },
            done: function (e, data) {
                data.context.text('上传完成');
                $('.progress').addClass('hide');
                if(data.result){
                    odata.name = data.result.name;
                    odata.filename = data.result.filename;
                    odata.suffix = data.result.suffix;
                    odata.realpath = data.result.realpath;
                    odata.size = data.result.size;
                    console.log(odata);
                    //return;
                    postJson('/resource/save',odata,function (rst) {
                        //console.log(rst);
                        reloadPage();
                    })
                }
            }
        });
    });
</script>
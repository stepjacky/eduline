<%--
  Created by Jackysoft.Inc on 2017/7/24 0024 11:19.
  User: qujiakang@126.com  
  Date: 2017/7/24 0024 11:19  
--%>
<%@ page pageEncoding="UTF-8" %><%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %><%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %><%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ include file="../uploadrs.jsp" %>
<div class="panel panel-info">
    <!-- Default panel contents -->
    <div class="panel-heading">上传习题</div>
    <div id="file-body" class="panel-body">
        <p>
            第一步:
        <span class="btn btn-info btn-xs fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>添加习题</span>
                    <input id="exercise-upload" type="file" name="file" data-url="/exercise/upload-exercise">
        </span>
        </p>
        <p>
            <span class="label label-success exercise-added"></span>

        </p>

        <p>
            第二步:
        <span class="btn btn-info btn-xs fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>添加答案</span>
                    <input id="answer-upload" type="file" name="file" data-url="/exercise/upload-answer">
        </span>
        </p>
        <p>
            <span class="label label-success answer-added"></span>
        </p>
        <p>
            第三步:
            <input type="number" v-model="esize">个大题

        </p>
        <div class="progress hide" style="height:10px">
            <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar"

                 aria-valuenow="0"
                 aria-valuemin="0"
                 aria-valuemax="100">

            </div>
        </div>
        <button type="button" class="btn btn-sm btn-info action-save">
            <i class="fa fa-save"></i>保存
        </button>
    </div>


</div>
<script>
    $(function () {



        //习题返回,name,realpath,size,suffix,
        //答案返回,choice,csize,explain,esize
        var odata = {
            'name':null,
            'realpath':null,
            'size':null,
            'suffix':null,
            'choice':null,
            'csize':0,
            'explain':null,
            'esize':0,
            'grade':'${bean.grade}',
            'course':'${bean.course}',
            'textbook':'${bean.textbook}',
            'chapter':'${bean.chapter}',
            'owner':{'value':'${sysUser.username}','name':'${sysUser.nickname}'},
            'commontype':'${bean.commontype}'
        };

        var vm = new Vue({
            'el':'#file-body',
            data:odata
        });

        $('#exercise-upload').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.submit();
                $('.exercise-added').html(data.files[0].name);

            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress .progress-bar').css(
                    'width',progress + '%'
                );
            },
            done: function (e, data) {
                $('.progress').addClass('hide');
                console.log(data.result);
                if(data.result){
                    odata.name = data.result.name;
                    odata.suffix = data.result.suffix;
                    odata.realpath = data.result.realpath;
                    odata.size = data.result.size;

                }
            }
        });

        $('#answer-upload').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.submit();
                $('.answer-added').html(data.files[0].name);

            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress .progress-bar').css(
                    'width',progress + '%'
                );
            },
            done: function (e, data) {
                $('.progress').addClass('hide');
                if(data.result){
                    odata.choice = data.result.choice;
                    odata.csize = data.result.csize;
                    odata.explain = data.result.explain;
                    return;

                }
            }
        });

        $('.action-save').on('click',function () {
            if(!odata.commontype){
                odata.commontype="personal";
            }
            postJson('/exercise/save',odata,function (rst) {
                reloadPage();
            });
        })
    })

</script>

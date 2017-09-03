<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/notechapter/edit.css" rel="stylesheet">
<p class="lead">

    编辑<span class="label label-info">讲义章节</span>
</p>
<form class="form-horizontal">
    <div class="form-group">
        <label for="id" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="id"
                   name="id" value="${bean.id}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="name"
                   name="name" value="${bean.name}" placeholder="名称">
        </div>
    </div>
    <div class="form-group">
        <label for="noteId" class="col-sm-2 control-label">所属讲义</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="noteId"
                   name="noteId" value="${bean.noteId}" placeholder="所属讲义">
        </div>
    </div>
    <div class="form-group">
        <label for="parent" class="col-sm-2 control-label">父编号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="parent"
                   name="parent" value="${bean.parent}" placeholder="父编号">
        </div>
    </div>
    <div class="form-group">
        <label for="parentName" class="col-sm-2 control-label">父名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="parentName"
                   name="parentName" value="${bean.parentName}" placeholder="父名称">
        </div>
    </div>
    <div class="form-group">
        <label for="pptFile" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="pptFile"
                   name="pptFile" value="${bean.pptFile}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="mp3File" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="mp3File"
                   name="mp3File" value="${bean.mp3File}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="keysFile" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="keysFile"
                   name="keysFile" value="${bean.keysFile}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="exerciseFile" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="exerciseFile"
                   name="exerciseFile" value="${bean.exerciseFile}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="anwserFile" class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <input type="text" class="form-control"
                   id="anwserFile"
                   name="anwserFile" value="${bean.anwserFile}" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-info persisteFormItem">
                <span class="glyphicon glyphicon-floppy-saved"></span>
                保存
            </button>
        </div>
    </div>
</form>
<script src="static/scripts/notechapter/edit.js"></script>
<link href="static/css/ebook/input.css" rel="stylesheet">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ include file="../uploadrs.jsp" %>

<div class="table-responsive">
    <p class="text text-success">
        <span class="label label-info">上传说明</span>
        上传书籍前需要填写好书籍信息,先上传<code>封面</code>,再上传 <code>书籍</code>
    </p>
    <form class="form-horizontal">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">标签[多个用半角逗号(,)隔开]</label>
            <div class="col-sm-10">
                <input type="text" name="tags" class="form-control" id="tags" placeholder="标签多个用逗号隔开">
                <c:forEach items="${tags}" var="tag">
                    <button type="button" class="btn btn-info btn-xs small-tag" key="${tag }">${tag}</button>
                </c:forEach>
                <span></span>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">豆瓣链接</label>
            <div class="col-sm-10">
                <input type="text" name="douban" class="form-control" id="inputEmail3" placeholder="豆瓣链接">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">简介</label>
            <div class="col-sm-10">
                <textarea name="remark" class="form-control" id="inputPassword3" placeholder="书籍简介"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">上传封面</label>
            <div class="col-sm-10">
                <input id="coverupload" type="file" name="cover" data-url="/ebook/cover/upload?id=">
                <input name="coverPath" type="hidden" id="coverPath">
                <img src="" id="prevImg" style="width:80px;height:100px">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">上传书籍</label>
            <div class="col-sm-10">
                <span class="label label-success">注意:系统提取所上传电子书文件名作为书名,书籍最大为50M</span>
                <input id="fileupload" type="file" name="file" data-url="/ebook/multile/upload?id=">

            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">上传进度</label>
            <div class="col-sm-10">
                <div id="progress" class="progress">
                    <div class="progress-bar progress-bar-success progress-bar-striped"
                         role="progressbar" aria-valuenow="0" aria-valuemin="0"
                         aria-valuemax="100" style="width: 0%"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="static/scripts/ebook/input.js"></script>
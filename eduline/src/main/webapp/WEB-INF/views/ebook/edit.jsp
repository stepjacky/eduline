
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="static/css/ebook/edit.css" rel="stylesheet">
<div class="container-fluid">
	<div class="row edit-style">
		<h3>${fn:substringBefore(bean.name, ".")}</h3>
	</div>
	<div class="row">
		<div class="col-md-5 edit-style">
			<img class="img-thumbnail" style="width: 300px; height: 441px;"
				src="/mycover/large/${(fn:endsWith(bean.coverPath,'.htm') || fn:endsWith(bean.coverPath,'.html'))?'404.jpg':bean.coverPath}"
				data-holder-rendered="true">
		</div>
		<div class="col-md-7 edit-style">
			<p>
				<span class="label label-default">TAGS:</span>
				<c:forTokens items="${bean.tags}" delims="," var="tag">

					<a class="label label-info "
						href="/ebook/with/tags/0/10?tags=${tag}"> ${tag} </a>

				</c:forTokens>
			</p>
			<p>
				<a class="btn btn-success btn-xs" href="${bean.douban}"
					target="_blank"> <span class="badge">豆瓣</span></a>
			</p>
			<p>
				<a class="btn btn-primary" href="/ebook/download/${bean.id}"
					class=""> <i class="fa fa-download"></i> 下载
				</a><span class="badge">${bean.clicknum}</span>次
			</p>




		</div>
	</div>
	<div class="row">
		<div class="col-md-12 edit-style">
			<div class="list-group">
				<div class="list-group-item active">
					${fn:substringBefore(bean.name, ".")}					
				</div>
                <div class="list-group-item">
                   ${bean.remark }
                </div>

			</div>
		</div>
	</div>
</div>
<script src="static/scripts/ebook/edit.js"></script>

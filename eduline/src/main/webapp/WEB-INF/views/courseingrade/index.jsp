<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">课程设置</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<div class="form-group">

				<label for="exampleInputName2">注意:</label> <span
					class="label label-danger">点击年份和年级显示所搭配课程</span>
			</div>




			<div class="form-group">

				<label for="exampleInputName2">入学年份</label>
				<div class="center-block">
					<ol class="breadcrumb" id="inyearol">
						<c:forEach var="iy" begin="2009" end="2016">
							<li><label><input type="radio" name="inyear"
									value='${iy}' class="item-inyear">${iy} 届</label></li>
						</c:forEach>
					</ol>


				</div>

			</div>
			<div class="form-group">
				<label for="exampleInputEmail2">所在年级</label>

				<div class="center-block">
					<ol class="breadcrumb" id="gradeol">

						<c:forEach items="${grades }" var="grade">
							<li><label><input type="radio" name="grade"
									value='${grade.id}' class="item-grade">${grade.name}</label></li>
						</c:forEach>
					</ol>


				</div>


			</div>

			<hr>
			<div class="center-block">
				<table class="table" id="courseTable">
					<tbody>

						<c:forEach items="${courses}" var="c" varStatus="vs">
							<c:if test="${vs.index%4==0}">
								<tr>
							</c:if>
							<td><label> <input type="checkbox" name="course"
									value="${c.id}"> <span class="label label-default">
										${c.name}</span>
							</label></td>
							<c:if test="${(vs.index+1)%4==0 }">
								</tr>
							</c:if>
						</c:forEach>

					</tbody>


				</table>
				<hr>
				<button type="button" class="btn btn-primary" id="saveAll">保存</button>

			</div>
		</form>

	</div>
</div>


<script src="static/scripts/courseingrade/index.js"></script>
<%@ include file="../pageFoot.jsp"%>
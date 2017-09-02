
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<form action="/examscore/score/single/report/scorereport"
	target="_blank">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<tbody>
			<tr>
				<th><span class="label label-default">年级</span></th>
				<c:forEach var="y" begin="7" end="12">
					<td>
						<div class="radio">
							<label> <input type="radio" name="grade" value="${y}" />
								<span class="label label-info">${y}</span>


							</label>
						</div>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<th><span class="label label-default">学期</span></th>
				<c:forEach var="y" begin="0" end="1">
					<td>
						<div class="radio">
							<label> <input type="radio" name="semester" value="${y}">
								<span class="label label-info">${jxf:semester(y)}</span>
							</label>
						</div>
					</td>
				</c:forEach>
			</tr>

			<tr>
				<th><span class="label label-default">测试名称</span></th>
				<c:forEach var="y" begin="0" end="3">
					<td>
						<div class="radio">
							<label> <input type="radio" name="monthly" value="${y}">
								<span class="label label-info">${jxf:monthly(y)}</span>
							</label>
						</div>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<th><span class="label label-default">学号</span></th>
				<td><input type="text" class="form-control studentNo"
					name="user"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9" align="center">
					<button type="submit" class="btn btn-info">查询</button>
				</td>
			</tr>
		</tfoot>
	</table>
</form>


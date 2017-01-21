<%@ include file="../pageHead.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<link href="static/css/examscore/pager.css" rel="stylesheet">
<div class="table-responsive">
<div class="btn-group">
  
  <a class="btn btn-info btn-sm"
									href="/examscore/student/score/${bean.student}/${bean.current-1}">
									下一次<i class="fa fa-arrow-left"></i>  </a>
<a class="btn btn-info btn-sm "
			href="/examscore/student/score/${bean.student}/${bean.current+1}">
	    	<i class="fa fa-arrow-right"></i>上一次  </a>										    	
  <button class="btn btn-sm btn-default">${bean.name}</button>	    	
  	    	
</div>

	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>			
			<tr>
				<th>总分: <span class="label label-info">
						${bean.totalScore} </span>
				</th>
				<th>总均分: <span class="label label-info">
						${bean.avgTotalScore} </span>
				</th>
				<th>总均分年级排名: <span class="label label-info">
						${bean.totalSorted} </span>
				</th>
				<th>${student.nickname}[${student.username}]</th>
				<th><c:if test="${bean!=null }">
						<a class="btn btn-success btn-xs"
							href="/examscore/series/avgtotal/${bean.inyear}/${bean.grade}/${bean.semester}/${bean.monthly}">总分分布</a>
					</c:if></th>
			</tr>

			<tr>
				<th>课程</th>
				<th>成绩</th>
				<th>单科年级平均分</th>
				<th>单科排名</th>
				<th>图</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${bean.details}">
				<tr>
					<td>${item.courseName}</td>
					<td><span class="label label-danger">
							${jxf:formatFloat(item.scoreValue,0)} </span></td>
					<td><span class="label label-success">
							${jxf:formatFloat(item.avgValue,1)} </span></td>
					<td><span class="label label-info"> ${item.rankValue} </span>
					</td>
					<td>
						<button type="button" class="btn btn-xs btn-success">分布图表</button>
					</td>

				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a class="btn btn-primary"
					href="examscore/score/report/${student.username}/${page}/scorereport"
					target="_blank">查看评语 </a></td>
			</tr>
		</tfoot>
	</table>
</div>
<script src="static/scripts/examscore/pager.js"></script>
<%@ include file="../pageFoot.jsp"%>
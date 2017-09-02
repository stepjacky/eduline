
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf"%>
<span class="label label-warning">注意:根据成绩数量,每次排名大概需要1.5分钟左右</span>
<ol class="breadcrumb">
  <li>入学年份</li>
  <c:forEach begin="2011" end="2018" var="year">
    <li>
      <label>
        <input type="radio" class="year" name="year" value="${year}">
        ${year}
      </label>
    </li>
  </c:forEach>
</ol>
<ol class="breadcrumb">
  <li>所在年级</li>
  <c:forEach begin="7" end="12" var="grade">
    <li>
      <label>
        <input type="radio" class="grade" name="grade" value="${grade}">
        ${jxf:grade(grade)}
      </label>
    </li>
  </c:forEach>
</ol>
<ol class="breadcrumb">
  <li>学期</li>
  <c:forEach begin="0" end="1" var="semester">
    <li>
      <label>
        <input type="radio" class="semester"  name="semester" value="${semester}">
        ${jxf:semester(semester)}
      </label>
    </li>
  </c:forEach>
</ol>
<ol class="breadcrumb">
  <li>月考次别</li>
  <c:forEach begin="0" end="3" var="monthly">
    <li>
      <label>
        <input type="radio"  class="monthly"  name="monthly" value="${monthly}">
        ${jxf:monthly(monthly)}
      </label>
    </li>
  </c:forEach>
</ol>

<button type="button" class="btn btn-info btn-lg" id="resort">重排</button>

<script>
$(function(){
	var year,grade,semester,monthly;
	$('input:radio').on('click',function(){
		if($(this).hasClass('year')){
			year = $(this).val();
			return ;
		}
		if($(this).hasClass('grade')){
			grade = $(this).val();
			return ;
		}
		if($(this).hasClass('semester')){
			semester = $(this).val();
			return ;
		}
		if($(this).hasClass('monthly')){
			monthly = $(this).val();
			return ;
		}
	});
	 $('#resort').on('click',function(){
		 
		 if(!confirm('确定要重排本次成绩!') || !year || !grade || !semester || !monthly) return;
		 var url = '/examscore/score/resorted/'+year+'/'+grade+'/'+semester+'/'+monthly;
		 var that = $(this);
		 that.prop('disabled',true);
		 $.get(url)
		 .done(function(result){
			console.log(result); 
			 that.prop('disabled',false);
		 }).fail(function(result){
			 console.dir(result); 
			 that.prop('disabled',false);
		 });
	  });
	
});
 
</script>

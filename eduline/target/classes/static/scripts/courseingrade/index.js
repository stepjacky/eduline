$(function(){
	
	function showCourse(event){
		var inyear = $("#inyearol input:checked").val();
		var grade = $("#gradeol input:checked").val();
		if(inyear&&grade)
		$.get('/courseingrade/courses/'+inyear+'/'+grade,function(data){
           
			var cs = $('#courseTable :checkbox');
			cs.prop('checked',false);
			cs.next('span').removeClass('label-success');
			cs.next('span').addClass('label-default');
			
			$.each(cs,function(i,item){
				log($.inArray(parseInt($(item).val()), data));
				if($.inArray(parseInt($(item).val()), data)!=-1){
				  $(item).prop("checked",true);
				  $(item).next('span').removeClass('label-default');
				  $(item).next('span').addClass('label-success');
				}
			});
 
		})
	}
	
	$(".item-inyear,.item-grade").on('click',showCourse);	
	
	$('#saveAll').on('click',function(event){
		var inyear = $("#inyearol input:checked").val();
		var grade = $("#gradeol input:checked").val();
		if(!inyear || !grade) return;
		var cs = $('#courseTable input:checked');
		var data = {'inyear':inyear,'grade':grade};
		var ds = [];
		if(!cs||cs.length==0)return;
		$.each(cs,function(i,item){
			ds.push($.extend({}, data,{'course':$(item).val()}));
		});
		var url = '/courseingrade/persisties';
		var pdata = "data="+JSON.stringify(ds);
		//log(pdata);
		$.post(url,pdata,function(){
			alert("保存成功");
		});
	});
	
});
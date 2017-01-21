$(function(){
	 
	 var minfo = {
	    		'0':'第一次月考',
				'1':'期中考试',
				'2':'第二次月考',
				'3':'期末考试'
	    };
	    $(".withHidden").on('click',function(event){
	    	var k = $(this).val();
	    	$(".withHidden").parent('label').css('color','#000000');
	    	$(this).parent('label').css('color','#FF0000');
	    	$("#monthlyName").val(minfo[k]);
	    	$("#userpanel").load('/examscore/scoredpanel/'+groupId+'/'+k);
	    }); 
	   $(".withHidden").first().trigger('click');
});
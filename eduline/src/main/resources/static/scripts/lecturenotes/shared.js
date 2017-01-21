/**
 * 
 */
$(function(){
    $.get('/sysuser/find/typed/2/0?ajax=true')
    .done(function(pager){
    	$.get('/static/tmps/users/user-checkeds.html',function(cnt){
    		$('#users').html(tmpstr(cnt,{pager:pager}));
    	});
    });
    $("#users").on('click','ul.pagination  li  a',function(){
    	$.get($(this).attr('link'))
        .done(function(pager){
        	$.get('/static/tmps/users/user-checkeds.html',function(cnt){
        		$('#users').html(tmpstr(cnt,{pager:pager}));
        	});
        });
    });
    var users = {};
    $('#users').on('click','input:checkbox',function(){
    	if($(this).prop('checked')){
    		users[$(this).val()]=true;
    	}else{
    		users[$(this).val()]=false;
    	}
    });
    
    $('button.doShared').on('click',function(){
    	var url = '/lecturenotes/shared/'+noteId
    	var u = [];
    	for(var k in users){
    		if(users[k])u.push(k);
    	}
    	console.log(u);
    	$.post(url,'users='+u.join('&users='))
    	.done(function(){
    		notify('分享成功');
    	});
    	
    });
});
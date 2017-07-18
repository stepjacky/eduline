/**
 * 
 */
$(function(){
	var chpId = false;
	var async = {
			enable: true,
			url: "/chapter/ztree",
			type:'get',
			autoParam: ["id=parent"]
	};
	var callback =  {	
			
			onClick:function(event, treeId, treeNode) {
				if(treeNode.isParent==true)return;	
				chpId = treeNode.id;
			}
		
	};
	var edit = {
			enable: false
	
		
	};
	
	var view = {			
	}
	 var root = {
		  	  name : name+'章节树',
				  open : true,
				  nocheck:true,
				  id:nid,
				  isParent:true
	  } ;
	var tree = intiZtree('#cpt',root,async,callback,edit,{},view);
	$('button.sendWork').on('click',function(){
		if(!chpId){
			alert("请选择要布置的作业章节");
			return;		
		}
		var days = $(this).parent('span').prev('select').val();
		var url = tmpstr("/homework/teacher/homework/assign/<%= groupId %>/<%= lecture%>/<%=  chapter %>/<%=  offset %>"
				,{"groupId":gid,"chapter":chpId,"offset":days,"lecture":nid});
		$.post(url).done(function(data){
			if(!data){
				alert("已经为该班级布置过本章的作业,请重新选择");
			}else{
				alert("作业布置成功");
			}
		});
	});
});
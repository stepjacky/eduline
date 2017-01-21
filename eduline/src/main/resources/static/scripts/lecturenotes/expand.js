var details = null;
var selects = null;
var parentNode = null;
$(function(){
	
	var c=  new Date();
	var lt = c.getTime();
	
	$.get('/static/tmps/notechapter/select-card.html?_='+lt).done(function(data){
		selects = data;
	});
	$.get('/static/tmps/notechapter/chapter-file.html?_='+lt).done(function(data){
		details = data;
	});
	var async = {
			enable: true,
			url: "/notechapter/ztree",
			type:'get',
			autoParam: ["id=parent"]
	};
	var callback =  {
			onRename:function(event, treeId, treeNode, isCancel) {
				var pnode = treeNode.getParentNode();
				var data = {name:treeNode.name,parent:pnode.id,parentName:pnode.name,noteId:nid};
				if(treeNode.id){
					data['id']=treeNode.id;
				}
				$.post('/notechapter/persiste','data='+JSON.stringify(data))
				.done(function(rst){
					
					refreshNode(treeId,pnode);	
				});
				
			},
			onRemove:function(event,treeId,treeNode){
				var pnode = treeNode.getParentNode();
				$.get('/notechapter/remove/'+treeNode.id)
				.done(function(){
					refreshNode(treeId,pnode);	
				});
				
			},
			onClick:function(event, treeId, treeNode) {
				if(treeNode.isParent==true)return;
				parentNode = treeNode.getParentNode();
				$('#letcpt').html(tmpstr(details,{
		    		id:treeNode.id,
		    		name:treeNode.name,
		    		attr:!treeNode.attr?{}:treeNode.attr,
		    	    student:dtype	
		    	}));
			},
			onDrop: function(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			    var pnode = targetNode.getParentNode();
			    if(pnode){
			    	var nodes = pnode.children;
			    	for(var i=0;i<nodes.length;i++){
			    		var n = nodes[i];
			    	   $.post('/notechapter/update/partial',{id:n.id,props:'sort:'+i});			    	   
			    	}
			    	refreshNode(treeId,pnode);	
			    }
			},
	};
	var edit = {
			enable: !dtype,
			showRemoveBtn: function (treeId, treeNode) {
				return treeNode.id!=nid;
			},
			showRenameBtn:  function (treeId, treeNode) {
				return treeNode.id!=nid;
			},
			removeTitle:'删除章节',
			renameTitle:'修改章节',
			drag: {
					autoExpandTrigger: true,
					prev:true,
					next:true,
					inner:false
				}
	
		
	};
	
	var view = {
			addHoverDom: function(treeId, treeNode) {
				if(dtype) return;
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
				var addStr = "<span class='fa fa-plus-circle' id='addBtn_" + treeNode.tId
					+ "' title='添加章节' onfocus='this.blur();'></span>";
				sObj.after(addStr);
				var btn = $("#addBtn_"+treeNode.tId);
				if (btn) btn.bind("click", function(){
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					zTree.addNodes(treeNode, {id:false, pId:treeNode.id, name:"新增章节"});
					return false;
				});
			},
			removeHoverDom: function(treeId, treeNode) {
				$("#addBtn_"+treeNode.tId).unbind().remove();
			}
	}
	 var root = {
		  	  name : name+'章节树',
				  open : true,
				  nocheck:true,
				  id:nid,
				  isParent:true
	  } ;
	var tree = intiZtree('#cpt',root,async,callback,edit,{},view);
	
});
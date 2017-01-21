/**
 * 
 */
function intiZtree(id,root,ztreeAsync,ztreeCallback,ztreeEdit,ztreeCheck,ztreeView){
	 var setting = {
				
	            async:ztreeAsync,
				callback: ztreeCallback,
				edit : ztreeEdit,
				check: ztreeCheck,
				data : {
					simpleData : {
						enable : true
					}
				},
				view:ztreeView
	 };
	
	  var zNodes = [ root ];
	  var treeObj = $.fn.zTree.init($(id), setting,zNodes);		
	  var nodes = treeObj.getNodes();
	  treeObj.expandNode(nodes[0], true, true, true);
	  return treeObj;
}

function refreshNode(treeId,treeNode){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.reAsyncChildNodes(treeNode, "refresh");
}
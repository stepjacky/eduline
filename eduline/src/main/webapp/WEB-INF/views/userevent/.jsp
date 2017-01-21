#if(!ajax)#include("../pageHead.jetx")#end
<link href="static/css/userevent/query.css" rel="stylesheet">
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">查询用户事件</h3>
	</div>
	<div class="panel-body">
		<form class="form-inline">
			<button type="button" class="btn btn-primary queryItem">
				<span class="glyphicon glyphicon-search"> </span> 查询
			</button>
		</form>
	</div>
</div>

<div class="table-responsive">
  <button type="button" class="btn btn-primary inputItem">
<span class="glyphicon glyphicon-new-window"></span>新增用户事件
</button>
  <table class="table table-striped table-bordered table-hover table-condensed">
     <thead>
       <tr>
        <th></th>        
        <th></th>        
        <th></th>        
        <th></th>        
        <th></th>        
        <th></th>        
        <th></th>        
        <th></th>        
         <th>管理</th>
       </tr>
     </thead>
     <tbody>
       #for(item : pager.dataList?![])
         <tr>
        		<td>${item.id}</td>
        		<td>${item.username}</td>
        		<td>${item.nickname}</td>
        		<td>${item.firetime}</td>
        		<td>${item.starttime}</td>
        		<td>${item.endtime}</td>
        		<td>${item.name}</td>
        		<td>${item.content}</td>
           <td class="btn-group">
                       
             <button type="button" class="btn btn-info editItem" key="${item.id}">       
        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
             </button>
             <button type="button" class="btn btn-danger removeItem" key="${item.id}">       
        		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
             </button>
             
           </td>
         </tr>       
       #end
     </tbody>
     <tfoot>
       <tr>
         <td colspan="9">
            ${pager.pagination}
         </td>
       </tr>
     </tfoot>
  </table>
</div>
<div id="inputArea" class="table-responsive">
  
</div>
<script>
  var ajax = true;
  var csrf = '${_csrf.parameterName}=${_csrf.token}';
</script>
<script src="static/scripts/userevent/query.js"></script>
#if(!ajax)#include("../pageHead.jetx")#end
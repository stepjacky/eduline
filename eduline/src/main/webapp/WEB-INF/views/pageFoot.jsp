<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</div><!-- end of main content -->	
</div><!-- end of main row -->
</div><!-- end of main container -->


<div class="container-fluid page-footer bg-warning" role="contentinfo">
    当前版本 <code>v2.0</code>
    <a href="http://www.bjnewtalent.com/">北京市新英才学校——为孩子提供卓越的世界同步课程</a>
</div>


	
	<script src="${sbase}static/lib/ie10-viewport-bug-workaround.js"></script> 

    <script>
      var csrfinfo = {
    		  name:"${_csrf.parameterName}",
    		  token:"${_csrf.token}",
    		  param:function(){
    			  return this.name+'='+this.token;
    		  },
    		  jsonData:{'${_csrf.parameterName}':'${_csrf.token}'},
    		  formData:{'name':'${_csrf.parameterName}','value':'${_csrf.token}'}
    	      	  
      }; 
     
    </script>
<script src="${sbase}static/scripts/index.js"></script>
<div id="confirm_dialog" style="display:none; cursor: default">
  <div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">确定要执行此操作?</h3>
      </div>
      <div class="panel-body">
       <button type="button" class="btn btn-sm btn-danger closeBlock" value="yes">确定</button>
        <button type="button" class="btn btn-sm closeBlock" value="no">取消</button>
      </div>
    </div>  
</div>
<div id="redirect_dialog" style="display:none; cursor: default">
 
  <div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">操作完成，需要继续添加吗?</h3>
      </div>
      <div class="panel-body">
       <button type="button" class="btn btn-sm btn-danger closeBlock" value="yes">确定</button>
  <button type="button" class="btn btn-sm closeBlock" value="no">取消</button>
      </div>
    </div>  
</div>
</body>
</html>



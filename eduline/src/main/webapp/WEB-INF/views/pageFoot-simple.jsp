<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

</div><!-- end of main container -->
<footer class="bs-docs-footer" role="contentinfo">
    <div class="container">
        <ul class="bs-docs-footer-links text-muted">
            <li>当前版本 v1.0</li>
            <li>·</li>
            <li><a href="http://www.bjnewtalent.com/">北京市新英才学校——为孩子提供卓越的世界同步课程</a></li>
        </ul>
    </div>
</footer>

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
</body>
</html>



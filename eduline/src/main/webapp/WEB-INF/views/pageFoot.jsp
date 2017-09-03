<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
</div>

<div class="layui-footer">
    当前版本 <code>v2.0</code>
    <a href="http://www.bjnewtalent.com/">北京市新英才学校——为孩子提供卓越的世界同步课程</a>
</div>
</div>
<script src="${sbase}static/lib/layui/layui.all.js"></script>
<script>

    var csrfinfo = {
        name: "${_csrf.parameterName}",
        token: "${_csrf.token}",
        param: function () {
            return this.name + '=' + this.token;
        },
        jsonData: {'${_csrf.parameterName}': '${_csrf.token}'},
        formData: {'name': '${_csrf.parameterName}', 'value': '${_csrf.token}'}

    };
</script>
<script src="${sbase}static/scripts/index.js"></script>
</body>
</html>



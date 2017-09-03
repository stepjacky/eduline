<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:if test="${not empty message }">
    <div class="alert alert-success" role="alert" style="width:400px;margin:0 auto">
            ${message}
    </div>
</c:if>

<div class="panel panel-info col-md-5">
    <!-- Default panel contents -->
    <div class="panel-heading">个人资料</div>
    <div class="panel-body">
        <form action="/modify/password" method="post">

            <input type="hidden" name="username" value="${sysUser.username}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="password">旧密码</label>
                <input
                        type="password" class="form-control" id="password"
                        name="password"
                        placeholder="输入旧密码">
            </div>
            <div class="form-group">
                <label for="passwordn">输入新密码</label> <input
                    type="password" class="form-control"
                    id="passwordn"
                    name="passwordn"
                    placeholder="输入新密码">
            </div>

            <div class="form-group">
                <label for="passwordr">输入新密码</label> <input
                    type="password" class="form-control"
                    id="passwordr"
                    name="passwordr"
                    placeholder="确认新密码">
            </div>
            <button type="submit" class="btn btn-info">保存修改</button>
        </form>
    </div>
</div>

<c:if test="${sysUser.userType==2}">
    <form action="/modify/textbook" method="post">
        <input type="hidden" name="username" value="${sysUser.username}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="panel panel-info col-md-5">

            <div class="panel-heading">选择课本</div>

        </div>
        <c:forEach items="${textbooks}" var="bookentry">
            <div class="panel panel-success col-md-5">
                <!-- Default panel contents -->
                <div class="panel-heading">${bookentry.key.name}</div>
                <table class="table">
                    <c:forEach items="${bookentry.value}" var="book" varStatus="vs">
                        <c:if test="${va.index%5==0}"><tr></c:if>
                        <td>
                            <label>
                                <input type="radio" name="textbook" value="${book.id}"
                                    ${sysUser.textbook==book.id?'checked':''}
                                >
                                    ${book.name}
                            </label>
                        </td>

                        <c:if test="${(va.index+1)%5==0}"></tr></c:if>

                    </c:forEach>

                </table>


            </div>
        </c:forEach>
        <div class="panel col-md-5">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <button type="submit" class="btn btn-info">保存修改</button>
            </div>

        </div>
    </form>
</c:if>
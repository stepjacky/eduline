<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<link href="static/css/examscore/input.css" rel="stylesheet">
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">查询成绩</h3>
    </div>
    <div class="panel-body">
        <form id="qform" class="form-inline">
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>
                        <div class="form-group pull-right">
                            <label for="student" class="control-label">学号</label> <input
                                type="text" class="form-control" name="student" id="student"
                                placeholder="学号">

                        </div>
                    </td>
                    <td>
                        <div class="form-group pull-right">
                            <label for="inyear" class="control-label">入学年份</label> <select
                                class="form-control" name="inyear" id="inyear">
                            <option value=''>请选择</option>
                            <c:forEach var="y" begin="2009" end="2016">
                                <option value="${y}">${y}届</option>
                            </c:forEach>
                        </select>

                        </div>
                    </td>
                    <td>
                        <div class="form-group pull-right">
                            <label  class="control-label">年级</label>
                            <select
                                class="form-control " name="grade" >
                                <option value=''>请选择</option>
                            <c:forEach items="${grades}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>

                        </div>

                    </td>

                </tr>
                <tr>
                    <td>
                        <div class="form-group pull-right">
                            <label for="semester" class="control-label">学期</label> <select
                                id='semester' name='semester' class='form-control withHidden'>
                            <option value=''>请选择</option>
                            <option value='0'>上学期</option>
                            <option value='1'>下学期</option>
                        </select>

                        </div>
                    </td>
                    <td>

                        <div class="form-group pull-right">
                            <label  class="control-label">课程</label>
                            <select
                                class='form-control' name="course">
                                <option value=''>请选择</option>
                                <c:forEach items="${courses}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </td>
                    <td>
                        <div class="form-group pull-right">
                            <label for="monthly" class="control-label">月考</label> <select
                                id='monthly' name='monthly' class='form-control withHidden'>
                            <option value=''>请选择</option>
                            <option value='0'>第一次月考</option>
                            <option value='1'>期中考试</option>
                            <option value='2'>第二次月考</option>
                            <option value='3'>期末考试</option>
                        </select>


                        </div>
                    </td>

                </tr>
                <tr>
                    <td colspan="3" align="center">


                        <button type="button" class="btn btn-primary queryItem">
                            <span class="glyphicon glyphicon-search"> </span> 查询
                        </button>

                    </td>


                </tr>
                </tbody>
            </table>
        </form>
        <input type="file" name="file" id="fileupload">
        <button class="btn btn-info" id="doUpload" type="button">导入</button>
    </div>
</div>

<script src="static/scripts/examscore/preinput.js"></script>
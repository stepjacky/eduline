<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.jackysoft.com/jsp/jstl/jxf" prefix="jxf" %>
<div id="container" style="width: 100%; height: 400px;"></div>


<script type="text/javascript">
    var datas =${jxf:toJson(scores)};
    var sdatas = [];
    for (var k in datas) {
        sdatas.push(parseFloat(datas[k]));
    }
    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '${title}成绩分布'

            },
            xAxis: {
                categories: ${jxf:toJson(names)}
            },
            yAxis: {
                title: {
                    text: '学生成绩'
                }
            },
            series: [{
                name: '${title}成绩(均分)',
                data: sdatas
            }]
        });
    });

</script>










$(function () {

    var data = {
        'name': '',
        'course':null,
        'grade':null
    };
    var vm = new Vue({
        el: '#input-item',
        data: data
    });

    $('.action-save').on('click',function () {

        var param = JSON.stringify(data);

        postJson('/textbook/save',param,function(rst){
           layer.alert('添加成功');

        });


    })
})
$(function () {

    var data = selected;
    var vm = new Vue({
        el: '#input-item',
        data: data
    });

    $('.action-save').on('click',function () {

        var param = JSON.stringify(data);

        postJson('/textbook/update',param,function(rst){
            console.log(rst);
        });


    })
})
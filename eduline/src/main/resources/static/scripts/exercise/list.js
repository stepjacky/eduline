$(function(){


    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init({});
    dataAdmin.addPagerListener();

    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        loadUrl(opt.data('url'));
    });

    $('.action-start-upload').on('click',function (e) {
        var data = $(this).data();
        if(!data.commontype || !data.chapter){
            bootbox.alert("请选择章节或者公开形式");
            return;
        }

        $.post('/exercise/openupload',data,function (data) {
            bootbox.dialog(
                {
                    title:'添加习题',
                    message: data
                }

                );

        })
    })

    $('.action-remove').on('click',function (e) {
        e.preventDefault();
        $.get('/exercise/remove/'+$(this).data('id'))
            .done(function(data){
               if(data.message!="ok"){
                   bootbox.alert(data.message);
               }
            })
    })
})
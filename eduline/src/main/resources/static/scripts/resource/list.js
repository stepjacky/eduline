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
        $.post('/resource/openupload',data,function (data) {

            bootbox.dialog(

                {
                    title:'上传资源',
                    message: data
                }

                );

        })
    })

    $('.action-remove').on('click',function (e) {
        e.preventDefault();
        $.get('/resource/remove/'+$(this).data('id'))
            .done(function(data){
                if(data.message!="ok"){
                    bootbox.alert(data.message);
                }
            })
    })
})
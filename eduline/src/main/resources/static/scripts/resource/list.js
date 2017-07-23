$(function(){

    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        redirectTo(opt.data('url'));
    });

    $('.action-start-upload').on('click',function (e) {
        var data = $(this).data();

        console.log(data);
        $.post('/resource/openupload',data,function (data) {

            bootbox.dialog(

                {
                    title:'上传资源',
                    message: data
                }

                );

        })
    })
})